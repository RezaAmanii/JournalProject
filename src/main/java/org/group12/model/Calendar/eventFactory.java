package org.group12.model.Calendar;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class eventFactory {
    private long nextID = 1;

    public Event createEvent(String title, String description, LocalDateTime dateOfEvent,
                             Pair<LocalDateTime, LocalDateTime> timeFrame) {
        List<String> tags = new ArrayList<>();
        return new Event(nextID, title, description, dateOfEvent, timeFrame, tags, false);
    }
}
