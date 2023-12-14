package org.group12.model.Calendar;

import javafx.util.Pair;
import org.group12.model.Calendar.factories.eventFactory;
import org.group12.model.Calendar.interfaces.IEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecurringEventHandler {
    private static eventFactory eventFactory = org.group12.model.Calendar.factories.eventFactory.getInstance();

    public static List<IEvent> makeRecurring(IEvent event, int frequency, int duration) {
        event.setRecurrence(true);
        int iterations = duration / frequency;
        List<IEvent> newEvents = new ArrayList<>();
        for (int i = 1; i < iterations + 1; i++) {
            LocalDateTime oldStart = event.getTimeFrame().getKey();
            LocalDateTime oldEnd = event.getTimeFrame().getValue();
            LocalDateTime newStart = oldStart.plusDays(frequency * i);
            LocalDateTime newEnd = oldEnd.plusDays(frequency * i);
            Pair<LocalDateTime, LocalDateTime> newTimeFrame = new Pair<>(newStart, newEnd);

            IEvent newEvent = eventFactory.createEvent(event.getTitle(), event.getDescription(), newTimeFrame.getKey(), newTimeFrame);
            newEvents.add(newEvent);
        }
        return newEvents;
    }

    public static List<IEvent> removeRecurring(IEvent event, List<Event> eventList) {
        List<IEvent> toRemove = new ArrayList<>();
        for (Event e : eventList) {
            if (e.getParentEvent() != null && e.getParentEvent().getID().equals(event.getID())) {
                toRemove.add(e);
            }
        }
        event.setRecurrence(false);
        return toRemove;
    }
}