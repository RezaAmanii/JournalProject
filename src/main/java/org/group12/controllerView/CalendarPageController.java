package org.group12.controllerView;

import org.group12.model.homeCalendar.CalendarActivityModel;
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
        //drawCalendar();
    }


    /**
     * Sets the month to January and redraws the calendar.
     */
    public void january(){
        dateFocus=dateFocus.withMonth(1);
        //drawCalendar();
    }
    @FXML
    void feb(){
        dateFocus=dateFocus.withMonth(2);
        //drawCalendar();
    }
    @FXML
    void mar(){
        dateFocus=dateFocus.withMonth(3);
        //drawCalendar();
    }
    @FXML
    void apr(){
        dateFocus=dateFocus.withMonth(4);
        //drawCalendar();
    }@FXML
    void may(){
        dateFocus=dateFocus.withMonth(5);
        //drawCalendar();
    }
    @FXML
    void june(){
        dateFocus=dateFocus.withMonth(6);
        //drawCalendar();
    }
    @FXML
    void july(){
        dateFocus=dateFocus.withMonth(7);
        //drawCalendar();
    }@FXML
    void aug(){dateFocus=dateFocus.withMonth(8);
        //drawCalendar();

    }
    @FXML
    void sep(){
        dateFocus=dateFocus.withMonth(9);
        //drawCalendar();
    }
    @FXML
    void oct(){
        dateFocus=dateFocus.withMonth(10);
        //drawCalendar();
    }
    @FXML
    void nov(){
        dateFocus=dateFocus.withMonth(11);
        //drawCalendar();
    }
    @FXML
    void dec(){
        dateFocus=dateFocus.withMonth(12);
        //drawCalendar();
    }

    @FXML
    void backOneMonth() {
        dateFocus = dateFocus.minusMonths(1);
//        dateFocus=dateFocus.minusDays(7);
        //drawCalendar();
    }

    @FXML
    void forwardOneMonth() {
        dateFocus = dateFocus.plusMonths(1);
        //drawCalendar();
    }


}

