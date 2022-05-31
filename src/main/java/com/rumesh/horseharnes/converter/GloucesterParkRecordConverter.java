package com.rumesh.horseharnes.converter;

import com.rumesh.horseharnes.config.DateTimeFormats;
import com.rumesh.horseharnes.model.SectionalDataRecord;
import com.rumesh.horseharnes.util.DateTimeUtil;
import com.rumesh.horseharnes.util.TokenParser;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GloucesterParkRecordConverter implements RecordConvertable<SectionalDataRecord> {
    @Override
    public SectionalDataRecord convert(String[] tokenArray,
                                       int lineNo,
                                       String fileName) {
        final List<String> tokens = Arrays.stream(tokenArray)
                .map(String::trim)
                .collect(Collectors.toList());
        return SectionalDataRecord.builder()
                .fileName(fileName)
                .lineNo(lineNo)
                .raceDate(DateTimeUtil.getDate(tokens.get(0), DateTimeFormats.DD_MMM_YYYY))
                .raceLocation(tokens.get(1))
                .raceNo(TokenParser.parseIntegerToken(tokens.get(3)))
                .tabNo(TokenParser.parseIntegerToken(tokens.get(4)))
                .horseName(tokens.get(5))
                .horseImportedCountry("")
                .finishingPlace(TokenParser.parseIntegerToken(tokens.get(6)))
                .runnerMarginForWinner(TokenParser.parseDoubleToken(tokens.get(7)))
                .runnerMarginFor800Mark(TokenParser.parseDoubleToken(tokens.get(8)))
                .runnerMarginFor400Mark(TokenParser.parseDoubleToken(tokens.get(9)))
                .runnerWidthFor800Mark(TokenParser.parseIntegerToken(tokens.get(10)))
                .runnerWidthFor400Mark(TokenParser.parseIntegerToken(tokens.get(11)))
                .raceFinishTime(tokens.get(12))
                .race800FinishTime(tokens.get(13))
                .race400FinishTime(tokens.get(14))
                .build();
    }
}
