package org.group12.model.Calendar;

import javafx.util.Pair;
import org.group12.IDateCreated;
import org.group12.INameable;
import org.group12.model.IEvent;

import java.time.LocalDateTime;
import java.util.List;

public class Event implements IEvent, INameable, IDateCreated {
    private String title;
    private String description;
    private final LocalDateTime dateCreated;
    private LocalDateTime dateOfEvent;
    private long ID;
    private Pair<LocalDateTime, LocalDateTime> timeFrame;
    private List<String> tags;
    private boolean recurrence;

    public Event(Long ID, String title, String description, LocalDateTime dateOfEvent,
                 Pair<LocalDateTime, LocalDateTime> timeFrame, List<String> tags, boolean recurrence) {

        this.dateCreated = LocalDateTime.now();
        this.title = title;
        this.ID = ID;
        this.description = description;
        this.dateOfEvent = dateOfEvent;
        this.timeFrame = timeFrame;
        this.tags = tags;
        this.recurrence = recurrence;
    }
//    @Override
//    public void setTimeFrame(Pair<LocalDateTime, LocalDateTime> timeFrame){
//        this.timeFrame = timeFrame;
//    }
    @Override
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public boolean getRecurrence() {
        return recurrence;
    }

    @Override
    public Pair<LocalDateTime, LocalDateTime> getTimeFrame() {
        return timeFrame;
    }
    @Override
    public long getID() {
        return ID;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @Override
    public LocalDateTime getDateOfEvent() {
        return dateOfEvent;
    }

}
