package org.group12.controller;

import org.group12.model.Calendar.Calendar;
import org.group12.model.Calendar.Event;
import org.group12.model.INameable;
import org.group12.view.CalendarView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public class CalendarController implements IController {

    private Calendar calenderModel;
    private CalendarView calenderView;
    private HashMap<String, INameable> itemMap;


    public CalendarController(Calendar calenderModel, HashMap<String, INameable> itemMap){
        this.calenderModel = calenderModel;
        this.itemMap = itemMap;
        //calenderModel.addObserver(calenderView);
    }

    /**
     * Handles the addition of an event to the calendar.
     * Validates the event details and adds it to the calendar model.
     *
     * @param event The event object containing details to be added.
     */
    private void addEventHandler(Event event){
        try{
            if(validateEvent(event)){
                LocalDateTime startOfEvent = event.getTimeFrame().getKey();
                LocalDateTime endOfEvent = event.getTimeFrame().getValue();

                if(validateEventTiming(startOfEvent, endOfEvent)){
                    calenderModel.addEvent(event.getTitle(), event.getDescription(), event.getDateOfEvent(), event.getTimeFrame());
                } else {
                    //calenderView.displayErrorMessage("Invalid time frame, start or duration is invalid.");
                }
            } else{
                //calenderView.displayErrorMessage("Invalid input: Title, date, or time frame.");
            }
        } catch (IllegalArgumentException error){
            //calenderView.displayErrorMessage(error.getMessage());

        } catch (Exception commonError){
            //calenderView.displayErrorMessage(commonError.getMessage());
        }
    }

    /**
     * Removes an event from the calendar model.
     *
     * @param event The event to be removed.
     */
    public void removeEventHandler(Event event){
        calenderModel.removeEvent(event);
        //calenderView.displayErrorMessage(event.getTitle() + " was successfully removed.");
    }

    /**
     * Updates an existing event in the calendar model.
     *
     * @param event The event to be updated.
     */
    public void updateEvent(Event event){

        //calenderView.displayErrorMessage(event.getTitle() + " was successfully updated");
    }

    /**
     * Retrieves the recurrence information of an event.
     *
     * @param event The event to retrieve the recurrence information from.
     */
    public void recurrenceHandler(Event event){
        event.getRecurrence();
    }


    /**
     * Retrieves a list of upcoming events from the calendar model.
     *
     * @return A list of upcoming events.
     */
    public List<Event> getUpcomingEvents(){
        return calenderModel.getUpcomingEvents();
    }


    /**
     * Retrieves a list of past events from the calendar model.
     *
     * @return A list of past events.
     */
    public List<Event> getPastEvents(){
        return calenderModel.getPastEvents();
    }

    /**
     * Retrieves a list of all events from the calendar model.
     *
     * @return A list of all events.
     */
    public List<Event> getAllEvents(){
        return calenderModel.getEvents();
    }


    /**
     * Validates the timing of an event.
     *
     * @param startOfEvent The start time of the event.
     * @param endOfEvent   The end time of the event.
     * @return True if the event timing is valid, false otherwise.
     */
    private boolean validateEventTiming(LocalDateTime startOfEvent, LocalDateTime endOfEvent) {
        if(startOfEvent.isAfter(endOfEvent) || startOfEvent.plusHours(1).isAfter(endOfEvent)){
            return false;
        }
        return true;
    }


    /**
     * Validates the event details.
     *
     * @param event The event object to be validated.
     * @return True if the event details are valid, false otherwise.
     */
    private boolean validateEvent(Event event) {
        if(event == null || event.getTitle() == null || event.getTitle().isEmpty() || event.getDateOfEvent() == null
                || event.getTimeFrame() == null || event.getDateOfEvent().isBefore(LocalDateTime.now())){
            return false;
        }
        return true;
    }


    public void handleButtonClick(){}





}
