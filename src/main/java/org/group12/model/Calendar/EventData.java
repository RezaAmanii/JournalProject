package org.group12.model.Calendar;

import java.time.LocalDateTime;

public class EventData {
    private String title;
    private String description;
    private LocalDateTime from;
    private LocalDateTime To;

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
