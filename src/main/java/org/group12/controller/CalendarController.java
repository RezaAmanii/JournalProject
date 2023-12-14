package org.group12.controller;

import org.group12.dataHandler.SaveLoad;
import org.group12.model.Calendar.Calendar;
import org.group12.model.Calendar.Event;
import org.group12.model.Container;
import org.group12.model.INameable;
import org.group12.model.Items;
import org.group12.view.CalendarView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import org.group12.model.Items;

public class CalendarController implements IController {

    private Calendar calenderModel;
    private Container container;

    private CalendarView calenderView;
//    private HashMap<String, INameable> itemMap;
    private Items itemMap;


    public CalendarController(){
        this.container = SaveLoad.getInstance().getContainerInstance();
        this.itemMap = SaveLoad.getInstance().getItemsInstance();
        this.calenderModel = container.getCalender();

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
                    calenderModel.addEvent(event.getTitle(), event.getDescription(), event.getTimeFrame());
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
     * Updates an event in the calendar model.
     *
     * @param event The event to be updated.
     */
    public void updateEvent(Event event){
        Event oldEvent = (Event) itemMap.getItem(event.getID());

        if(oldEvent != null){
            if(event.getTitle() != null){
                //oldEvent.editTitle(event.getTitle());
                //calenderView.displayErrorMessage(event.getTitle() + " was successfully updated");
                //calenderView.update();
            }
            if(event.getDescription() != null){
                //oldEvent.editDescription(event.getDescription());
                //calenderView.displayErrorMessage(event.getDescription() + " was successfully updated");
                //clenderView.update();
            }
            if(event.getDateOfEvent() != null){
                //oldEvent.editDateOfEvent(event.getDateOfEvent());
                //calenderView.displayErrorMessage(event.getDateOfEvent() + " was successfully updated");
                //calenderView.update();
            }
            if(event.getTimeFrame() != null){
                //oldEvent.editTimeFrame(event.getTimeFrame());
                //calenderView.displayErrorMessage(event.getTimeFrame() + " was successfully updated");
                //calenderView.update();
            }

            //oldEvent.editRecurrence(event.getRecurrence());
            //calenderView.displayErrorMessage(event.getRecurrence() + " was successfully updated");
            //calenderView.update();

        } else {
            //calenderView.displayErrorMessage("Event does not exist.");
        }

    }

    public void sortEvent(){
        calenderModel.sortEvents();
        //calenderView.update();
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
     * Retrieves the details of an event.
     *
     * @param event The event to retrieve the details from.
     */
    /*
    public void executeCommandHandler(String string, Command command){
        command.execute(string);
    }

     */


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
     * Retrieves a list of events between two dates from the calendar model.
     *
     * @param from The start date.
     * @param to   The end date.
     * @return A list of events between the two dates.
     */
    public List<Event> getEventsBetweenDates(LocalDateTime from, LocalDateTime to){
        if(from == null || to == null){
            //calenderView.displayErrorMessage("Invalid date range.");
        }
        //return calenderModel.getEventsBetweenDates(from, to);
        return null;
    }

    /**
     * Retrieves an event by its id.
     *
     * @param id The id of the event.
     * @return The event object.
     */
    public Event getEventById(String id){
        if(id == null || id.isEmpty()){
            //calenderView.displayErrorMessage("Invalid id.");
        }
        return (Event) itemMap.getItem(id);
    }

    /**
     * Retrieves an event by its tag.
     *
     * @param tag The tag of the event.
     * @return The event object.
     */
    public Event getEventByTag(String tag){
        if(tag == null || tag.isEmpty()){
            //calenderView.displayErrorMessage("Invalid tag.");
        }
        //calenderModel.getEventByTag(tag);
        return null;
    }

    /**
     * Retrieves a list of tags from the calendar model.
     *
     * @return A list of tags.
     */
    public List<String> getTags(){
        //if(calenderModel.getTags() == null){
            //calenderView.displayErrorMessage("No tags found.");
        //}

        //return calenderModel.getTags();
        //calenderView.update();

        return null;
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
