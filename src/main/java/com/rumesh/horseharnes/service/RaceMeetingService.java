package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.model.RaceMeeting;
import com.rumesh.horseharnes.model.SectionalDataRecord;
import com.rumesh.horseharnes.repository.RaceMeetingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RaceMeetingService {

    private final RaceMeetingRepository raceMeetingRepository;

    public RaceMeetingService(RaceMeetingRepository raceMeetingRepository) {
        this.raceMeetingRepository = raceMeetingRepository;
    }

    public String validateLocation(SectionalDataRecord record) {
        return Optional.ofNullable(record.getRaceLocation())
                .map(location -> raceMeetingRepository.findByTrackName(location.toUpperCase()))
                .map(raceMeeting ->  "")
                .orElse("Race location is invalid");
    }
}
