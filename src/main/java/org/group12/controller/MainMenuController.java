package org.group12.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

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


    ZonedDateTime dateFocus;
    ZonedDateTime today;
    int selectedDay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();

    }

    /**
     * Switches to the "ToDo" page.
     */
    public void switchToTabPane(){
        homePane.setVisible(false);
        todoPane.setVisible(true);
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
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                clearCell(calendarPane, j, i);

            }
        }
        drawCalendar();
    }

    @FXML
    void forwardOneMonth() {
        dateFocus = dateFocus.plusMonths(1);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                clearCell(calendarPane, i, j);

            }
        }
        drawCalendar();
    }

    private void clearCell(GridPane gridPane, int rowIndex, int colIndex) {
        gridPane.getChildren().removeIf(node ->
                GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node) == colIndex);
    }

    private void drawCalendar() {
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
                boolean valid=false;
                if (calculatedDate > dateOffset) {
                    if (currentDate <= monthMaxDate) {
                        valid=true;
                        vDay.setStyle("-fx-background-color: #e3f6f5; -fx-background-radius: 10;");
                        vDay.setPadding(new Insets(1));
                        GridPane.setMargin(vDay, new Insets(4));
//                        vDay.getChildren().add(date);

                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        vDay.setStyle("-fx-background-color: #213742; -fx-background-radius: 10;");
                        date.setStyle("-fx-text-fill: #ffffff;");
                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        dayDeadlines.getChildren().clear();
                        //day label
                        Label todayLBL = new Label(String.valueOf(currentDate)+" "+monthLBL.getText()+" "+yearLBL.getText());
                        todayLBL.setPadding(new Insets(5, 5, 5, 5));
                        todayLBL.setFont(Font.font("Bodoni MT Black", 17));
                        todayLBL.setStyle("-fx-text-fill: #081e2a");
                        dayDeadlines.getChildren().add(todayLBL);
                        //if activities
                        if(calendarActivities != null){
                            createCalendarActivity(calendarActivities, dayDeadlines);
                        }
                    }
                    if (valid){
                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        vDay.getChildren().add(date);
                        if(calendarActivities != null){
                            VBox notification = new VBox();
                            notification.setPadding(new Insets(5));
                            notification.setStyle("-fx-background-color: #081e2a; -fx-background-radius: 10;");
                            VBox.setMargin(notification,new Insets(2));
                            vDay.getChildren().add(notification);
                        }
                        vDay.setOnMouseClicked(mouseEvent -> {
//                            vDay.setStyle("-fx-background-color: #284a64; -fx-background-radius: 10;");
                            dayDeadlines.getChildren().clear();
                            selectedDay=currentDate;
                            Label todayLBL = new Label(String.valueOf(currentDate)+" "+monthLBL.getText()+" "+yearLBL.getText());
                            todayLBL.setPadding(new Insets(5, 5, 5, 5));
                            todayLBL.setFont(Font.font("Bodoni MT Black", 17));
                            todayLBL.setStyle("-fx-text-fill: #081e2a");
                            dayDeadlines.getChildren().add(todayLBL);
                            if(calendarActivities != null){
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
        VBox calendarActivityBox = new VBox();
        calendarActivityBox.setPadding(new Insets(5));
        calendarActivityBox.setStyle("-fx-background-color: #e3f6f5; -fx-background-radius: 10;");
        VBox.setMargin(calendarActivityBox,new Insets(5));
        for (CalendarActivity currCalendarActivity : currCalendarActivities) {
            Text text = new Text(currCalendarActivity.getClientName() + ", " + currCalendarActivity.getDate().toLocalTime());
            text.setStyle("-fx-text-fill: #ffffff");
            text.setFont(Font.font("Bodoni MT Black", 15));
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                System.out.println(text.getText());
            });
        }
        stackPane.getChildren().add(calendarActivityBox);
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


    List<CalendarActivity> calendarActivities = new ArrayList<>();



    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<CalendarActivity>currMonthActivities=new ArrayList<>();
        for (CalendarActivity ca:calendarActivities) {
            if (ca.getDate().getMonth()==dateFocus.getMonth()){
                currMonthActivities.add(ca);
            }
        }
        return createCalendarMap(currMonthActivities);
    }

    public boolean sideBarExpanded=false;
    public void sideBarOnMouseEnter(){
        sideBarExpanded=!sideBarExpanded;
        if (sideBarExpanded){
            sideBar.setPrefWidth(sideBar.getMaxWidth());

            calendarLBL.setVisible(true);
            settingsLBL.setVisible(true);
            homeLBL.setVisible(true);
            todoLBL.setVisible(true);
            journalLBL.setVisible(true);
            homeLBL1.setVisible(true);

        }
        else{
            sideBar.setPrefWidth(sideBar.getMinWidth());
            calendarLBL.setVisible(false);
            settingsLBL.setVisible(false);
            homeLBL.setVisible(false);
            todoLBL.setVisible(false);
            journalLBL.setVisible(false);
            homeLBL1.setVisible(false);
        }

    }
}
