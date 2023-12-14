package org.group12.model.Calendar;

import org.group12.model.Calendar.interfaces.IEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventSorter implements Serializable {

    // ----- O( nlo(n) ) time complexity -----------

    public static void sortEvents(List<IEvent> eventList) {
        Collections.sort(eventList, new Comparator<IEvent>() {
            @Override
            public int compare(IEvent e1, IEvent e2) {
                return e1.getDateOfEvent().compareTo(e2.getDateOfEvent());
            }
        });
    }

    // ----- O( n ) time complexity -----------
    // these methods does not require the list to be sorted

    public static List<IEvent> getEventsAfterNow(List<IEvent> eventList) {
        LocalDateTime now = LocalDateTime.now();
        List<IEvent> result = new ArrayList<>();
        for (IEvent event : eventList) {
            if (event.getDateOfEvent().isAfter(now)) {
                result.add(event);
            }
        }
        return result;
    }

    public static List<IEvent> getEventsBeforeNow(List<IEvent> eventList) {
        LocalDateTime now = LocalDateTime.now();
        List<IEvent> result = new ArrayList<>();
        for (IEvent event : eventList) {
            if (event.getDateOfEvent().isBefore(now)) {
                result.add(event);
            }
        }
        return result;
    }

    public static List<IEvent> getEventsBetweenDates(List<IEvent> eventList, LocalDateTime date1, LocalDateTime date2) {
        List<IEvent> result = new ArrayList<>();
        for (IEvent event : eventList) {
            if (event.getDateOfEvent().isAfter(date1) && event.getDateOfEvent().isBefore(date2)) {
                result.add(event);
            }
        }
        return result;
    }
    public static List<IEvent> getEventsByTag(List<IEvent> eventList, String tag){
        List<IEvent> eventsWithTag = new ArrayList<>();
        for (IEvent event : eventList){
            if (event.getTags().contains(tag)){
                eventsWithTag.add(event);
            }
        }
        return eventsWithTag;
    }
    public static List<String> getTags(List<IEvent> eventList){
        List<String> tags = new ArrayList<>();
        for (IEvent event : eventList){
            for (String tag : event.getTags()){
                if (!tags.contains(tag)){
                    tags.add(tag);
                }
            }
        }
        return tags;
    }
}
