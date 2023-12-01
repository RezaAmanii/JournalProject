package org.group12.model.Calendar;

import javafx.util.Pair;
import org.group12.model.IDFactory.EventIdFactory;
import org.group12.model.IDFactory.IDFactory;
import org.group12.model.IDFactory.IIDFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class eventFactory {
    //TODO implement factory in the same way as todo and journal
    private IIDFactory idFactory;

    public eventFactory() {
        this.idFactory = IDFactory.getInstance(EventIdFactory.class);
    }
    /**
     * Creates a new Event with a generated ID, title set to the current date,
     * provided content, and timestamps set to the current date.
     *
     * @param title the name of event
     * @param description the content for the new event
     * @param dateOfEvent when the event is happening
     * @param timeFrame when the event is happening
     * @return a new Event object with ID
     */

    public Event createEvent(String title, String description, LocalDateTime dateOfEvent,
                             Pair<LocalDateTime, LocalDateTime> timeFrame) {
        List<String> tags = new ArrayList<>();
        String ID = idFactory.generateID();
        LocalDateTime createdTimestamp = LocalDateTime.now();
        return new Event(ID, title, description, dateOfEvent, timeFrame, createdTimestamp, tags, false);
    }
}
