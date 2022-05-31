package com.rumesh.horseharnes.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SECTIONAL_DATA")
@Data
public class SectionalData {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "SECTIONAL_DATA_ID")
    private String sectionalDataId;

    @ManyToOne
    @JoinColumn(name = "HORSE_CODE")
    private Horse horse;

    @ManyToOne
    @JoinColumn(name = "RACE_CODE")
    private Race race;

    @Column(name = "HORSE_IMPORTED_COUNTRY")
    private String horseImportedCountry;

    @Column(name = "FINISHING_PLACE")
    private Integer finishingPlace;

    @Column(name = "RUNNER_MARGIN_FOR_WINNER")
    private Double runnerMarginForWinner;

    @Column(name = "RUNNER_MARGIN_FOR_800_MARK")
    private Double runnerMarginFor800Mark;

    @Column(name = "RUNNER_MARGIN_FOR_400_MARK")
    private Double runnerMarginFor400Mark;

    @Column(name = "RUNNER_WIDTH_FOR_800_MARK")
    private Integer runnerWidthFor800Mark;

    @Column(name = "RUNNER_WIDTH_FOR_400_MARK")
    private Integer runnerWidthFor400Mark;

    @Column(name = "RACE_FINISH_TIME")
    private String raceFinishTime;

    @Column(name = "RACE_800_FINISH_TIME")
    private String race800FinishTime;

    @Column(name = "RACE_400_FINISH_TIME")
    private String race400FinishTime;

    @Column(name = "FILE_NAME")
    private String fileName;

}
