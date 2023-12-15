package org.group12.controllerView;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.group12.model.Calendar.CalendarEvent;
import org.group12.model.Calendar.interfaces.IEvent;
import org.group12.util.Globals;

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

/**
 * This class represents the Week Calendar view.
 * It displays a weekly calendar with events for each day of the week.
 */
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

    /**
     * Constructs a WeekCalenderView object with the specified parameters.
     *
     * @param DAY_FORMATTER The formatter for displaying day numbers.
     * @param SUNDAY_COL    The column index for Sunday in the calendar grid.
     * @param friDateLbl    The label for displaying the date of Friday.
     * @param monDateLbl    The label for displaying the date of Monday.
     * @param satDateLbl    The label for displaying the date of Saturday.
     * @param sunDateLbl    The label for displaying the date of Sunday.
     * @param thurDateLbl   The label for displaying the date of Thursday.
     * @param tuesDateLbl   The label for displaying the date of Tuesday.
     * @param wedDateLbl    The label for displaying the date of Wednesday.
     * @param calendarGrid  The grid pane representing the calendar.
     */
    public WeekCalenderView(DateTimeFormatter DAY_FORMATTER, int SUNDAY_COL, Label friDateLbl, Label monDateLbl, Label satDateLbl, Label sunDateLbl, Label thurDateLbl, Label tuesDateLbl, Label wedDateLbl, GridPane calendarGrid) {
        this.DAY_FORMATTER = DAY_FORMATTER;
        this.SUNDAY_COL = SUNDAY_COL;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {}


    /**
     * Creates a mapping between each day of the week and its corresponding date label.
     *
     * @return The map containing the day-date label mappings.
     */
    public Map<DayOfWeek, Label> createWeekDayDateMapping() {
        return Map.of(SUNDAY, sunDateLbl,
                MONDAY, monDateLbl,
                TUESDAY, tuesDateLbl,
                WEDNESDAY, wedDateLbl,
                THURSDAY, thurDateLbl,
                FRIDAY, friDateLbl,
                SATURDAY, satDateLbl);
    }


    /**
     * Clears the calendar grid and resets the date labels.
     *
     * @param weekDayDateLbl The map containing the day-date label mappings.
     */
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

    /**
     * Sets the date label for the specified day in the week.
     *
     * @param day            The date for which to set the label.
     * @param weekDayDateLbl The map containing the day-date label mappings.
     */
    public void setDateLbl(LocalDate day,Map<DayOfWeek,Label> weekDayDateLbl) {
        var lbl = weekDayDateLbl.get(day.getDayOfWeek());
        lbl.setText(DAY_FORMATTER.format(day));
        if(LocalDate.now().isEqual(day)) {
            lbl.getParent().setStyle("-fx-background-color: yellow");
        }
    }


    /**
     * Draws the column for a specific day in the calendar grid and fills it with events.
     *
     * @param day              The day for which to draw the column.
     * @param events           The list of events for the day.
     * @param deleteEventAction The action to perform when deleting an event.
     * @param getEventFn       The function to retrieve an event.
     */
    public void drawDayColumn(LocalDate day, List<IEvent> events, Consumer<String> deleteEventAction, Function<String, IEvent> getEventFn) {
        var col = getCalendarGridCol(day);
        fillWithLightGrey(col);
        toCalendarEvents(events).forEach(ev -> addEventToCalendar(ev, col, deleteEventAction, getEventFn));
    }


    /**
     * Retrieves the column index in the calendar grid for the specified day.
     *
     * @param day The day for which to retrieve the column index.
     * @return The column index in the calendar grid.
     */
    public int getCalendarGridCol(LocalDate day) {
        var weekDay = day.getDayOfWeek();
        return SUNDAY.equals(weekDay) ? SUNDAY_COL : weekDay.getValue() + SUNDAY_COL;
    }


    /**
     * Fills the specified column in the calendar grid with a light grey background.
     *
     * @param col The column index in the calendar grid.
     */
    public void fillWithLightGrey(int col) {
        for(int row=1; row < calendarGrid.getRowCount(); row++) {
            var pane = new BorderPane();
            pane.setStyle("-fx-background-color: #aeb9bf;-fx-border-color: white;-fx-border-width: 0.5px;");
            calendarGrid.add(pane, col, row);
        }
    }


    /**
     * Adds an event to the calendar grid.
     *
     * @param event             The event to add.
     * @param calendarColIndex  The column index in the calendar grid.
     * @param deleteEventAction The action to perform when deleting the event.
     * @param getEventFn        The function to retrieve an event.
     */
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


    /**
     * Opens the event details window for the selected event.
     *
     * @param deleteEventAction The action to perform when deleting the event.
     * @param getEventFn        The function to retrieve an event.
     * @param mouseEvent        The MouseEvent that triggered the event details window.
     */
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


    /**
     * Converts a list of generic events to a list of calendar events.
     *
     * @param events The list of events to convert.
     * @return The list of calendar events.
     */
    public static List<CalendarEvent> toCalendarEvents(List<IEvent> events) {
        return events.stream()
                .map(x -> new CalendarEvent(x,FIRST_HR_ROW,FIRST_HR))
                .sorted(comparing(CalendarEvent::getRowStart))
                .collect(toList());
    }
}
