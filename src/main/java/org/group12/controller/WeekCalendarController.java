package org.group12.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.group12.model.Calendar.CalendarWeek;
import org.group12.controllerView.WeekCalenderView;
import org.group12.model.Calendar.interfaces.IEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

/**
 * This class is a controller for the Week Calendar.
 * It handles the logic and interaction with the Week Calendar view.
 */
public class WeekCalendarController {

    private WeekCalenderView weekCalenderView;
    public static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("dd");

    public static final int SUNDAY_COL = 1;

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


    /**
     * Retrieves the instance of WeekCalenderView.
     *
     * @return The instance of WeekCalenderView.
     */
    public WeekCalenderView getViewInstance()
    {
        if(weekCalenderView == null)
        {
            return new WeekCalenderView(DAY_FORMATTER,  SUNDAY_COL,  friDateLbl,  monDateLbl,  satDateLbl,  sunDateLbl,  thurDateLbl,  tuesDateLbl,  wedDateLbl,  calendarGrid) ;
        }else
            return   this.weekCalenderView;
    }

    /**
     * Initializes the Week Calendar controller.
     *
     * @param week The calendar week.
     * @param weekEvents The events for the week.
     * @param deleteEventAction The action to delete an event.
     * @param getEventFn The function to retrieve an event.
     */
    public void initialize(CalendarWeek week, List<IEvent> weekEvents, Consumer<String> deleteEventAction, Function<String, IEvent> getEventFn) {
        weekCalenderView = getViewInstance();
        this.events = weekEvents;
        this.eventsPerDay = weekEvents
                .stream()
                .collect(
                        groupingBy(event -> event.getTimeFrame().getKey().toLocalDate()));
        this.weekDayDateLbl = weekCalenderView.createWeekDayDateMapping();

        weekCalenderView.clearCalendar(weekDayDateLbl);

        week.getDays().forEach( x-> weekCalenderView.setDateLbl(x,weekDayDateLbl));
        week.getDays().stream().map(x -> weekCalenderView.getCalendarGridCol(x)).forEach(y->weekCalenderView.fillWithLightGrey(y));

        eventsPerDay
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(day -> weekCalenderView.drawDayColumn(day.getKey(), day.getValue(), deleteEventAction, getEventFn));
    }


}