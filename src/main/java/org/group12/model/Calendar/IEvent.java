package org.group12.model.Calendar;

import javafx.util.Pair;

import java.time.LocalDateTime;

public interface IEvent {
    long getID();

    LocalDateTime getDateOfEvent();

    Pair<LocalDateTime, LocalDateTime> getTimeFrame();
    void addTag(String tag);

    boolean getRecurrence();
}
