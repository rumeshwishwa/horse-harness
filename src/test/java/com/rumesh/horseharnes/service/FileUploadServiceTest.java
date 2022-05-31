package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.model.FileUpload;
import com.rumesh.horseharnes.repository.FileUploadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FileUploadServiceTest {

    private FileUploadService fileUploadService;

    @Mock
    private FileUploadRepository fileUploadRepository;

    private FileUpload fileUpload;

    @BeforeEach
    void setUp() {
        fileUploadService = new FileUploadService(fileUploadRepository);
        fileUpload = new FileUpload();
        fileUpload.setIsValidInsert(Boolean.TRUE);
        fileUpload.setFileName("abc.csv");
        fileUpload.setFileUploadId("65a65s6a5s0");
    }

    @Test
    public void givenThatAlreadyExistFileWithValidUploadThenReturnFalse() {

        when(fileUploadRepository.findByFileNameAndIsValidInsert("abc.csv",Boolean.TRUE))
                .thenReturn(Optional.of(fileUpload));
        final boolean allowToSubmit = fileUploadService.isAllowToSubmit("abc.csv");
        assertFalse(allowToSubmit);
        verify(fileUploadRepository, times(1))
                .findByFileNameAndIsValidInsert("abc.csv",Boolean.TRUE);
    }

    @Test
    public void givenThatNonExistFileThenReturnTrue() {

        when(fileUploadRepository.findByFileNameAndIsValidInsert("abc.csv",Boolean.TRUE))
                .thenReturn(Optional.empty());
        final boolean allowToSubmit = fileUploadService.isAllowToSubmit("abc.csv");
        assertTrue(allowToSubmit);
        verify(fileUploadRepository, times(1))
                .findByFileNameAndIsValidInsert("abc.csv",Boolean.TRUE);
    }
}
