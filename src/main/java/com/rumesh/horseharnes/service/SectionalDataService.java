package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.exception.FileUploadException;
import com.rumesh.horseharnes.factory.ConverterFactory;
import com.rumesh.horseharnes.model.*;
import com.rumesh.horseharnes.repository.SectionalDataRepository;
import com.rumesh.horseharnes.type.MessageLevel;
import com.rumesh.horseharnes.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SectionalDataService {

    private final CsvFileReaderService<SectionalDataRecord> csvFileReaderService;
    private final ConverterFactory converterFactory;
    private final SectionalDataRepository sectionalDataRepository;
    private final RaceMeetingService raceMeetingService;
    private final RaceFieldService raceFieldService;
    private final HorseService horseService;
    private final RaceService raceService;
    private final FileUploadService fileUploadService;

    public SectionalDataService(CsvFileReaderService csvFileReaderService,
                                ConverterFactory converterFactory,
                                SectionalDataRepository sectionalDataRepository,
                                RaceMeetingService raceMeetingService,
                                RaceFieldService raceFieldService,
                                HorseService horseService,
                                RaceService raceService, FileUploadService fileUploadService) {
        this.csvFileReaderService = csvFileReaderService;
        this.converterFactory = converterFactory;
        this.sectionalDataRepository = sectionalDataRepository;
        this.raceMeetingService = raceMeetingService;
        this.raceFieldService = raceFieldService;
        this.horseService = horseService;
        this.raceService = raceService;
        this.fileUploadService = fileUploadService;
    }

    public List<ValidationResponse> storeSectionalData(MultipartFile file,
                                                       String location) {
        if (fileUploadService.isAllowToSubmit(file.getOriginalFilename())) {
            final List<SectionalDataRecord> sectionalDataRecords =
                    csvFileReaderService.read(file, converterFactory.getConverter(location));
            return storeRecords(file, sectionalDataRecords);
        } else {
            throw new FileUploadException("Already uploaded file. please check and upload a new file.");
        }
    }

    public SectionalData createData(SectionalDataRecord record,
                                    Optional<Horse> horse,
                                    Optional<Race> race) {
        SectionalData sectionalData = new SectionalData();
        horse.ifPresent(sectionalData::setHorse);
        race.ifPresent(sectionalData::setRace);
        BeanUtils.copyProperties(record, sectionalData);
        return sectionalDataRepository.save(sectionalData);
    }

    private List<ValidationResponse> storeRecords(MultipartFile file, List<SectionalDataRecord> sectionalDataRecords) {
        final List<ValidationResponse> validationResponses = sectionalDataRecords
                .stream()
                .map(this::validateAndStore)
                .collect(Collectors.toList());
        final boolean isValidFile = validationResponses.stream()
                .allMatch(res -> res.getMessages().isEmpty());
        if (!isValidFile) {
            sectionalDataRepository.deleteByFileName(file.getOriginalFilename());
        }
        fileUploadService.create(file.getOriginalFilename(), isValidFile);
        return validationResponses;
    }

    private ValidationResponse validateAndStore(SectionalDataRecord record) {
        ValidationResponse response = new ValidationResponse();
        response.setLineNo(record.getLineNo());
        List<ResponseMessage> messages = new ArrayList<>();

        Optional<Race> race = raceValidation(record, messages);
        Optional<Horse> horse = horseValidation(record, messages);
        raceFieldValidation(record, race, horse, messages);
        final SectionalData data = createData(record, horse, race);
        Optional.ofNullable(data)
                .ifPresent(secData -> record.setRecordId(secData.getSectionalDataId()));
        response.setRecord(record);
        response.setMessages(messages);
        return response;
    }

    private Optional<Race> raceValidation(SectionalDataRecord record,
                                  List<ResponseMessage> messages) {
        final Optional<Race> race = raceService.getRaceByLocationAndRaceNoAndDate(record);
        if (!race.isPresent()) {
            messages.add(getResponseMessage(
                    String.format("No race found for input combination -> Location: %s , Race No: %s , Date: %s",
                            record.getRaceLocation(), record.getRaceNo(), record.getRaceDate().toString()),
                    MessageLevel.ERROR));
        }
        return race;
    }

    private Optional<Horse> horseValidation(SectionalDataRecord record,
                                    List<ResponseMessage> messages) {
        Optional<Horse> horse = horseService.findByHorseName(record.getHorseName());
        if (!horse.isPresent()) {
            horse = horseService.searchByHorseName(record.getHorseName());
        }
        if (!horse.isPresent()) {
            messages.add(getResponseMessage(
                    String.format("No horse found for name with %s", record.getHorseName()),
                    MessageLevel.ERROR));
        }
        return horse;
    }

    private Optional<RaceField> raceFieldValidation(SectionalDataRecord record,
                                                          Optional<Race> race,
                                                          Optional<Horse> horse,
                                                          List<ResponseMessage> messages) {
        final Optional<RaceField> raceField = raceFieldService.findByRace(race, horse);
        if (!raceField.isPresent()) {
            messages.add(getResponseMessage(
                    String.format("No race field found for input combination -> Location: %s , Race No: %s , Date: %s , Horse Name: %s",
                            record.getRaceLocation(), record.getRaceNo(), record.getRaceDate().toString(),
                            record.getHorseName()),
                    MessageLevel.ERROR));
        }
        return raceField;
    }

    public Insights getInsights() {
        Insights insights = new Insights();
        final List<SectionalData> sectionalData = sectionalDataRepository.findAll();
        final List<Map<String, Double>> avgSpeedPerHorse = sectionalData.stream()
                .map(data -> {
                    final double avgSpeed =
                            data.getRace().getDistance() / DateTimeUtil.parseTimeToSeconds(data.getRaceFinishTime());
                    return Collections.singletonMap(data.getHorse().getName(), avgSpeed);
                })
                .collect(Collectors.toList());
        final double avgSpeed = sectionalData.stream()
                .map(data -> data.getRace().getDistance() / DateTimeUtil.parseTimeToSeconds(data.getRaceFinishTime()))
                .mapToDouble(Double::doubleValue).average().getAsDouble();
        insights.setAvgHorseSpeed(avgSpeed);
        insights.setAvgHorseSpeedsPerHorse(avgSpeedPerHorse);

        final List<String> raceWinningHorses = sectionalDataRepository.findByFinishingPlace(1)
                .stream()
                .map(record -> record.getHorse().getName())
                .collect(Collectors.toList());
        insights.setRaceWinningHorses(raceWinningHorses);
        return insights;
    }

    private ResponseMessage getResponseMessage(String message,
                                               MessageLevel messageLevel) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageLevel(messageLevel);
        responseMessage.setMessage(message);
        return responseMessage;
    }
}
