package org.group12.model.Calendar;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class eventFactory {
    //TODO implement factory in the same way as todo and journal
    private String nextID= "temp";

    public Event createEvent(String title, String description, LocalDateTime dateOfEvent,
                             Pair<LocalDateTime, LocalDateTime> timeFrame) {
        List<String> tags = new ArrayList<>();
        return new Event(nextID, title, description, dateOfEvent, timeFrame, tags, false);
    }
}
