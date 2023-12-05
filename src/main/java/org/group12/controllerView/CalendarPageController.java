package org.group12.controllerView;

import org.group12.model.homeCalendar.CalendarActivity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * This class is a controller for the Calendar Page.
 * It implements the Initializable interface.
 */
public class CalendarPageController implements Initializable {
    public Label monthLBL;
    public  Label yearLBL;
    public GridPane calendarPane;

    public ZonedDateTime dateFocus;
    public  ZonedDateTime today;
    public  int selectedDay;


    /**
     * Initializes the CalendarPageController.
     * It sets the initial values for dateFocus, today, selectedDay, and draws the calendar.
     * @param location The URL location.
     * @param resources The ResourceBundle resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        today = ZonedDateTime.now();
        selectedDay = today.getDayOfMonth();
        dateFocus = ZonedDateTime.now();
        drawCalendar();
    }


    /**
     * Sets the month to January and redraws the calendar.
     */
    public void january(){
        dateFocus=dateFocus.withMonth(1);
        drawCalendar();
    }
    @FXML
    void feb(){
        dateFocus=dateFocus.withMonth(2);
        drawCalendar();
    }
    @FXML
    void mar(){
        dateFocus=dateFocus.withMonth(3);
        drawCalendar();
    }
    @FXML
    void apr(){
        dateFocus=dateFocus.withMonth(4);
        drawCalendar();
    }@FXML
    void may(){
        dateFocus=dateFocus.withMonth(5);
        drawCalendar();
    }
    @FXML
    void june(){
        dateFocus=dateFocus.withMonth(6);
        drawCalendar();
    }
    @FXML
    void july(){
        dateFocus=dateFocus.withMonth(7);
        drawCalendar();
    }@FXML
    void aug(){dateFocus=dateFocus.withMonth(8);
        drawCalendar();

    }
    @FXML
    void sep(){
        dateFocus=dateFocus.withMonth(9);
        drawCalendar();
    }
    @FXML
    void oct(){
        dateFocus=dateFocus.withMonth(10);
        drawCalendar();
    }
    @FXML
    void nov(){
        dateFocus=dateFocus.withMonth(11);
        drawCalendar();
    }
    @FXML
    void dec(){
        dateFocus=dateFocus.withMonth(12);
        drawCalendar();
    }

    @FXML
    void backOneMonth() {
        dateFocus = dateFocus.minusMonths(1);
//        dateFocus=dateFocus.minusDays(7);
        drawCalendar();
    }

    @FXML
    void forwardOneMonth() {
        dateFocus = dateFocus.plusMonths(1);
        drawCalendar();
    }

    /**
     * Draws the calendar by creating and arranging the necessary UI elements based on the current date focus.
     */
    public  void drawCalendar() {
        calendarPane.getChildren().clear();
        yearLBL.setText(String.valueOf(dateFocus.getYear()));
        monthLBL.setText(String.valueOf(dateFocus.getMonth()));

    }

    /**
     * Creates calendar activity UI elements and adds them to the provided stack pane.
     *
     * @param currCalendarActivities The list of calendar activities for a specific date.
     * @param stackPane              The stack pane where the calendar activities will be added.
     */
    private  void createCalendarActivity(List<CalendarActivity> currCalendarActivities, VBox stackPane) {

    }


    /**
     * Creates a map of calendar activities grouped by day of the month.
     *
     * @param calendarActivities The calendar.
     * @return A map of calendar activities grouped by day of the month.
     */
    private  Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        return calendarActivityMap;
    }


    // Map to store calendar activities
    Map<ZonedDateTime,ArrayList<CalendarActivity>>calendarActivities=new HashMap<>();

    /**
     * Adds a new activity for the selected day.
     */
    public void addNewDayActivity() {
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

    }


    /**
     * Retrieves the calendar activities for the specified month.
     *
     * @param dateFocus The date focus representing the month.
     * @return A map of calendar activities grouped by day of the month.
     */
    private  Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<CalendarActivity> currMonthActivities = new ArrayList<>();
        for (Map.Entry<ZonedDateTime,ArrayList<CalendarActivity>>entry:calendarActivities.entrySet()){
            if (entry.getKey().getMonth()==dateFocus.getMonth()){
                currMonthActivities.addAll(entry.getValue());
            }
        }
        return createCalendarMap(currMonthActivities);
    }
}

