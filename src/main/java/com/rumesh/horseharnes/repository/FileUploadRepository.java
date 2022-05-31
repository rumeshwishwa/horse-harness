package com.rumesh.horseharnes.repository;

import com.rumesh.horseharnes.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
    Optional<FileUpload> findByFileNameAndIsValidInsert(String fileName, Boolean aTrue);
}
