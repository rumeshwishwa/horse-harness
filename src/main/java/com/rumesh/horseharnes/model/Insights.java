package com.rumesh.horseharnes.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Insights {

    private double avgHorseSpeed;
    private List<String> raceWinningHorses;
    private List<Map<String,Double>> avgHorseSpeedsPerHorse;
}
