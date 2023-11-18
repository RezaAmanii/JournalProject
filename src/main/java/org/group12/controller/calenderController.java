package org.group12.controller;


import org.group12.Listeners.CalenderListener;
import org.group12.model.Calendar.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class calenderController {

    private List<Event> events;

    public List<CalenderListener> listeners;


    public calenderController() {
        this.events = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    public void addEvent(Event event){
        events.add(event);

        for(CalenderListener listener : this.listeners){
            listener.notifyCalenderEventAdded(event);
        }
    }

    public void removeEvent(Event event){
        events.remove(event);

        for(CalenderListener listener : listeners){
            listener.notifyCalenderEventRemoved(event);
        }
    }


    public void updateEvent(Event event){
        // Not implemented yet, waiting for Event class to implements its attribute setters

        for(CalenderListener listener : listeners){
            listener.notifyCalenderEventUpdated(event);
        }
    }

    public void setRepeatPattern(){

    }

    public List<Event> getUppcommingEvents(){
        List<Event> uppcomingEvents = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();

        for(Event event : this.events){
            if(event.getDateOfEvent().isAfter(currentDateTime)){
                uppcomingEvents.add(event);
            }
        }
        return uppcomingEvents;

    }

    public List<Event> getPastEvents(){
        List<Event> pastEvents = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();

        for(Event event : events){
            LocalDateTime eventDateTime = event.getDateCreated();
            if(eventDateTime.isBefore(currentDateTime)){
                pastEvents.add(event);
            }
        }
        return pastEvents;

    }


}
