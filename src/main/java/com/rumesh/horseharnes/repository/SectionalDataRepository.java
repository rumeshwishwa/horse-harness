package com.rumesh.horseharnes.repository;

import com.rumesh.horseharnes.model.SectionalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionalDataRepository extends JpaRepository<SectionalData, String> {

    long deleteByFileName(String fileName);

    List<SectionalData> findByFinishingPlace(int i);
}
