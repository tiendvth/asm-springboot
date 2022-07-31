package fpt.aptech.asmspringboot.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DateTimeHelper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static Random random = new Random();

    public static LocalDateTime convertStringToLocalDatetime(String date) {
        return LocalDate.parse(date, formatter).atStartOfDay();
    }

    public static String convertLocalDateTimeToString(LocalDateTime date) {
        return date.format(formatter);
    }

    public static Month getRandomMonth() {
        Month[] months = Month.values();
        return months[random.nextInt(months.length)];
    }
}
