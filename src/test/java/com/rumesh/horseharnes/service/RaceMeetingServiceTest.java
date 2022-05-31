package com.rumesh.horseharnes.service;


import com.rumesh.horseharnes.repository.RaceMeetingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RaceMeetingServiceTest {

    @Mock
    private RaceMeetingRepository raceMeetingRepository;

    private RaceMeetingService raceMeetingService;

    @BeforeEach
    void setUp() {
        raceMeetingService = new RaceMeetingService(raceMeetingRepository);
    }

}
