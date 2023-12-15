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

import static java.util.Comparator.comparing;

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
    private BorderPane mainWindowBorder;

    @FXML
    private Label monthLBL;

    @FXML
    private Label nameLBL11;

    @FXML
    private Label yearLBL;

    private SimpleObjectProperty<CalendarWeek> calendarWeek;

    public void initialize() {
        calendar = SaveLoad.getInstance().getContainerInstance().getCalender();
        calendarView = new CalendarView(calendarPane);
        calendarWeek = new SimpleObjectProperty<>();
        setBindings();
        calendarWeek.set(CalendarWeek.currentWeek());
    }


    private void setBindings() {
        calendarWeek.addListener((observable, oldValue, newValue) -> calendarView.drawCalendar(calendarWeek.getValue(), calendar.getEventList(calendarWeek.getValue()),this::deleteEvent,this::getEvent));
        monthLBL.textProperty().bind(calendarWeek.map(CalendarWeek::getMonth));
        yearLBL.textProperty().bind(calendarWeek.map(CalendarWeek::getYear));
    }

    @FXML
    void backOneWeek(MouseEvent event) {
        calendarWeek.set(calendarWeek.getValue().previousWeek());
    }

    @FXML
    void forwardOneWeek(MouseEvent event) {
        calendarWeek.set(calendarWeek.getValue().nextWeek());
    }

    @FXML
    void onAddEvent(MouseEvent msEvent) throws IOException {
        calendarView.onAddEvent(this::addNewEvent);
    }

    private void addNewEvent(EventData ev) {
        calendar.addEvent(ev.getTitle(), ev.getDescription(), ev.getFrom(), new Pair<>(ev.getFrom(), ev.getTo()));
        calendarView.drawCalendar(calendarWeek.getValue(), calendar.getEventList(calendarWeek.getValue()),this::deleteEvent,this::getEvent);
    }

    private void deleteEvent(String eventId) {
        calendar.removeEvent(eventId);
        calendarView.drawCalendar(calendarWeek.getValue(), calendar.getEventList(calendarWeek.getValue()),this::deleteEvent,this::getEvent);
    }

    private IEvent getEvent(String eventId) {
        return calendar.getEvent(eventId);
    }

}