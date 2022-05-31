package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.exception.FileUploadException;
import com.rumesh.horseharnes.factory.ConverterFactory;
import com.rumesh.horseharnes.model.*;
import com.rumesh.horseharnes.repository.SectionalDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SectionalDataServiceTest {

    private SectionalDataService sectionalDataService;

    @Mock
    private CsvFileReaderService csvFileReaderService;

    @Mock
    private ConverterFactory converterFactory;

    @Mock
    private SectionalDataRepository sectionalDataRepository;

    @Mock
    private RaceMeetingService raceMeetingService;

    @Mock
    private RaceFieldService raceFieldService;

    @Mock
    private HorseService horseService;

    @Mock
    private RaceService raceService;

    @Mock
    private FileUploadService fileUploadService;

    MockMultipartFile mockMultipartFile;
    List<SectionalDataRecord> sectionalDataRecords;
    SectionalData sectionalData1;
    SectionalData sectionalData2;

    @BeforeEach
    void setUp() {
        sectionalDataService = new SectionalDataService(
                csvFileReaderService,
                converterFactory,
                sectionalDataRepository,
                raceMeetingService,
                raceFieldService,
                horseService,
                raceService,
                fileUploadService
        );
        mockMultipartFile = new MockMultipartFile(
                "file",
                "test.csv",
                MediaType.TEXT_PLAIN_VALUE,
                ("Date,Location,Race No,TAB No,Horse,Country,Place,Margin,800Mar,400Mar," +
                        "800Width,400Width,Time,800T,600T,400T\n 01/Sep/2020,Albion Park,1,2," +
                        "DEFENSIVE GUY,,1,0,0,0,0,0,2:46.70,60.40,,30.60\n 01/Sep/2020,Albion Park,1,3," +
                        "INDEFENSIBLE,,2,9.1,13.8,12.6,0,1,2:47.43,60.05,,30.33").getBytes()
        );
        sectionalDataRecords = new ArrayList<>();
        sectionalDataRecords.add(SectionalDataRecord.builder()
                .fileName("test.csv")
                .raceNo(11)
                .lineNo(1)
                .finishingPlace(1)
                .horseImportedCountry("NZ")
                .horseName("name1")
                .race400FinishTime("22")
                .race800FinishTime("22")
                .raceFinishTime("1:00:000")
                .raceLocation("ALBION_PARK")
                .raceDate(LocalDate.now())
                .build());
        sectionalDataRecords.add(SectionalDataRecord.builder()
                .fileName("test.csv")
                .raceNo(11)
                .lineNo(2)
                .finishingPlace(1)
                .horseImportedCountry("NZ")
                .horseName("name2")
                .race400FinishTime("22")
                .race800FinishTime("22")
                .raceFinishTime("1:00:000")
                .raceLocation("ALBION_PARK")
                .raceDate(LocalDate.now())
                .build());
        sectionalData1 = new SectionalData();
        sectionalData2 = new SectionalData();
        BeanUtils.copyProperties(sectionalDataRecords.get(0),sectionalData1);
        Race race1 = new Race();
        race1.setDistance(600.0);
        sectionalData1.setRace(race1);
        Horse horse1 = new Horse();
        horse1.setName("name1");
        sectionalData1.setHorse(horse1);
        BeanUtils.copyProperties(sectionalDataRecords.get(1),sectionalData2);
        Race race2 = new Race();
        race2.setDistance(1200.0);
        sectionalData2.setRace(race2);
        Horse horse2 = new Horse();
        horse1.setName("name2");
        sectionalData2.setHorse(horse2);
    }

    @Test
    public void givenThatProperFileDataAndLocationThenSuccessWithValidationMessages() {
        when(csvFileReaderService.read(mockMultipartFile,converterFactory.getConverter("ALBION_PARK")))
                .thenReturn(sectionalDataRecords);
        when(sectionalDataRepository.save(sectionalData1)).thenReturn(sectionalData1);
        when(sectionalDataRepository.save(sectionalData2)).thenReturn(sectionalData2);
        when(fileUploadService.isAllowToSubmit("test.csv")).thenReturn(Boolean.TRUE);
        final List<ValidationResponse> validationResponses =
                sectionalDataService.storeSectionalData(mockMultipartFile, "ALBION_PARK");
        assertEquals(validationResponses.get(0).getMessages().size(),3);
    }

    @Test
    public void givenThatAlreadySubmittedFileThenThrowException() {
        when(fileUploadService.isAllowToSubmit("test.csv")).thenReturn(Boolean.FALSE);
        final FileUploadException exception = assertThrows(FileUploadException.class, () ->
                sectionalDataService.storeSectionalData(mockMultipartFile, "ALBION_PARK"));
        assertEquals("Already uploaded file. please check and upload a new file.", exception.getMessage());
    }

    @Test
    public void getInsightsTest() {
        when(sectionalDataRepository.findAll()).thenReturn(Arrays.asList(sectionalData1,sectionalData2));
        when(sectionalDataRepository.findByFinishingPlace(1)).thenReturn(Arrays.asList(sectionalData1,sectionalData2));
        final Insights insights = sectionalDataService.getInsights();
        assertEquals(15.0,insights.getAvgHorseSpeed());
        final List<String> raceWinningHorses = insights.getRaceWinningHorses();
        assertEquals(2, raceWinningHorses.size());
        assertEquals("name2",raceWinningHorses.get(0));
    }

}
