package org.group12.model.Calendar;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.List;

public interface ICalendar {
    void addEvent(String title, String description, Pair<LocalDateTime, LocalDateTime> timeFrame);
    void removeEvent(Event event);
    void makeRecurring(Event event, int frequency, int durationDays);
    void removeRecurring(Event event);
    void detachRecurring(Event event);
    List<Event> getUpcomingEvents();
    List<Event> getPastEvents();
    List<Event> getEvents();
    List<Event> getEventsBetweenDates(LocalDateTime date1, LocalDateTime date2);
    List<Event> getEventsByTag(String tag);

}
