package ru.restaurants.web.convecter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.restaurants.util.DataTimeUtil.*;

public class DataTimeFormatters {
    public static class LocalDateFormatter implements Formatter<LocalDate>{

        @Override
        public LocalDate parse(String s, Locale locale) throws ParseException {
            return parseLocalDate(s);
        }

        @Override
        public String print(LocalDate localDate, Locale locale) {
            return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    public static class LocalTimeFormatter implements Formatter<LocalTime>{
        @Override
        public LocalTime parse(String s, Locale locale) throws ParseException {
            return parseLocalTime(s);
        }

        @Override
        public String print(LocalTime localTime, Locale locale) {
            return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }




}
