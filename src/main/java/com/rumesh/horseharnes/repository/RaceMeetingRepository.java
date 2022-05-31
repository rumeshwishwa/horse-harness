package com.rumesh.horseharnes.repository;

import com.rumesh.horseharnes.model.RaceMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceMeetingRepository extends JpaRepository<RaceMeeting, String> {
    RaceMeeting findByTrackName(String raceLocation);
}
