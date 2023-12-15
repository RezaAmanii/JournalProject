package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.group12.controllerView.EventDetailsView;
import org.group12.model.Calendar.CalendarEvent;
import org.group12.model.Calendar.CalendarWeek;
import org.group12.model.Calendar.Event;
import org.group12.model.Calendar.interfaces.IEvent;
import org.group12.util.Globals;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.time.DayOfWeek.*;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class WeekCalendarView {

    public static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("dd");

    public static final int SUNDAY_COL = 1;

    public static final LocalTime FIRST_HR = LocalTime.of(4,0);
    public static final  int FIRST_HR_ROW = 1;


    @FXML
    private Label friDateLbl;

    @FXML
    private Label monDateLbl;

    @FXML
    private Label satDateLbl;

    @FXML
    private Label sunDateLbl;

    @FXML
    private Label thurDateLbl;

    @FXML
    private Label tuesDateLbl;

    @FXML
    private Label wedDateLbl;

    @FXML
    private GridPane calendarGrid;

    Map<DayOfWeek,Label> weekDayDateLbl;

    List<IEvent> events;
    Map<LocalDate,List<IEvent>> eventsPerDay;

    Consumer<String> deleteEventAction;
    Function<String,Event> getEventFn;


    public void initialize(CalendarWeek week, List<IEvent> weekEvents, Consumer<String> deleteEventAction, Function<String, IEvent> getEventFn) {
        this.events = weekEvents;
        this.eventsPerDay = weekEvents
                .stream()
                .collect(
                        groupingBy(event -> event.getTimeFrame().getKey().toLocalDate()));
        this.weekDayDateLbl = createWeekDayDateMapping();

        clearCalendar(weekDayDateLbl);

        week.getDays().forEach( x-> setDateLbl(x,weekDayDateLbl));
        week.getDays().stream().map(x -> getCalendarGridCol(x)).forEach(y->fillWithLightGrey(y));

        eventsPerDay
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(day -> drawDayColumn(day.getKey(), day.getValue(), deleteEventAction, getEventFn));
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

    public void drawDayColumn(LocalDate day, List<IEvent> events, Consumer<String> deleteEventAction, Function<String, IEvent> getEventFn) {
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


    public void addEventToCalendar(CalendarEvent event, int calendarColIndex, Consumer<String> deleteEventAction, Function<String, IEvent> getEventFn) {
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

    private static void openEventDetailsWindow(Consumer<String> deleteEventAction, Function<String, IEvent> getEventFn, MouseEvent mouseEvent) {
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

    public static List<CalendarEvent> toCalendarEvents(List<IEvent> events) {
        return events.stream()
                .map(x -> new CalendarEvent(x,FIRST_HR_ROW,FIRST_HR))
                .sorted(comparing(CalendarEvent::getRowStart))
                .collect(toList());
    }

}