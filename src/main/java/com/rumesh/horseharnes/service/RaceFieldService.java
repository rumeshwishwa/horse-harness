package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.model.Horse;
import com.rumesh.horseharnes.model.Race;
import com.rumesh.horseharnes.model.RaceField;
import com.rumesh.horseharnes.repository.RaceFieldRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RaceFieldService {

    private RaceFieldRepository raceFieldRepository;

    public RaceFieldService(RaceFieldRepository raceFieldRepository) {
        this.raceFieldRepository = raceFieldRepository;
    }

    public Optional<RaceField> findByRace(Optional<Race> race, Optional<Horse> horse) {
        if (race.isPresent() && horse.isPresent()) {
            return raceFieldRepository.findByRaceAndHorse(race.get(), horse.get());
        } else {
            return Optional.empty();
        }
    }
}
