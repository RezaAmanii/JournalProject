package org.group12.model.Calendar.interfaces;

import javafx.util.Pair;

import java.time.LocalDateTime;

public interface IEventFactory {
    IEvent createEvent(String title, String description, LocalDateTime dateOfEvent, Pair<LocalDateTime, LocalDateTime> timeFrame);
    IEvent createEvent(String title, String description, Pair<LocalDateTime, LocalDateTime> timeFrame, IEvent parentEvent);
}
