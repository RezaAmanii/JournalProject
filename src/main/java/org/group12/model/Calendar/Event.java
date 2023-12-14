package org.group12.model.Calendar;

import javafx.util.Pair;
import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.Calendar.interfaces.ICalendar;
import org.group12.model.Calendar.interfaces.IEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event implements IEvent, IObservable, Serializable {
    private String title;
    private String description;
    private final LocalDateTime dateCreated;
    private String ID;
    private Pair<LocalDateTime, LocalDateTime> timeFrame;
    private List<String> tags;
    private boolean recurrence;
    private IEvent parentEvent;
    private List<IPlanITObserver> observers;

    public Event(String ID, String title, String description,
                 Pair<LocalDateTime, LocalDateTime> timeFrame, LocalDateTime dateCreated, List<String> tags, boolean recurrence, IEvent parentEvent){

        this.dateCreated = dateCreated;
        this.title = title;
        this.ID = ID;
        this.description = description;
        this.timeFrame = timeFrame;
        this.tags = tags;
        this.recurrence = recurrence;
        this.parentEvent = parentEvent;
        this.observers = new ArrayList<>();
    }

    // ------------------ methods ------------------

    @Override
    public void addTag(String tag) { this.tags.add(tag);
        notifyObservers();
    }

    @Override
    public void removeTag(String tag) {
        // remove all instances of tag in tags
        while (this.tags.contains(tag)){
            this.tags.remove(tag);
        }
        notifyObservers();}

//    public void updateEvent(){
//        listeners, obersvers
//    }

    /**
     * Removes the parent event from this event, called when Event is edited to no longer be same as parent
     */
    private void removeParentEvent(){
        this.parentEvent = null;
    }

// ------------- setters -------------------
    @Override
    public void setTitle(String title) {
        this.title = title;
        notifyObservers();
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    @Override
    public void setTimeFrame(Pair<LocalDateTime, LocalDateTime> timeFrame) {
        this.timeFrame = timeFrame;
        notifyObservers();
    }

    @Override
    public void setRecurrence(boolean recurrence) {
        this.recurrence = recurrence;
        notifyObservers();
    }
    @Override
    public void setParentEvent(Event parentEvent) {
        this.parentEvent = parentEvent;
        notifyObservers();
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

    public LocalDateTime getDateOfEvent() {
        return timeFrame.getKey();
    }
    public LocalDateTime getEndDateOfEvent() {
        return timeFrame.getValue();
    }

    public IEvent getParentEvent() {
        return parentEvent;
    }
    public List<String> getTags() {
        return tags;
    }

//    ------------------ observers ------------------
    @Override
    public void addObserver(IPlanITObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPlanITObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IPlanITObserver observer : observers){
            observer.update();
        }
    }
}