package org.group12.model.Calendar;

import javafx.util.Pair;
import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Calendar implements IObservable, ICalendar {
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
        eventList.remove(event);
        isEmpty = eventList.isEmpty();
        notifyObservers();
    }

    /**
     *
     * @param event sends the event to the factory to create a new event with the same parameters but new ID
     * @param frequency how often the event should be repeated (7 = weekly, 14 = biweekly, etc)
     * @param durationDays how many days the event should be repeated (30 makes the event repeat for a month at frequency)
     *            the new event is added to the eventList
     *           Call this method when you want to make an event recurring, and the event is not recurring already
     *                     example: makeRecurring(event, 7, 30) makes the event repeat weekly for a month which is 4 times because 30/7 = 4
     */
    public void makeRecurring(Event event, int frequency, int durationDays){
        event.setRecurrence(true);
        int iterations = durationDays/frequency;
        for (int i = 1; i < iterations+1; i++){
            LocalDateTime oldStart = event.getTimeFrame().getKey();
            LocalDateTime oldEnd = event.getTimeFrame().getValue();
            LocalDateTime newStart = oldStart.plusDays(frequency*i);
            LocalDateTime newEnd = oldEnd.plusDays(frequency*i);
            Pair<LocalDateTime, LocalDateTime> newTimeFrame = new Pair<>(newStart, newEnd);
            Event newEvent = eventFactory.createEvent(event.getTitle(), event.getDescription(), newTimeFrame, event);
            eventList.add(newEvent);
        }
        notifyObservers(); // dangerous to notify observers here, since it will notify the observers multiple times
    }
    public void removeRecurring(Event event){
        Iterator<Event> iterator = eventList.iterator();
        while (iterator.hasNext()) {
            Event e = iterator.next();
            if (e.getParentEvent() != null && e.getParentEvent().getID().equals(event.getID())) {
                iterator.remove();
            }
        }
        event.setRecurrence(false);
        notifyObservers();
    }
    public void detachRecurring(Event event){
        event.setRecurrence(false);
        event.setParentEvent(null);
        notifyObservers();
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
    public List<Event> getEventsBetweenDates(LocalDateTime date1, LocalDateTime date2){
        return EventSorter.getEventsBetweenDates(eventList, date1, date2);
    }
    public List<Event> getEventsByTag(String tag){
        return EventSorter.getEventsByTag(eventList, tag);
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
