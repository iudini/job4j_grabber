package ru.job4j.html;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DateParse {
    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd MM yy, HH:mm");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MM yy");
    private static final Map<String, String> MONTHS = monthsMap();

    public static LocalDateTime parse(String date) {
        if (date.contains("сегодня")) {
            date = date.replace("сегодня", LocalDate.now().format(DATE_FORMAT));
        } else if (date.contains("вчера")) {
            date = date.replace("вчера", LocalDate.now().minusDays(1).format(DATE_FORMAT));
        } else {
            if (date.charAt(1) == ' ') {
                date = "0" + date;
            }
            for (var m : MONTHS.keySet()) {
                if (date.contains(m)) {
                    date = date.replace(m, MONTHS.get(m));
                    break;
                }
            }
        }
        return LocalDateTime.parse(date, DATE_TIME_FORMAT);
    }

    private static Map<String, String> monthsMap() {
        Map<String, String> months = new HashMap<>();
        months.putAll(Map.of("янв", "01", "фев", "02", "мар", "03", "апр", "04",
                "май", "05", "июн", "06", "июл", "07", "авг", "08"));
        months.putAll(Map.of("сен", "09", "окт", "10", "ноя", "11", "дек", "12"));
        return months;
    }
}