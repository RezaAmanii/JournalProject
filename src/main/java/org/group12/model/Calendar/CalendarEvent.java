package org.group12.model.Calendar;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Represents a calendar event.
 */
public class CalendarEvent implements Serializable {
    private int rowStart;
    private int rowEnd;
    private String title;
    private String discription;

    /**
     * Constructs a CalendarEvent object based on the given Event and initial parameters.
     *
     * @param event       The Event object representing the calendar event.
     * @param FIRST_HR_ROW The row number corresponding to the first hour in the grid.
     * @param FIRST_HR    The LocalTime representing the first hour in the grid.
     */
    public CalendarEvent(Event event, int FIRST_HR_ROW, LocalTime FIRST_HR) {
        var startHr = event.getTimeFrame().getKey();
        var endHr = event.getTimeFrame().getValue();
        var endHrNormalized = !endHr.toLocalDate().isEqual(startHr.toLocalDate()) ? startHr.withHour(23) : endHr;
        this.rowStart = getGridRow(startHr.getHour(), FIRST_HR_ROW, FIRST_HR);
        this.rowEnd = getGridRow(endHrNormalized.getHour(), FIRST_HR_ROW, FIRST_HR);
        this.title = event.getTitle();
        this.discription= event.getDescription();
    }

    /**
     * Gets the description of the calendar event.
     *
     * @return The description of the calendar event.
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * Gets the starting row of the calendar event in the grid.
     *
     * @return The starting row of the calendar event.
     */
    public int getRowStart() {
        return rowStart;
    }

    /**
     * Gets the ending row of the calendar event in the grid.
     *
     * @return The ending row of the calendar event.
     */
    public int getRowEnd() {
        return rowEnd;
    }

    /**
     * Gets the title of the calendar event.
     *
     * @return The title of the calendar event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Calculates the grid row based on the given hour, first hour row, and first hour time.
     *
     * @param hr           The hour value.
     * @param FIRST_HR_ROW The row number corresponding to the first hour in the grid.
     * @param FIRST_HR     The LocalTime representing the first hour in the grid.
     * @return The calculated grid row.
     */
    private int getGridRow(int hr, int FIRST_HR_ROW, LocalTime FIRST_HR) {
        return hr < FIRST_HR.getHour() ? FIRST_HR_ROW : (hr - FIRST_HR.getHour() + FIRST_HR_ROW);
    }
}
