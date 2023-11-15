package org.group12.model;

import javafx.util.Pair;

import java.time.LocalDateTime;

public interface IEvent {
    long getID();

    String getTitle();

    String getDescription();

    LocalDateTime getDateCreated();

    LocalDateTime getDateOfEvent();

    Pair<LocalDateTime, LocalDateTime> getTimeFrame();
    void addTag(String tag);

    boolean getRecurrence();
}
