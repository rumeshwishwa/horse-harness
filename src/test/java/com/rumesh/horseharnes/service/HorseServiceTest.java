package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.repository.HorseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HorseServiceTest {

    @Mock
    private HorseRepository horseRepository;

    private HorseService horseService;

    @BeforeEach
    void setUp() {
        horseService = new HorseService(horseRepository);
    }
}
