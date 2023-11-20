package org.group12.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

public class MainMenuController implements Initializable {

    public VBox sideBar;
    public Pane homePane;
    public Label homeLBL;
    public Pane journalPane;
    public Label journalLBL;
    public Pane todoPane;
    public Label todoLBL;
    public GridPane calendarPane;
    public Label calendarLBL;
    public Pane settingsPane;
    public Label settingsLBL;
    public Label nameLBL;
    public Label homeLBL1;
    public Label monthLBL;
    public Label yearLBL;
    public VBox dayDeadlines;
    public TextField newTaskNameTF;
    public Spinner<Integer> hrSpinner;
    public Spinner<Integer> minSpinner;


    ZonedDateTime dateFocus;
    ZonedDateTime today;
    int selectedDay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hrSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,ZonedDateTime.now().getHour()));
        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,ZonedDateTime.now().getMinute()));
        todoPane.setVisible(false);
        homePane.setVisible(true);
        today = ZonedDateTime.now();
        selectedDay = today.getDayOfMonth();
        dateFocus = ZonedDateTime.now();
        drawCalendar();
    }

    /**
     * Switches to the "ToDo" page.
     */
    public void switchToTabPane(){
        homePane.setVisible(false);
        todoPane.setVisible(true);
    }

    public boolean sideBarExpanded=false;

    public void sideBarOnMouseEnter() {
        sideBarExpanded = !sideBarExpanded;
        if (sideBarExpanded) {
            sideBar.setPrefWidth(sideBar.getMaxWidth());

            calendarLBL.setVisible(true);
            settingsLBL.setVisible(true);
            homeLBL.setVisible(true);
            todoLBL.setVisible(true);
            journalLBL.setVisible(true);
            homeLBL1.setVisible(true);

        } else {
            sideBar.setPrefWidth(sideBar.getMinWidth());
            calendarLBL.setVisible(false);
            settingsLBL.setVisible(false);
            homeLBL.setVisible(false);
            todoLBL.setVisible(false);
            journalLBL.setVisible(false);
            homeLBL1.setVisible(false);
        }

    }

    /**
     * Switches to the "Home" page.
     */
    public void switchToHomePane(){
        homePane.setVisible(true);
        todoPane.setVisible(false);
    }

    @FXML
    void backOneMonth() {
        dateFocus = dateFocus.minusMonths(1);
        drawCalendar();
    }

    @FXML
    void forwardOneMonth() {
        dateFocus = dateFocus.plusMonths(1);
        drawCalendar();
    }

    /*
    private void clearCell(GridPane gridPane, int rowIndex, int colIndex) {
        gridPane.getChildren().removeIf(node ->
                GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node) == colIndex);
    }
     */

    private void drawCalendar() {
        calendarPane.getChildren().clear();
        yearLBL.setText(String.valueOf(dateFocus.getYear()));
        monthLBL.setText(String.valueOf(dateFocus.getMonth()));


        //List of activities for a given month
        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                VBox vDay = new VBox();
                int calculatedDate = (j + 1) + (7 * i);
                int currentDate = calculatedDate - dateOffset;
                Label date = new Label(String.valueOf(currentDate));
                date.setPadding(new Insets(5, 5, 5, 5));
                date.setFont(Font.font("Bodoni MT Black", 17));
                boolean valid = false;

                if (calculatedDate > dateOffset) {
                    if (currentDate <= monthMaxDate) {
                        valid = true;
                        vDay.setStyle("-fx-background-color: #e3f6f5; -fx-background-radius: 10;-fx-border-radius: 10; -fx-border-color: #ffffff");
                        vDay.setPadding(new Insets(1,1,0,1));
                        GridPane.setMargin(vDay, new Insets(4));
//                        vDay.getChildren().add(date);

                    }

                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        vDay.setStyle("-fx-background-color: #213742; -fx-background-radius: 10;-fx-border-radius: 10; -fx-border-color: #ffffff");
                        date.setStyle("-fx-text-fill: #ffffff;");
                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        dayDeadlines.getChildren().clear();
                        //day label
                        Label todayLBL = new Label(String.valueOf(currentDate) + " " + monthLBL.getText() + " " + yearLBL.getText());
                        todayLBL.setPadding(new Insets(5, 5, 5, 5));
                        todayLBL.setFont(Font.font("Bodoni MT Black", 17));
                        todayLBL.setStyle("-fx-text-fill: #081e2a");
                        dayDeadlines.getChildren().add(todayLBL);
                        //if activities
                        if (calendarActivities != null) {
                            createCalendarActivity(calendarActivities, dayDeadlines);
                        }
                    }
                    if (valid) {
                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        vDay.getChildren().add(date);
                        if (calendarActivities != null) {
                            VBox notification = new VBox();
                            notification.setPadding(new Insets(5,5,3,5));
                            notification.setStyle("-fx-background-color: #081e2a; -fx-background-radius: 10;");
                            VBox.setMargin(notification, new Insets(2));
                            vDay.getChildren().add(notification);
                        }
                        vDay.setOnMouseClicked(mouseEvent -> {
//                            vDay.setStyle("-fx-background-color: #284a64; -fx-background-radius: 10;");
                            dayDeadlines.getChildren().clear();
                            selectedDay = currentDate;
                            Label todayLBL = new Label(String.valueOf(currentDate) + " " + monthLBL.getText() + " " + yearLBL.getText());
                            todayLBL.setPadding(new Insets(5, 5, 5, 5));
                            todayLBL.setFont(Font.font("Bodoni MT Black", 17));
                            todayLBL.setStyle("-fx-text-fill: #081e2a");
                            dayDeadlines.getChildren().add(todayLBL);
                            if (calendarActivities != null) {
                                createCalendarActivity(calendarActivities, dayDeadlines);
                            }
                        });
                        calendarPane.add(vDay, j, i);

                    }
                }
