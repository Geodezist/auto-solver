package ua.com.bpgdev.autosolver.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date addHours(Date date, long hours) {
        return localDateTimeToDate(dateToLocalDateTime(date).plusHours(hours));
    }

    public static Date addDay(Date date, long days) {
        return localDateTimeToDate(dateToLocalDateTime(date).plusDays(days));
    }

    public static Date addMonths(Date date, long months) {
        return localDateTimeToDate(dateToLocalDateTime(date).plusMonths(months));
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
