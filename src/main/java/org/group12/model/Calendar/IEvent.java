package org.group12.model.Calendar;

import javafx.util.Pair;

import java.time.LocalDateTime;

public interface IEvent {
    String getID();

    Pair<LocalDateTime, LocalDateTime> getTimeFrame();

    boolean getRecurrence();


}
