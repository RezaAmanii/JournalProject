package org.group12.controllerView;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.group12.model.homeCalendar.CalendarActivity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.time.LocalDateTime;
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
//    public  void drawCalendar() {
//        calendarPane.getChildren().clear();
//        yearLBL.setText(String.valueOf(dateFocus.getYear()));
//        monthLBL.setText(String.valueOf(dateFocus.getMonth()));
//
//
//        //List of activities for a given month
//        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);
//
//        int monthMaxDate = dateFocus.getMonth().maxLength();
//        //Check for leap year
//        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
//            monthMaxDate = 28;
//        }
//        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();
//
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 7; j++) {
//                VBox vDay = new VBox();
//                int calculatedDate = (j + 1) + (7 * i);
//                int currentDate = calculatedDate - dateOffset;
//                Label date = new Label(String.valueOf(currentDate));
//                date.setPadding(new Insets(5, 5, 5, 5));
//                date.setFont(Font.font("Bodoni MT Black", 17));
//                boolean valid = false;
//
//                if (calculatedDate > dateOffset) {
//                    if (currentDate <= monthMaxDate) {
//                        valid = true;
//                        vDay.setStyle("-fx-background-color: #e3f6f5; -fx-background-radius: 10;-fx-border-radius: 10; -fx-border-color: #ffffff");
//                        vDay.setPadding(new Insets(1,1,0,1));
//                        GridPane.setMargin(vDay, new Insets(4));
////                        vDay.getChildren().add(date);
//
//                    }
//
//                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
//                        vDay.setStyle("-fx-background-color: #213742; -fx-background-radius: 10;-fx-border-radius: 10; -fx-border-color: #ffffff");
//                        date.setStyle("-fx-text-fill: #ffffff;");
//                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
////                        dayDeadlines.getChildren().clear();
//                        //day label
//                        Label todayLBL = new Label(currentDate + " " + monthLBL.getText() + " " + yearLBL.getText());
//                        todayLBL.setPadding(new Insets(5, 5, 5, 5));
//                        todayLBL.setFont(Font.font("Bodoni MT Black", 17));
//                        todayLBL.setStyle("-fx-text-fill:  #183a4e");
//                    }
//                    if (valid) {
//                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
//                        vDay.getChildren().add(date);
//                        if (calendarActivities != null) {
//                            VBox notification = new VBox();
//                            notification.setPadding(new Insets(5,5,3,5));
//                            notification.setStyle("-fx-background-color: #081e2a; -fx-background-radius: 10;");
//                            VBox.setMargin(notification, new Insets(2));
//                            vDay.getChildren().add(notification);
//                        }
//                        vDay.setOnMouseClicked(mouseEvent -> {
//                            selectedDay = currentDate;
//                            Label todayLBL = new Label(currentDate + " " + monthLBL.getText() + " " + yearLBL.getText());
//                            todayLBL.setPadding(new Insets(5, 5, 5, 5));
//                            todayLBL.setFont(Font.font("Bodoni MT Black", 17));
//                            todayLBL.setStyle("-fx-text-fill:  #183a4e");
//                        });
//                        calendarPane.add(vDay, j, i);
//
//                    }
//                }
//            }
//
//        }
//    }
    public void drawCalendar() {
        clearCalendar();
        displayYearAndMonth();
        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);
        populateCalendar(calendarActivityMap);
    }

    private void clearCalendar() {
        calendarPane.getChildren().clear();
    }

    private void displayYearAndMonth() {
        yearLBL.setText(String.valueOf(dateFocus.getYear()));
        monthLBL.setText(String.valueOf(dateFocus.getMonth()));
    }

    private void populateCalendar(Map<Integer, List<CalendarActivity>> calendarActivityMap) {
        int monthMaxDate = dateFocus.getMonth().length(dateFocus.toLocalDate().isLeapYear());
        int dateOffset = LocalDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                int calculatedDate = (j + 1) + (7 * i);
                int currentDate = calculatedDate - dateOffset;
                if (isValidDate(calculatedDate, dateOffset, currentDate, monthMaxDate)) {
                    VBox dayBox = createDayBox(currentDate, calendarActivityMap);
                    calendarPane.add(dayBox, j, i);
                }
            }
        }
    }

    private boolean isValidDate(int calculatedDate, int dateOffset, int currentDate, int monthMaxDate) {
        return calculatedDate > dateOffset && currentDate <= monthMaxDate;
    }

    private VBox createDayBox(int currentDate, Map<Integer, List<CalendarActivity>> calendarActivityMap) {
        VBox dayBox = new VBox();
        dayBox.setStyle("-fx-background-color: #e3f6f5; -fx-background-radius: 10;-fx-border-radius: 10; -fx-border-color: #ffffff");
        dayBox.setPadding(new Insets(1,1,0,1));
        GridPane.setMargin(dayBox, new Insets(4));

        Label dateLabel = createDateLabel(currentDate);
        dayBox.getChildren().add(dateLabel);

        if (calendarActivityMap.get(currentDate) != null) {
            VBox notification = createNotification();
            dayBox.getChildren().add(notification);
        }

        dayBox.setOnMouseClicked(mouseEvent -> handleDayClick(currentDate));
        return dayBox;
    }



    private Label createDateLabel(int currentDate) {
        Label dateLabel = new Label(String.valueOf(currentDate));
        dateLabel.setPadding(new Insets(5, 5, 5, 5));
        dateLabel.setFont(Font.font("Bodoni MT Black", 17));
        return dateLabel;
    }

    private VBox createNotification() {
        VBox notification = new VBox();
        notification.setPadding(new Insets(5,5,3,5));
        notification.setStyle("-fx-background-color: #081e2a; -fx-background-radius: 10;");
        VBox.setMargin(notification, new Insets(2));

        // Add event title and time
        Label eventTitle = new Label("Event Title");
        Label eventTime = new Label("Event Time");
        notification.getChildren().addAll(eventTitle, eventTime);

        return notification;
    }


    private void handleDayClick(int currentDate) {
        selectedDay = currentDate;
        Label todayLBL = new Label(currentDate + " " + monthLBL.getText() + " " + yearLBL.getText());
        todayLBL.setPadding(new Insets(5, 5, 5, 5));
        todayLBL.setFont(Font.font("Bodoni MT Black", 17));
        todayLBL.setStyle("-fx-text-fill:  #183a4e");
    }



    /**
     * Creates calendar activity UI elements and adds them to the provided stack pane.
     *
     * @param currCalendarActivities The list of calendar activities for a specific date.
     * @param stackPane              The stack pane where the calendar activities will be added.
     */
    private  void createCalendarActivity(List<CalendarActivity> currCalendarActivities, VBox stackPane) {

        for (CalendarActivity currCalendarActivity : currCalendarActivities) {
            GridPane calendarActivityBox=new GridPane();
            ColumnConstraints col1=new ColumnConstraints();
            col1.setPercentWidth(80);
            col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);

            ColumnConstraints col2=new ColumnConstraints();
            col2.setPercentWidth(20);
            col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);

            calendarActivityBox.getColumnConstraints().addAll(col1,col2);

            RowConstraints row=new RowConstraints();
            row.setVgrow(Priority.SOMETIMES);

            calendarActivityBox.getRowConstraints().add(row);

            calendarActivityBox.setPadding(new Insets(5));
            calendarActivityBox.setStyle("-fx-background-color: #203649; -fx-background-radius: 10;");
            VBox.setMargin(calendarActivityBox, new Insets(5));

            ZonedDateTime activityTime= currCalendarActivity.getDate();
            Label text = new Label(currCalendarActivity.getClientName() + ", " + activityTime.toLocalTime());
            text.setFont(Font.font("Bodoni MT Black", 15));
            text.setStyle("-fx-text-fill: white");

            GridPane.setColumnIndex(text,0);

            ImageView imageView = new ImageView("/deleteWhite.png");
            imageView.setFitHeight(31.0);
            imageView.setFitWidth(31.0);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);

            GridPane.setColumnIndex(imageView,1);

            calendarActivityBox.getChildren().addAll(text,imageView);
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                System.out.println(text.getText());
            });

            stackPane.getChildren().add(calendarActivityBox);
            imageView.setOnMouseClicked(event -> { //delete

                ZonedDateTime ymd= ZonedDateTime.of(activityTime.getYear(),activityTime.getMonth().getValue(),selectedDay,0,0,0,0,ZonedDateTime.now().getZone());
                calendarActivities.computeIfPresent(ymd, (k, v) -> {
                    v.removeIf(ca -> ca.getDate()== activityTime && Objects.equals(ca.getClientName(), currCalendarActivity.getClientName()));
                    if (v.isEmpty()) {
                        return null;
                    }
                    return v;
                });
                drawCalendar();

            });

        }

    }


    /**
     * Creates a map of calendar activities grouped by day of the month.
     *
     * @param calendarActivities The calendar.
     * @return A map of calendar activities grouped by day of the month.
     */
    private  Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity : calendarActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<CalendarActivity> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarActivity> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }


    // Map to store calendar activities
    Map<ZonedDateTime,ArrayList<CalendarActivity>>calendarActivities=new HashMap<>();

    /**
     * Adds a new activity for the selected day.
     */
