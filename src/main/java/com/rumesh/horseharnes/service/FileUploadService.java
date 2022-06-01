package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.model.FileUpload;
import com.rumesh.horseharnes.repository.FileUploadRepository;
import org.springframework.stereotype.Service;

@Service
public class FileUploadService {

    private FileUploadRepository fileUploadRepository;

    public FileUploadService(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    public FileUpload create(FileUpload fileUpload) {
        return fileUploadRepository.save(fileUpload);
    }

    public FileUpload create(String fileName,
                             boolean isValidFile) {
        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setIsValidInsert(isValidFile);
        return fileUploadRepository.save(fileUpload);
    }

    public boolean isAllowToSubmit(String fileName) {
        return !fileUploadRepository.findByFileNameAndIsValidInsert(fileName, Boolean.TRUE).isPresent();
    }
}
