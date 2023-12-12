package org.group12.model.Calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

/**
 * Represents a calendar week.
 */
public class CalendarWeek {
    private final List<LocalDate> days;
    private final LocalDate firstDay;
    private final LocalDate lastDay;

    /**
     * Creates a CalendarWeek object based on a specific day in the week.
     *
     * @param aDayInTheWeek A LocalDate representing a day in the desired week.
     * @return The CalendarWeek object representing the week.
     */
    public static CalendarWeek ofDay(LocalDate aDayInTheWeek) {
        return new CalendarWeek(aDayInTheWeek);
    }

    /**
     * Creates a CalendarWeek object representing the current week.
     *
     * @return The CalendarWeek object representing the current week.
     */
    public static CalendarWeek currentWeek() {
        return ofDay(LocalDate.now());
    }


    /**
     * Creates a CalendarWeek object based on a specific day in the week.
     *
     * @param aDayInTheWeek A LocalDate representing a day in the desired week.
     */
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

    /**
     * Checks if an event falls within this calendar week.
     *
     * @param event The Event object to check.
     * @return True if the event falls within this week, false otherwise.
     */
    public boolean isEventInThisWeek(Event event) {
        return event.getTimeFrame().getKey().toLocalDate().isAfter(firstDay.minusDays(1))
                && event.getTimeFrame().getValue().toLocalDate().isBefore(lastDay.plusDays(1));
    }

    /**
     * Retrieves the list of days in the calendar week.
     *
     * @param aDayInTheWeek A LocalDate representing a day in the desired week.
     * @return The list of LocalDate objects representing the days in the week.
     */
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

    /**
     * Retrieves the list of days in the calendar week.
     *
     * @return The list of LocalDate objects representing the days in the week.
     */
    public List<LocalDate> getDays() {
        return days;
    }

    public LocalDate getFirstDay() {
        return firstDay;
    }

    public LocalDate getLastDay() {
        return lastDay;
    }

    /**
     * Retrieves the month of the calendar week.
     *
     * @return The month as a String.
     */
    public String getMonth() {
        return DateTimeFormatter.ofPattern("MMMM").format(firstDay);
    }

    /**
     * Retrieves the year of the calendar week.
     *
     * @return The year as a String.
     */
    public String getYear() {
        return DateTimeFormatter.ofPattern("yyyy").format(firstDay);
    }
}
