package org.group12.model.Calendar;

import javafx.util.Pair;
import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.Calendar.factories.eventFactory;
import org.group12.model.Calendar.interfaces.ICalendar;
import org.group12.model.Calendar.interfaces.IEvent;
import org.group12.model.ItemsSet;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Calendar implements IObservable, ICalendar, Serializable {
    private List<IEvent> eventList;
    private boolean isEmpty;
    private List<IPlanITObserver> observers;
    private eventFactory eventFactory;
    private final ItemsSet items;

    public Calendar(ItemsSet items) {
        this.eventList = new ArrayList<>();
        this.isEmpty = true;
        this.observers = new ArrayList<>();
        this.eventFactory = new eventFactory();
        this.items = items;
    }

    // ---------- methods ----------------

    public void sortEvents(){
        EventSorter.sortEvents(eventList);
    }

    public void addEvent(String title, String description, LocalDateTime dateOfEvent,
                         Pair<LocalDateTime, LocalDateTime> timeFrame){
        IEvent newEvent = eventFactory.createEvent(title, description,dateOfEvent, timeFrame);
        eventList.add(newEvent);
        items.addItem(newEvent);
        this.isEmpty = false;
        notifyObservers();
    }

    /**
     * Adds a new event to the calendar.
     *
     * @param title       The title of the event.
     * @param description The description of the event.
     * @param timeFrame   The time frame of the event, represented as a Pair of LocalDatetimes (start and end).
     */
    @Override
    public void addEvent(String title, String description, Pair<LocalDateTime, LocalDateTime> timeFrame) {
        IEvent newEvent = eventFactory.createEvent(title, description, timeFrame.getKey(), timeFrame);
        eventList.add(newEvent);
        items.addItem(newEvent);
        this.isEmpty = false;
        notifyObservers();
    }


    /**
     *
     * @param event is removed from the eventList
     *              if the event is recurring, all events with the same parent ID are also removed
     */
    public void removeEvent(IEvent event){
        eventList.remove(event);
        isEmpty = eventList.isEmpty();
        notifyObservers();
        items.removeItem(event.getID());
//        notifyObservers();
    }

    /**
     * Removes an event with the specified event ID from the event list.
     *
     * @param eventId The ID of the event to be removed.
     */
    public void removeEvent(String eventId){
        eventList.removeIf(ev -> eventId.equals(ev.getID()));
        isEmpty = eventList.isEmpty();
        notifyObservers();
        items.removeItem(eventId);
    }

    /**
     * Retrieves a list of events within the specified calendar week.
     *
     * @param week The CalendarWeek representing the week for which to retrieve events.
     * @return The list of events within the specified week, sorted by date.
     */
    public List<IEvent> getEventList(CalendarWeek week){
        return eventList.stream()
                .filter(week::isEventInThisWeek)
                .sorted(comparing(IEvent::getDateOfEvent))
                .collect(toList());
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
    public void makeRecurring(IEvent event, int frequency, int durationDays){
        event.setRecurrence(true);
        int iterations = durationDays/frequency;
        for (int i = 1; i < iterations+1; i++){
            LocalDateTime oldStart = event.getTimeFrame().getKey();
            LocalDateTime oldEnd = event.getTimeFrame().getValue();
            LocalDateTime newStart = oldStart.plusDays(frequency*i);
            LocalDateTime newEnd = oldEnd.plusDays(frequency*i);
            Pair<LocalDateTime, LocalDateTime> newTimeFrame = new Pair<>(newStart, newEnd);
            IEvent newEvent = eventFactory.createEvent(event.getTitle(), event.getDescription(), newTimeFrame, event);
            eventList.add(newEvent);
        }
        notifyObservers(); // dangerous to notify observers here, since it will notify the observers multiple times
    }
    public void removeRecurring(IEvent event){
        Iterator<IEvent> iterator = eventList.iterator();
        while (iterator.hasNext()) {
            IEvent e = iterator.next();
            if (e.getParentEvent() != null && e.getParentEvent().getID().equals(event.getID())) {
                iterator.remove();
            }
        }
        event.setRecurrence(false);
        notifyObservers();
    }
    public void detachRecurring(IEvent event){
        event.setRecurrence(false);
        event.setParentEvent(null);
        notifyObservers();
    }


    // ----------- getters ----------------
    public List<IEvent> getUpcomingEvents(){
        return EventSorter.getEventsAfterNow(eventList);
    }
    public List<IEvent> getPastEvents(){
        return EventSorter.getEventsBeforeNow(eventList);
    }
    public List<IEvent> getEvents(){
        return eventList;
    }
    public List<IEvent> getEventsBetweenDates(LocalDateTime date1, LocalDateTime date2){
        return EventSorter.getEventsBetweenDates(eventList, date1, date2);
    }
    public List<IEvent> getEventsByTag(String tag){
        return EventSorter.getEventsByTag(eventList, tag);
    }

    public List<String> getTags(){
        return EventSorter.getTags(eventList);
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

    /**
     * Retrieves the event with the specified event ID.
     *
     * @param eventId The ID of the event to retrieve.
     * @return The event with the specified ID, or null if not found.
     */
    public IEvent getEvent(String eventId) {
        return this.eventList.stream().filter(ev -> Objects.equals(ev.getID(), eventId)).findFirst().orElse(null);
    }
}
