package com.rumesh.horseharnes.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.rumesh.horseharnes.converter.RecordConvertable;
import com.rumesh.horseharnes.exception.CsvFileReadException;
import com.rumesh.horseharnes.model.SectionalDataRecord;
import com.rumesh.horseharnes.type.FileType;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class can be used to read data from a csv file and will be returns the data as EntryRecord list
 *
 * @author Rumesh
 */

@Slf4j
@Service
public class CsvFileReaderService<T> {

    /**
     * Method is mainly reads the data from the specified file and returns as list of EntryRecords
     *
     * @param file file path that input data resides
     * @@return List<EntryRecord> returns line count that written in to the file as a integer
     * @author Rumesh
     */
    public List<T> read(MultipartFile file,
                        RecordConvertable<T> convertable) {
        log.info("Csv input file name: {}", file.getOriginalFilename());
        List<T> recordList = new ArrayList<>();
        int lineCount = 0;
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            reader.readNext();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null)  //returns a boolean value
            {
                lineCount++;
                recordList.add(convertable.convert(nextLine, lineCount, file.getOriginalFilename()));
            }
            return recordList;
        } catch (IOException | CsvValidationException e) {
            log.error("Exception occur while reading csv file", e);
            throw new CsvFileReadException(e.getMessage());
        }
    }

}
