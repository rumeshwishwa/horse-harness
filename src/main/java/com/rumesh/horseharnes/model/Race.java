package com.rumesh.horseharnes.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "RACE")
public class Race {

    @Id
    @Column(name = "RACE_CODE")
    private String raceCode;

    @ManyToOne
    @JoinColumn(name = "RACE_MEETING_CODE")
    private RaceMeeting raceMeeting;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SCHEDULED_TIME")
    private LocalDateTime scheduledTime;

    @Column(name = "RACE_ORDER")
    private Integer raceOrder;

    @Column(name = "DISTANCE")
    private Double distance;
}
