package org.group12.view;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
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

public class CalendarView {

    private BorderPane calendarPane;
    public CalendarView(BorderPane calendarPane) {
        this.calendarPane = calendarPane;

    }

    public void drawCalendar(CalendarWeek week , List<IEvent> eventList, Consumer<String> deleteEventAction, Function<String, IEvent> getEventFn) {
        Globals.FxmlUi<GridPane, WeekCalendarController> form = loadFxml("/org/group12/view/weekCalendar.fxml");
        form.getController().initialize(week, eventList, deleteEventAction, getEventFn);
        calendarPane.setCenter(form.getRoot());
    }

    public void onAddEvent(Consumer<EventData> onSaveAction) throws IOException {
        NewEventView controller =  Globals.openNewForm("/org/group12/view/newEvent.fxml", "New Event", false);
        controller._initialize(onSaveAction);
    }

}
