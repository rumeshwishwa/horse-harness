package com.rumesh.horseharnes.controller;

import com.rumesh.horseharnes.model.Insights;
import com.rumesh.horseharnes.model.ValidationResponse;
import com.rumesh.horseharnes.service.SectionalDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("sectional-data")
@RequiredArgsConstructor
public class SectionalDataController {

    private final SectionalDataService sectionalDataService;

    @PostMapping("")
    public List<ValidationResponse> storeSectionalData(@RequestPart("file") MultipartFile file,
                                                       @RequestPart String location) {
        log.info("Incoming sectional data for location: {}", location);
        return sectionalDataService.storeSectionalData(file, location);
    }

    @GetMapping("/insights")
    public Insights getInsights() {
        return sectionalDataService.getInsights();
    }

}
