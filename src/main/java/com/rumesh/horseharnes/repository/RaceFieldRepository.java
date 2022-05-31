package com.rumesh.horseharnes.repository;

import com.rumesh.horseharnes.model.Horse;
import com.rumesh.horseharnes.model.Race;
import com.rumesh.horseharnes.model.RaceField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RaceFieldRepository extends JpaRepository<RaceField, String> {
    Optional<RaceField> findByRaceAndHorse(Race race, Horse horse);
}
