package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.model.Race;
import com.rumesh.horseharnes.model.SectionalDataRecord;
import com.rumesh.horseharnes.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public Optional<Race> getRaceByLocationAndRaceNoAndDate(SectionalDataRecord record) {
        return raceRepository.findByRaceForTrackNameAndRaceOrderAndDate(record.getRaceLocation().toUpperCase(),
                record.getRaceNo(), record.getRaceDate().atStartOfDay(), record.getRaceDate().atTime(23, 59));
    }
}
