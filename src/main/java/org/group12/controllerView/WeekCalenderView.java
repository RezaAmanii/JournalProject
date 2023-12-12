package org.group12.controllerView;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.group12.model.Calendar.CalendarEvent;
import org.group12.model.Calendar.Event;
import org.group12.model.toDoSubTask.Globals;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.time.DayOfWeek.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class WeekCalenderView implements Initializable {

    public DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("dd");

    public  int SUNDAY_COL = 1;
    public static final  LocalTime FIRST_HR = LocalTime.of(4,0);
    public static final  int FIRST_HR_ROW = 1;


    private Label friDateLbl;


    private Label monDateLbl;


    private Label satDateLbl;


    private Label sunDateLbl;

    private Label thurDateLbl;


    private Label tuesDateLbl;


    private Label wedDateLbl;


    private GridPane calendarGrid;

    public WeekCalenderView(DateTimeFormatter DAY_FORMATTER, int SUNDAY_COL, Label friDateLbl, Label monDateLbl, Label satDateLbl, Label sunDateLbl, Label thurDateLbl, Label tuesDateLbl, Label wedDateLbl, GridPane calendarGrid) {
        this.DAY_FORMATTER = DAY_FORMATTER;
        this.SUNDAY_COL = SUNDAY_COL;
//        this.FIRST_HR = FIRST_HR;
//        this.FIRST_HR_ROW = FIRST_HR_ROW;
        this.friDateLbl = friDateLbl;
        this.monDateLbl = monDateLbl;
        this.satDateLbl = satDateLbl;
        this.sunDateLbl = sunDateLbl;
        this.thurDateLbl = thurDateLbl;
        this.tuesDateLbl = tuesDateLbl;
        this.wedDateLbl = wedDateLbl;
        this.calendarGrid = calendarGrid;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public Map<DayOfWeek, Label> createWeekDayDateMapping() {
        return Map.of(SUNDAY, sunDateLbl,
                MONDAY, monDateLbl,
                TUESDAY, tuesDateLbl,
                WEDNESDAY, wedDateLbl,
                THURSDAY, thurDateLbl,
                FRIDAY, friDateLbl,
                SATURDAY, satDateLbl);
    }


    public void clearCalendar(Map<DayOfWeek,Label> weekDayDateLbl) {
        weekDayDateLbl.values().forEach(lbl -> lbl.setText(""));
        for(int row=1; row < calendarGrid.getRowCount(); row++) {
            for(int col=1; col < calendarGrid.getColumnCount(); col++) {
                var pane = new BorderPane();
                pane.setStyle("-fx-background-color: grey;-fx-border-color: white;-fx-border-width: 0.5px;");
                calendarGrid.add(pane, col, row);
            }
        }
    }

    public void setDateLbl(LocalDate day,Map<DayOfWeek,Label> weekDayDateLbl) {
        var lbl = weekDayDateLbl.get(day.getDayOfWeek());
        lbl.setText(DAY_FORMATTER.format(day));
        if(LocalDate.now().isEqual(day)) {
            lbl.getParent().setStyle("-fx-background-color: yellow");
        }
    }

    public void drawDayColumn(LocalDate day, List<Event> events, Consumer<String> deleteEventAction, Function<String, Event> getEventFn) {
        var col = getCalendarGridCol(day);
        fillWithLightGrey(col);
        toCalendarEvents(events).forEach(ev -> addEventToCalendar(ev, col, deleteEventAction, getEventFn));
    }

    public int getCalendarGridCol(LocalDate day) {
        var weekDay = day.getDayOfWeek();
        return SUNDAY.equals(weekDay) ? SUNDAY_COL : weekDay.getValue() + SUNDAY_COL;
    }

    public void fillWithLightGrey(int col) {
        for(int row=1; row < calendarGrid.getRowCount(); row++) {
            var pane = new BorderPane();
            pane.setStyle("-fx-background-color: #aeb9bf;-fx-border-color: white;-fx-border-width: 0.5px;");
            calendarGrid.add(pane, col, row);
        }
    }


    public void addEventToCalendar(CalendarEvent event, int calendarColIndex, Consumer<String> deleteEventAction, Function<String, Event> getEventFn) {
        var pane = new BorderPane();
        pane.setTop(new Label(event.getTitle()+" : "+ event.getDiscription()));
        pane.setStyle("-fx-background-color: orange;");
        pane.setId(event.getId());
        pane.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                openEventDetailsWindow(deleteEventAction, getEventFn, mouseEvent);
            }
        });
        calendarGrid.add(pane, calendarColIndex, event.getRowStart(), 1, event.getRowEnd() - event.getRowStart() + 1);
    }

    private static void openEventDetailsWindow(Consumer<String> deleteEventAction, Function<String, Event> getEventFn, MouseEvent mouseEvent) {
        try {
            var eventId = ((Parent) mouseEvent.getSource()).getId();
            var eventData = getEventFn.apply(eventId);
            if(eventData == null) {
                return;
            }
            EventDetailsView controller = Globals.openNewForm("/org/group12/view/eventDetails.fxml", "Event", false);
            controller._initialize(eventData, deleteEventAction);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CalendarEvent> toCalendarEvents(List<Event> events) {
        return events.stream()
                .map(x -> new CalendarEvent(x,FIRST_HR_ROW,FIRST_HR))
                .sorted(comparing(CalendarEvent::getRowStart))
                .collect(toList());
    }
}
