package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.repository.RaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RaceServiceTest {

    @Mock
    private RaceRepository raceRepository;

    private RaceService raceService;

    @BeforeEach
    void setUp() {
        raceService = new RaceService(raceRepository);
    }

}
