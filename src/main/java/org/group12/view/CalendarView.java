package org.group12.view;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.group12.controller.WeekCalendarController;
import org.group12.controllerView.NewEventView;
import org.group12.model.Calendar.CalendarWeek;
import org.group12.model.Calendar.EventData;
import org.group12.model.Calendar.interfaces.IEvent;
import org.group12.util.Globals;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.group12.util.Globals.loadFxml;

/**
 * The CalendarView class represents the view for displaying a calendar.
 */
public class CalendarView {

    private BorderPane calendarPane;

    /**
     * Constructs a CalendarView object with the specified calendar pane.
     *
     * @param calendarPane The BorderPane representing the calendar pane.
     */
    public CalendarView(BorderPane calendarPane) {
        this.calendarPane = calendarPane;

    }

    /**
     * Draws the calendar view for the specified week and event list.
     *
     * @param week              The CalendarWeek representing the week to be displayed.
     * @param eventList         The list of IEvent objects representing the events to be displayed.
     * @param deleteEventAction The action to perform when deleting an event.
     * @param getEventFn        The function to retrieve an event.
     */
    public void drawCalendar(CalendarWeek week , List<IEvent> eventList, Consumer<String> deleteEventAction, Function<String, IEvent> getEventFn) {
        Globals.FxmlUi<GridPane, WeekCalendarController> form = loadFxml("/org/group12/view/weekCalendar.fxml");
        form.getController().initialize(week, eventList, deleteEventAction, getEventFn);
        calendarPane.setCenter(form.getRoot());
    }

    /**
     * Opens the "New Event" form and initializes it with the onSaveAction consumer.
     *
     * @param onSaveAction The action to perform when saving a new event.
     * @throws IOException If an I/O error occurs while opening the form.
     */
    public void onAddEvent(Consumer<EventData> onSaveAction) throws IOException {
        NewEventView controller =  Globals.openNewForm("/org/group12/view/newEvent.fxml", "New Event", false);
        controller._initialize(onSaveAction);
    }

}
