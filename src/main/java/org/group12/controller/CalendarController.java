package org.group12.controller;

import javafx.util.Pair;
import org.group12.model.Calendar.Calendar;
import org.group12.view.CalendarView;

import java.time.LocalDateTime;


public class CalendarController implements IController {

    private Calendar calenderModel;
    private CalendarView calenderView;

    public CalendarController(){

    }

    public CalendarController(Calendar calenderModel, CalendarView calenderView){
        this.calenderModel = calenderModel;
        this.calenderView = calenderView;
        //calenderModel.addObserver(calenderView);
    }

    private void addEvent(String title, String description, LocalDateTime dateOfEvent, Pair<LocalDateTime, LocalDateTime> timeFrame){

        try{
            // Checks if title, date and time frame is not null or empty
            if(title == null || title.isEmpty() || dateOfEvent == null || timeFrame == null){
                throw new IllegalArgumentException("Invalid input: Title, date, or time frame is empty or null.");
            }

            // Checks if date of event is in the future
            if(dateOfEvent.isBefore(LocalDateTime.now())){
                throw new IllegalArgumentException("Invalid input: Event must be in the future.");
            }

            LocalDateTime startOfEvent = timeFrame.getKey();
            LocalDateTime endOfEvent = timeFrame.getValue();

            // Checks if start of event is before end of event
            if(startOfEvent.isAfter(endOfEvent)){
                throw new IllegalArgumentException("Invalid time frame : Start ");
            }

            // Checks if event is at least 1 hour long
            if(startOfEvent.plusHours(1).isAfter(endOfEvent)){
                throw new IllegalArgumentException("Invalid time frame : Event duration must be at least 1 hour long.");
            }

            calenderModel.addEvent(title, description, dateOfEvent, timeFrame);

            //Event newEvent = calenderModel.eventFactory.createEvent(title, description, dateOfEvent, timeFrame);

            // Notify observer to update view
            //calenderModel.notifyObservers(newEvent);

            // Catches exceptions for adding method
        } catch (IllegalArgumentException error){
            System.out.println("Error occurred while adding event : " + error.getMessage());

            // Catches other types of exceptions which are not IllegalArgumentExceptions
        } catch (Exception commonError){
            System.out.println("Error occurred : " + commonError.getMessage());
        }

    }


    public void handleButtonClick(){}





}
