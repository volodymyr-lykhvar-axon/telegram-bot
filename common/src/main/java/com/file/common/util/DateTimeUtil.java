package com.file.common.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Common DateTime util.
 *
 * @author Volodymyr Lykhvar
 */
public final class DateTimeUtil {

    public static final String LOCAL_DATE_TIME_ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private DateTimeUtil() {
    }

    public static LocalDateTime nowLocalDateTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
    }
}
