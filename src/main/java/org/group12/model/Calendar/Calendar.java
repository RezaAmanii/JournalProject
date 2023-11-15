package org.group12.model.Calendar;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<Event> eventList;
    private boolean isEmpty;
//    private List<CalendarObser> observers;
    private eventFactory eventFactory;

    public Calendar() {
        this.eventList = new ArrayList<>();
        this.isEmpty = true;
//        this.observers = new ArrayList<>();
    }
    public void sortEvents(){
        EventSorter.sortEvents(eventList);
    }

    public void addEvent(String title, String description, LocalDateTime dateOfEvent,
                         Pair<LocalDateTime, LocalDateTime> timeFrame){
        Event newEvent = eventFactory.createEvent(title, description, dateOfEvent, timeFrame);
        eventList.add(newEvent);
        this.isEmpty = false;
//        notifyObservers();
    }
    public void removeEvent(Event event){
        eventList.remove(event);
        isEmpty = eventList.isEmpty();
//        notifyObservers();
    }
    public void updateEvent(Event event){

    }
    public List<Event> getUpcomingEvents(){
        return EventSorter.getEventsAfterNow(eventList);
    }
    public List<Event> getPastEvents(){
        return EventSorter.getEventsBeforeNow(eventList);
    }
    public List<Event> getEvents(){
        return eventList;
    }

//    private void notifyObservers() {
//        for (EventObserver observer : observers) {
//            observer.update();
//        }
//    }
//    public void addObserver(EventObserver observer){
//        observers.add(observer);
//    }
//    public void removeObserver(EventObserver observer){
//        observers.remove(observer);
//    }
}
