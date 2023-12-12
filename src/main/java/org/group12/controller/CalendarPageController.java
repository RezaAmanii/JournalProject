package org.group12.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import org.group12.model.Calendar.CalendarWeek;
import org.group12.controllerView.NewEventView;
import org.group12.model.Calendar.Calendar;
import org.group12.model.Calendar.Event;
import org.group12.model.Calendar.EventData;
import org.group12.model.Container;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.group12.model.toDoSubTask.Globals;

import java.io.IOException;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.group12.model.toDoSubTask.Globals.loadFxml;

/**
 * This class is a controller for the Calendar Page.
 * It implements the Initializable interface.
 */
public class CalendarPageController {

    private Calendar calendarData = Container.getInstance().getCalender();

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
        calendarWeek = new SimpleObjectProperty<>();

        setBindings();

        calendarWeek.set(CalendarWeek.currentWeek());
    }


    private void setBindings() {

        calendarWeek.addListener((observable, oldValue, newValue) -> drawCalendar(newValue));

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
        NewEventView controller =  Globals.openNewForm("/org/group12/view/newEvent.fxml", "New Event", false);
        controller._initialize(this::addNewEvent);
    }

    private void addNewEvent(EventData ev) {
        calendarData.addEvent(ev.getTitle(), ev.getDescription(), ev.getFrom(), new Pair<>(ev.getFrom(), ev.getTo()));
        drawCalendar(calendarWeek.getValue());
    }

    private void deleteEvent(String eventId) {
        calendarData.removeEvent(eventId);
        drawCalendar(calendarWeek.getValue());
    }

    private Event getEvent(String eventId) {
        return calendarData.getEvent(eventId);
    }

    void drawCalendar(CalendarWeek week) {
        Globals.FxmlUi<GridPane, WeekCalendarController> form = loadFxml("/org/group12/view/weekCalendar.fxml");
        form.getController().initialize(week, getWeekEvents(week), this::deleteEvent, this::getEvent);
        calendarPane.setCenter(form.getRoot());
    }

    private List<Event> getWeekEvents(CalendarWeek week) {
        return calendarData.getEvents().stream()
                .filter(week::isEventInThisWeek)
                .sorted(comparing(Event::getDateOfEvent))
                .collect(toList());
    }

}