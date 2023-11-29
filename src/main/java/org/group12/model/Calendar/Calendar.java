package org.group12.model.Calendar;

import javafx.util.Pair;
import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calendar implements IObservable {
    private List<Event> eventList;
    private boolean isEmpty;
    private List<IPlanITObserver> observers;
    private eventFactory eventFactory;

    public Calendar() {
        this.eventList = new ArrayList<>();
        this.isEmpty = true;
        this.observers = new ArrayList<>();
        this.eventFactory = new eventFactory();
    }

    // ---------- methods ----------------

    public void sortEvents(){
        EventSorter.sortEvents(eventList);
    }

    public void addEvent(String title, String description,
                         Pair<LocalDateTime, LocalDateTime> timeFrame){
        Event newEvent = eventFactory.createEvent(title, description, timeFrame);
        eventList.add(newEvent);
        this.isEmpty = false;
        notifyObservers();
    }
    public void addEvent(Event event){
        eventList.add(event);
        this.isEmpty = false;
        notifyObservers();
    }

    /**
     *
     * @param event is removed from the eventList
     *              if the event is recurring, all events with the same parent ID are also removed
     */
    public void removeEvent(Event event){
        // if event is recurring, remove all events with the same parent ID as this event has
        if (event.getRecurrence()){
            // remove all events with same ID
            for (Event e : eventList){
                if (e.getParentEvent().getID().equals(event.getID())){
                    eventList.remove(e);
                }
            }
        }
        eventList.remove(event);
        isEmpty = eventList.isEmpty();
        notifyObservers();
    }

    /**
     *
     * @param event sends the event to the factory to create a new event with the same parameters but new ID
     *            the new event is added to the eventList
     *           Call this method when you want to make an event recurring (how many times is not specified here)
     */
    public void makeRecurring(Event event){
        event.setRecurrence(true);
        Event newEvent = eventFactory.createEvent(event.getTitle(), event.getDescription(), event.getTimeFrame(), event);
        eventList.add(newEvent);
        notifyObservers(); // dangerous to notify observers here, since it will notify the observers multiple times
    }
    public void updateEvent(Event event){

    }

    // ----------- getters ----------------
    public List<Event> getUpcomingEvents(){
        return EventSorter.getEventsAfterNow(eventList);
    }
    public List<Event> getPastEvents(){
        return EventSorter.getEventsBeforeNow(eventList);
    }
    public List<Event> getEvents(){
        return eventList;
    }
    public List<Event> getEventsBetweenDates(){
        return EventSorter.getEventsBetweenDates(eventList, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
    }


    // ----------- notifiers ----------------

    @Override
    public void addObserver(IPlanITObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPlanITObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (IPlanITObserver observer : observers) {
            observer.update();
        }
    }

}
