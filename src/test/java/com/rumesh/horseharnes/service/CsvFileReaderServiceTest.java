package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.converter.AlbionParkRecordConverter;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CsvFileReaderServiceTest {

    @Mock
    private AlbionParkRecordConverter entryRecordConverter;

    private CsvFileReaderService csvFileReaderService;

    @BeforeEach
    void setUp() {
        csvFileReaderService = new CsvFileReaderService<>();
    }
}