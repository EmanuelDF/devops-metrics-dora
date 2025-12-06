package com.devops.metrics.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Holiday {
    public static final DateTimeFormatter DT_FMT_YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static boolean isWorkDay(LocalDate date) {
        // Exemplo simples: sábado e domingo não são dias úteis
        return !(date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7);
    }

    public static long workDaysBetween(LocalDate start, LocalDate end) {
        long count = 0;
        LocalDate current = start;
        while (!current.isAfter(end)) {
            if (isWorkDay(current)) {
                count++;
            }
            current = current.plusDays(1);
        }
        return count;
    }

}
