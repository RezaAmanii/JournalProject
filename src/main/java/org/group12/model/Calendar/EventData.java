package org.group12.model.Calendar;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an event data object.
 */
public class EventData implements Serializable {
    private String title;
    private String description;
    private LocalDateTime from;
    private LocalDateTime To;

    /**
     * Creates an EventData object with the specified title, description, start time, and end time.
     *
     * @param title       The title of the event.
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventData(String title, String description, LocalDateTime from, LocalDateTime to) {
        this.title = title;
        this.description = description;
        this.from = from;
        To = to;
    }

    public EventData() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return To;
    }

}
