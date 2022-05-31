package com.rumesh.horseharnes.util;

import com.rumesh.horseharnes.config.DateTimeFormats;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class used to do the application specific date time operations
 *
 * @author Rumesh
 */

public class DateTimeUtil {

    /**
     * Class used to do the application specific date time operations
     *
     * @param date String date that need to convert to LocalDateTime
     * @author Rumesh
     */
    public static LocalDateTime getDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DateTimeFormats.DD_MM_YYYY_HH_MM_SS));
    }

    public static LocalDate getDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static int parseTimeToSeconds(String time) {
        // expected time format mm:ss.xxx
        try {
            int finalSeconds = 0;
            final String[] split = time.split(":");
            finalSeconds = TokenParser.parseIntegerToken(split[0]) * 60;

            if (split.length > 0) {
                final String[] secSplit = split[1].split("\\.");
                finalSeconds = finalSeconds + TokenParser.parseIntegerToken(secSplit[0]);
            }
            return finalSeconds;
        } catch (Exception ex) {
            return 0;
        }
    }
}
