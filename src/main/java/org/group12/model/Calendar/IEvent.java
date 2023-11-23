package org.group12.model.Calendar;

import javafx.util.Pair;

import java.time.LocalDateTime;

public interface IEvent {
    String getID();
    LocalDateTime getDateOfEvent();

    Pair<LocalDateTime, LocalDateTime> getTimeFrame();

    boolean getRecurrence();


}
