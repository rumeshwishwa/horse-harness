package com.rumesh.horseharnes.util;

import org.apache.commons.lang3.StringUtils;

public class TokenParser {

    public static Integer parseIntegerToken(String number) {
        return StringUtils.isEmpty(number) ? 0 : Integer.parseInt(number);
    }

    public static Long parseLongToken(String number) {
        return StringUtils.isEmpty(number) ? null : Long.parseLong(number);
    }

    public static Double parseDoubleToken(String number) {
        return StringUtils.isEmpty(number) ? null : Double.parseDouble(number);
    }
}
