package com.rumesh.horseharnes.repository;

import com.rumesh.horseharnes.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HorseRepository extends JpaRepository<Horse, String> {

    Optional<Horse> findByName(String horseName);

    @Query("select h from Horse as h where h.name like %:horseNamePart%")
    Optional<Horse> searchByNamePart(@Param("horseNamePart") String horseNamePart);
}