//                currDayRow.getChildren().add(stackPane);
            }
//            calendar.getChildren().add(currDayRow);

        }
    }

    private void createCalendarActivity(List<CalendarActivity> currCalendarActivities, VBox stackPane) {

        for (CalendarActivity currCalendarActivity : currCalendarActivities) {
//            VBox calendarActivityBox = new VBox();
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
            Text text = new Text(currCalendarActivity.getClientName() + ", " + activityTime.toLocalTime());
            text.setFont(Font.font("Bodoni MT Black", 15));
            text.setStyle("-fx-text-fill: #ffffff");

            GridPane.setColumnIndex(text,0);

            ImageView imageView = new ImageView("/delete-96.png");
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

//                currCalendarActivities.removeIf(ca -> ca.getDate() == activityTime && Objects.equals(ca.getClientName(), currCalendarActivity.getClientName()));
//                calendarActivities.removeIf(ca -> ca.getDate()== activityTime && Objects.equals(ca.getClientName(), currCalendarActivity.getClientName()));
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


    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
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

    Map<ZonedDateTime,ArrayList<CalendarActivity>>calendarActivities = new HashMap<>();


    public void addNewDayActivity() {
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        ZonedDateTime time = ZonedDateTime.of(year, month, selectedDay, hrSpinner.getValue(), minSpinner.getValue(), 0, 0, dateFocus.getZone());
        CalendarActivity calendarActivity = new CalendarActivity(time, newTaskNameTF.getText());
        time=ZonedDateTime.of(year,month,selectedDay,0,0,0,0,dateFocus.getZone());
        calendarActivities.computeIfAbsent(time, k -> new ArrayList<>()).add(calendarActivity);

//        createCalendarMap(calendarActivities);
        drawCalendar();
        dayDeadlines.getChildren().clear();
        Label todayLBL = new Label(String.valueOf(selectedDay) + " " + monthLBL.getText() + " " + yearLBL.getText());
        todayLBL.setPadding(new Insets(5, 5, 5, 5));
        todayLBL.setFont(Font.font("Bodoni MT Black", 17));
        todayLBL.setStyle("-fx-text-fill: #081e2a"); //today date label
        dayDeadlines.getChildren().add(todayLBL);
        if (getCalendarActivitiesMonth(dateFocus).get(selectedDay) != null) {
            createCalendarActivity(getCalendarActivitiesMonth(dateFocus).get(selectedDay), dayDeadlines);
        }
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<CalendarActivity> currMonthActivities = new ArrayList<>();
        for (Map.Entry<ZonedDateTime,ArrayList<CalendarActivity>>entry:calendarActivities.entrySet()){
            if (entry.getKey().getMonth()==dateFocus.getMonth()){
                currMonthActivities.addAll(entry.getValue());
            }
        }
//        for (CalendarActivity ca : calendarActivities) {
//            if (ca.getDate().getMonth() == dateFocus.getMonth()) {
//                currMonthActivities.add(ca);
//            }
//        }
        return createCalendarMap(currMonthActivities);
    }

}