//    public void addNewDayActivity() {
//        int year = dateFocus.getYear();
//        int month = dateFocus.getMonth().getValue();
//
//        ZonedDateTime time = ZonedDateTime.of(year, month, selectedDay, hrSpinner.getValue(), minSpinner.getValue(), 0, 0, dateFocus.getZone());
//        CalendarActivity calendarActivity = new CalendarActivity(time, newTaskNameTF.getText());
//        time=ZonedDateTime.of(year,month,selectedDay,0,0,0,0,dateFocus.getZone());
//        calendarActivities.computeIfAbsent(time, k -> new ArrayList<>()).add(calendarActivity);
//
////        createCalendarMap(calendarActivities);
//        drawCalendar();
//        dayDeadlines.getChildren().clear();
//        Label todayLBL = new Label(selectedDay + " " + monthLBL.getText() + " " + yearLBL.getText());
//        todayLBL.setStyle("-fx-text-fill:  #183a4e");
//
//        todayLBL.setPadding(new Insets(5, 5, 5, 5));
//        todayLBL.setFont(Font.font("Bodoni MT Black", 17));
//        dayDeadlines.getChildren().add(todayLBL);
//        if (getCalendarActivitiesMonth(dateFocus).get(selectedDay) != null) {
//            createCalendarActivity(getCalendarActivitiesMonth(dateFocus).get(selectedDay), dayDeadlines);
//        }
//    }


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

