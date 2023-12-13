package org.group12.model.Calendar;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class EventSorter implements Serializable {

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

/*    public static List<Event> getEventsAfterNow(List<Event> eventList) {
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
    }*/

    private static List<Event> getEventsByCondition(List<Event> eventList, Predicate<Event> condition) {
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (condition.test(event)) {
                result.add(event);
            }
        }
        return result;
    }
    public static List<Event> getEventsAfterNow(List<Event> eventList) {
        LocalDateTime now = LocalDateTime.now();
        return getEventsByCondition(eventList, event -> event.getDateOfEvent().isAfter(now));
    }

    public static List<Event> getEventsBeforeNow(List<Event> eventList) {
        LocalDateTime now = LocalDateTime.now();
        return getEventsByCondition(eventList, event -> event.getDateOfEvent().isBefore(now));
    }

    public static List<Event> getEventsBetweenDates(List<Event> eventList, LocalDateTime date1, LocalDateTime date2) {
        return getEventsByCondition(eventList, event -> event.getDateOfEvent().isAfter(date1) && event.getDateOfEvent().isBefore(date2));
    }

    /*public static List<Event> getEventsBetweenDates(List<Event> eventList, LocalDateTime date1, LocalDateTime date2) {
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getDateOfEvent().isAfter(date1) && event.getDateOfEvent().isBefore(date2)) {
                result.add(event);
            }
        }
        return result;
    }*/
    public static List<Event> getEventsByTag(List<Event> eventList, String tag){
        List<Event> eventsWithTag = new ArrayList<>();
        for (Event event : eventList){
            if (event.getTags().contains(tag)){
                eventsWithTag.add(event);
            }
        }
        return eventsWithTag;
    }
    public static List<String> getTags(List<Event> eventList){
        List<String> tags = new ArrayList<>();
        for (Event event : eventList){
            for (String tag : event.getTags()){
                if (!tags.contains(tag)){
                    tags.add(tag);
                }
            }
        }
        return tags;
    }
}
