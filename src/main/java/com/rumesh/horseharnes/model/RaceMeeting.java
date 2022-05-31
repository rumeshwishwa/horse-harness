package com.rumesh.horseharnes.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "RACE_MEETING")
public class RaceMeeting {

    @Id
    @Column(name = "RACE_MEETING_CODE")
    private String raceMeetingCode;

    @Column(name = "SCHEDULED_DATE")
    private LocalDate scheduledDate;

    @Column(name = "TRACK_NAME")
    private String trackName;
}
