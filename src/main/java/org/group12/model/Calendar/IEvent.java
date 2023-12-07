package org.group12.model.Calendar;

import javafx.util.Pair;

import java.time.LocalDateTime;

public interface IEvent {

    Pair<LocalDateTime, LocalDateTime> getTimeFrame();
    LocalDateTime getDateOfEvent();

}
