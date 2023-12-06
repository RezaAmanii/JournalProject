package org.group12.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.group12.controllerView.HomePageView;
import org.group12.model.homeCalendar.CalendarActivityModel;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

public class HomePageController implements Initializable {

    @FXML
    public Label nameLBL;
    @FXML
    public  Label monthLBL;
    @FXML
    public  Label yearLBL;
    @FXML
    public VBox dayDeadlines;
    @FXML
    public GridPane calendarPane;

    @FXML
    public TextField newTaskNameTF;
    @FXML
    public Spinner<Integer> hrSpinner;
    @FXML
    public Spinner<Integer> minSpinner;

    @FXML
    private Pane yourPane;

    public ZonedDateTime dateFocus;
    public  ZonedDateTime today;
    public  int selectedDay;
    private  HomePageView homePageView ;
    private   CalendarActivityModel calendarActivityModel ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homePageView = new HomePageView();
        calendarActivityModel = new CalendarActivityModel();
        hrSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,ZonedDateTime.now().getHour()));
        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,ZonedDateTime.now().getMinute()));
        homePageView.setComponent( nameLBL,  monthLBL,  yearLBL,  dayDeadlines,  calendarPane,  newTaskNameTF, hrSpinner , minSpinner ,  ZonedDateTime.now(),  ZonedDateTime.now(), ZonedDateTime.now().getDayOfMonth() );
        homePageView.drawCalendar(calendarActivityModel.getCalendarActivitiesMonth(homePageView.dateFocus));
    }

    public void forwardOneMonth(MouseEvent mouseEvent) {
        homePageView.forwardOneMonth();
        homePageView.drawCalendar(calendarActivityModel.getCalendarActivitiesMonth(homePageView.dateFocus));
    }

    public void backOneMonth(MouseEvent mouseEvent) {
        homePageView.backOneMonth();
        homePageView.drawCalendar(calendarActivityModel.getCalendarActivitiesMonth(homePageView.dateFocus));
    }


    public void addNewDayActivity(MouseEvent mouseEvent) {
        addNewCalender(homePageView.prepareCalenderModel());
        homePageView.drawCalendar(calendarActivityModel.getCalendarActivitiesMonth(homePageView.dateFocus));
        homePageView.addNewDayActivity(calendarActivityModel.getCalendarActivitiesMonth(homePageView.dateFocus));
    }


    public void addNewCalender(CalendarActivityModel calendarActivityModel){
        calendarActivityModel.getCalendarActivities().computeIfAbsent(calendarActivityModel.getDate(), k -> new ArrayList<>()).add(calendarActivityModel);
    }

}