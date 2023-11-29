package org.group12.model.Calendar;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class eventFactory {
    //TODO implement factory in the same way as todo and journal
    private EventIdFactory idFactory;

    public eventFactory() {
        this.idFactory = new EventIdFactory();
    }
    /**
     * Creates a new Event with a generated ID, title set to the current date,
     * provided content, and timestamps set to the current date.
     *
     * @param title the name of event
     * @param description the content for the new event
     * @param timeFrame when the event is happening
     * @return a new Event object with ID
     */

    public Event createEvent(String title, String description,
                             Pair<LocalDateTime, LocalDateTime> timeFrame) {
        List<String> tags = new ArrayList<>();
        String ID = idFactory.generateID();
        LocalDateTime createdTimestamp = LocalDateTime.now();
        return new Event(ID, title, description, timeFrame, createdTimestamp, tags, false, null);
    }
    public Event createEvent(String title, String description,
                             Pair<LocalDateTime, LocalDateTime> timeFrame, Event parentEvent){
        List<String> tags = new ArrayList<>();
        String ID = idFactory.generateID();
        LocalDateTime createdTimestamp = LocalDateTime.now();
        return new Event(ID, title, description, timeFrame, createdTimestamp, tags, false, parentEvent);
    }
}
