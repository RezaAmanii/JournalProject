package org.group12.model.Calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

public class CalendarWeek {
    private final List<LocalDate> days;
    private final LocalDate firstDay;
    private final LocalDate lastDay;

    public static CalendarWeek ofDay(LocalDate aDayInTheWeek) {
        return new CalendarWeek(aDayInTheWeek);
    }

    public static CalendarWeek currentWeek() {
        return ofDay(LocalDate.now());
    }

    private CalendarWeek(LocalDate aDayInTheWeek) {
        days = getWeekDays(aDayInTheWeek);
        firstDay = days.stream().min(naturalOrder()).orElseThrow();
        lastDay = days.stream().max(naturalOrder()).orElseThrow();
    }

    public CalendarWeek nextWeek() {
        return new CalendarWeek(lastDay.plusDays(1));
    }

    public CalendarWeek previousWeek() {
        return new CalendarWeek(firstDay.minusDays(1));
    }

    public boolean isEventInThisWeek(Event event) {
        return event.getTimeFrame().getKey().toLocalDate().isAfter(firstDay.minusDays(1))
                && event.getTimeFrame().getValue().toLocalDate().isBefore(lastDay.plusDays(1));
    }


    private List<LocalDate> getWeekDays(LocalDate aDayInTheWeek) {
        var weekOfMonth = weekOfMonth(aDayInTheWeek);
        return IntStream.rangeClosed(1, aDayInTheWeek.getMonth().maxLength())
                .mapToObj(aDayInTheWeek::withDayOfMonth)
                .filter(day -> weekOfMonth == weekOfMonth(day))
                .sorted()
                .collect(toList());
    }

    private int weekOfMonth(LocalDate date) {
        return date.get(WeekFields.SUNDAY_START.weekOfMonth());
    }

    public List<LocalDate> getDays() {
        return days;
    }

    public LocalDate getFirstDay() {
        return firstDay;
    }

    public LocalDate getLastDay() {
        return lastDay;
    }

    public String getMonth() {
        return DateTimeFormatter.ofPattern("MMMM").format(firstDay);
    }

    public String getYear() {
        return DateTimeFormatter.ofPattern("yyyy").format(firstDay);
    }
}
