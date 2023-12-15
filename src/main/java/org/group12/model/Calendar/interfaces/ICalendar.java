package org.group12.model.Calendar.interfaces;

import javafx.util.Pair;
import org.group12.model.Calendar.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface ICalendar {
    void addEvent(String title, String description, Pair<LocalDateTime, LocalDateTime> timeFrame);
    void addEvent(IEvent event);
    void removeEvent(IEvent event);
    void makeRecurring(IEvent event, int frequency, int durationDays);
    void removeRecurring(IEvent event);
    void detachRecurring(IEvent event);
    List<IEvent> getUpcomingEvents();
    List<IEvent> getPastEvents();
    List<IEvent> getEvents();
    List<IEvent> getEventsBetweenDates(LocalDateTime date1, LocalDateTime date2);
    List<IEvent> getEventsByTag(String tag);


}
