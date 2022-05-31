package com.rumesh.horseharnes.repository;

import com.rumesh.horseharnes.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RaceRepository extends JpaRepository<Race, String> {

    @Query("select r from Race as r join r.raceMeeting as rm " +
            "where rm.trackName = :trackName and r.raceOrder = :raceOrder " +
            "and r.scheduledTime between :startTime and :endTime")
    Optional<Race> findByRaceForTrackNameAndRaceOrderAndDate(@Param("trackName") String trackName,
                                                            @Param("raceOrder") Integer raceOrder,
                                                            @Param("startTime") LocalDateTime startTime,
                                                            @Param("endTime") LocalDateTime endTime);
}
