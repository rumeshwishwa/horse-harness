package com.rumesh.horseharnes.service;

import com.rumesh.horseharnes.model.Horse;
import com.rumesh.horseharnes.repository.HorseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HorseService {

    private final HorseRepository horseRepository;

    public HorseService(HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
    }

    public Optional<Horse> findByHorseName(String horseName) {
        return horseRepository.findByName(horseName);
    }

    public Optional<Horse> searchByHorseName(String horseName) {
        Optional<Horse> search = horseRepository.searchByNamePart(horseName);
        if (search == null) {
            String[] names = horseName.split(" ");
            search = horseRepository.searchByNamePart(names[0]);
        }
        return search;
    }
}
