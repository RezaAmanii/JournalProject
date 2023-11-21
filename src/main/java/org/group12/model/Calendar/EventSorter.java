package org.group12.model.Calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventSorter {

    // ----- O( nlo(n) ) time complexity -----------

    public static void sortEvents(List<Event> eventList) {
        Collections.sort(eventList, new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                return e1.getDateOfEvent().compareTo(e2.getDateOfEvent());
            }
        });
    }

    // ----- O( n ) time complexity -----------
    // these methods does not require the list to be sorted

    public static List<Event> getEventsAfterNow(List<Event> eventList) {
        LocalDateTime now = LocalDateTime.now();
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getDateOfEvent().isAfter(now)) {
                result.add(event);
            }
        }
        return result;
    }

    public static List<Event> getEventsBeforeNow(List<Event> eventList) {
        LocalDateTime now = LocalDateTime.now();
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getDateOfEvent().isBefore(now)) {
                result.add(event);
            }
        }
        return result;
    }

    public static List<Event> getEventsBetweenDates(List<Event> eventList, LocalDateTime date1, LocalDateTime date2) {
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getDateOfEvent().isAfter(date1) && event.getDateOfEvent().isBefore(date2)) {
                result.add(event);
            }
        }
        return result;
    }
}
