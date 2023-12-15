package org.group12.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import org.group12.model.Calendar.CalendarWeek;
import org.group12.model.Calendar.Calendar;
import org.group12.model.Calendar.EventData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.Calendar.interfaces.IEvent;
import org.group12.view.CalendarView;

import java.io.IOException;


/**
 * This class is a controller for the Calendar Page.
 * It implements the Initializable interface.
 */
public class CalendarPageController {

    private Calendar calendar;

    private CalendarView calendarView;

    @FXML
    private BorderPane calendarPane;

    @FXML
    private Label monthLBL;

    @FXML
    private Label yearLBL;

    private SimpleObjectProperty<CalendarWeek> calendarWeek;

    /**
     * Initializes the CalendarPageController.
     */
    public void initialize() {
        calendar = SaveLoad.getInstance().getContainerInstance().getCalender();
        calendarView = new CalendarView(calendarPane);
        calendarWeek = new SimpleObjectProperty<>();
        setBindings();
        calendarWeek.set(CalendarWeek.currentWeek());
    }


    /**
     * Sets the necessary bindings for the calendar view.
     */
    private void setBindings() {
        calendarWeek.addListener((observable, oldValue, newValue) -> calendarView.drawCalendar(calendarWeek.getValue(), calendar.getEventList(calendarWeek.getValue()),this::deleteEvent,this::getEvent));
        monthLBL.textProperty().bind(calendarWeek.map(CalendarWeek::getMonth));
        yearLBL.textProperty().bind(calendarWeek.map(CalendarWeek::getYear));
    }

    /**
     * Handles the event when the user clicks on the back arrow to go to the previous week.
     *
     * @param event The mouse event triggering the action.
     */
    @FXML
    void backOneWeek(MouseEvent event) {
        calendarWeek.set(calendarWeek.getValue().previousWeek());
    }

    /**
     * Handles the event when the user clicks on the forward arrow to go to the next week.
     *
     * @param event The mouse event triggering the action.
     */
    @FXML
    void forwardOneWeek(MouseEvent event) {
        calendarWeek.set(calendarWeek.getValue().nextWeek());
    }

    /**
     * Handles the event when the user clicks on the "Add Event" button.
     *
     * @param msEvent The mouse event triggering the action.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void onAddEvent(MouseEvent msEvent) throws IOException {
        calendarView.onAddEvent(this::addNewEvent);
    }

    /**
     * Adds a new event to the calendar.
     *
     * @param ev The event data containing the details of the new event.
     */
    private void addNewEvent(EventData ev) {
        calendar.addEvent(ev.getTitle(), ev.getDescription(), ev.getFrom(), new Pair<>(ev.getFrom(), ev.getTo()));
        calendarView.drawCalendar(calendarWeek.getValue(), calendar.getEventList(calendarWeek.getValue()),this::deleteEvent,this::getEvent);
    }

    /**
     * Deletes an event from the calendar.
     *
     * @param eventId The ID of the event to be deleted.
     */
    private void deleteEvent(String eventId) {
        calendar.removeEvent(eventId);
        calendarView.drawCalendar(calendarWeek.getValue(), calendar.getEventList(calendarWeek.getValue()),this::deleteEvent,this::getEvent);
    }

    /**
     * Retrieves an event from the calendar based on its ID.
     *
     * @param eventId The ID of the event to be retrieved.
     * @return The event with the specified ID.
     */
    private IEvent getEvent(String eventId) {
        return calendar.getEvent(eventId);
    }

}