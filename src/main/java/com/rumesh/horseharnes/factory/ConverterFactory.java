package com.rumesh.horseharnes.factory;

import com.rumesh.horseharnes.config.Locations;
import com.rumesh.horseharnes.converter.AlbionParkRecordConverter;
import com.rumesh.horseharnes.converter.GloucesterParkRecordConverter;
import com.rumesh.horseharnes.converter.RecordConvertable;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ConverterFactory {

    private final AlbionParkRecordConverter albionParkRecordConverter;
    private final GloucesterParkRecordConverter gloucesterParkRecordConverter;

    public RecordConvertable getConverter(String location) {
        if (StringUtils.equalsIgnoreCase(location, Locations.ALBION_PARK)) {
            return albionParkRecordConverter;
        } else if (StringUtils.equalsIgnoreCase(location, Locations.GLOUCESTER_PARK)) {
            return gloucesterParkRecordConverter;
        } else {
            throw new RuntimeException("No converter found");
        }
    }
}
