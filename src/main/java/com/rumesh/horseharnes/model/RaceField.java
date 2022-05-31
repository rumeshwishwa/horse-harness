package com.rumesh.horseharnes.model;

import com.rumesh.horseharnes.type.RunnerStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "RACE_FIELD")
public class RaceField {

    @Id
    @Column(name = "RACE_FIELD_ID")
    private String raceFieldId;

    @ManyToOne
    @JoinColumn(name = "RACE_CODE")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "HORSE_ID")
    private Horse horse;

    @Column(name = "SADDLE_CLOTH")
    private Integer saddleCloth;

    @Column(name = "PLACE")
    private Integer place;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private RunnerStatus status;
}
