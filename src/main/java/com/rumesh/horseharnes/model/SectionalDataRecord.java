package com.rumesh.horseharnes.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SectionalDataRecord {

    private String recordId;
    private String fileName;
    private int lineNo;
    private LocalDate raceDate;
    private String raceLocation;
    private Integer raceNo;
    private Integer tabNo;
    private String horseName;
    private String horseImportedCountry;
    private Integer finishingPlace;
    private Double runnerMarginForWinner;
    private Double runnerMarginFor800Mark;
    private Double runnerMarginFor400Mark;
    private Integer runnerWidthFor800Mark;
    private Integer runnerWidthFor400Mark;
    private String raceFinishTime;
    private String race800FinishTime;
    private String race400FinishTime;

}
