package org.group12.model.Calendar;

import javafx.util.Pair;
import org.group12.model.IDateCreated;
import org.group12.model.IDescription;
import org.group12.model.IEditEvent;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.List;

public class Event implements IEvent, INameable, IDateCreated, IDescription, IEditEvent {
    private String title;
    private String description;
    private final LocalDateTime dateCreated;
    private LocalDateTime dateOfEvent;
    private String ID;
    private Pair<LocalDateTime, LocalDateTime> timeFrame;
    private List<String> tags;
    private boolean recurrence;

    public Event(String ID, String title, String description, LocalDateTime dateOfEvent,
                 Pair<LocalDateTime, LocalDateTime> timeFrame,LocalDateTime dateCreated, List<String> tags, boolean recurrence) {

        this.dateCreated = dateCreated;
        this.title = title;
        this.ID = ID;
        this.description = description;
        this.dateOfEvent = dateOfEvent;
        this.timeFrame = timeFrame;
        this.tags = tags;
        this.recurrence = recurrence;
    }
    // ------------------ methods ------------------

    @Override
    public void addTag(String tag) { this.tags.add(tag);}

    @Override
    public void removeTag(String tag) {
        // remove all instances of tag in tags
        while (this.tags.contains(tag)){
            this.tags.remove(tag);
        } }

//    public void updateEvent(){
//        listeners, obersvers
//    }

// ------------- setters -------------------
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setDateOfEvent(LocalDateTime dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    @Override
    public void setTimeFrame(Pair<LocalDateTime, LocalDateTime> timeFrame) {
        this.timeFrame = timeFrame;
    }

    @Override
    public void setRecurrence(boolean recurrence) {
        this.recurrence = recurrence;
    }

    // ---------------------- getters ----------------------
    @Override
    public boolean getRecurrence() {
        return recurrence;
    }

    @Override
    public Pair<LocalDateTime, LocalDateTime> getTimeFrame() {
        return timeFrame;
    }
    @Override
    public String getID() {
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