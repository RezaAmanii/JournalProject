package org.group12.controllerView;

import org.group12.model.homeCalendar.CalendarActivityModel;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

import static org.group12.view.HomeCalendarView.*;

/**
 * This class is a controller for the Home Page.
 * It implements the Initializable interface.
 */
public class HomePageView implements Initializable {
    public Label nameLBL;
    public  Label monthLBL;
    public  Label yearLBL;
    public VBox dayDeadlines;
    public GridPane calendarPane;


    public TextField newTaskNameTF;
    public Spinner<Integer> hrSpinner;
    public Spinner<Integer> minSpinner;

    public ZonedDateTime dateFocus;
    public  ZonedDateTime today;
    public  int selectedDay;

    // Map to store calendar activities

    public HomePageView() {
    }

    public void setComponent(Label nameLBL, Label monthLBL, Label yearLBL, VBox dayDeadlines, GridPane calendarPane, TextField newTaskNameTF, Spinner<Integer> hrSpinner, Spinner<Integer> minSpinner, ZonedDateTime dateFocus, ZonedDateTime today, int selectedDay) {
        this.nameLBL = nameLBL;
        this.monthLBL = monthLBL;
        this.yearLBL = yearLBL;
        this.dayDeadlines = dayDeadlines;
        this.calendarPane = calendarPane;
        this.newTaskNameTF = newTaskNameTF;
        this.hrSpinner = hrSpinner;
        this.minSpinner = minSpinner;
        this.dateFocus = dateFocus;
        this.today = today;
        this.selectedDay = selectedDay;
    }

    /**
     * Initializes the HomePageController.
     * It sets the initial values for hrSpinner and minSpinner,
     * as well as the initial values for today, selectedDay, and dateFocus,
     * and draws the calendar.
     * @param location The URL location.
     * @param resources The ResourceBundle resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hrSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,ZonedDateTime.now().getHour()));
        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,ZonedDateTime.now().getMinute()));
        today = ZonedDateTime.now();
        selectedDay = today.getDayOfMonth();
        dateFocus = ZonedDateTime.now();
    }

    /**
     * Moves the date focus back by one month and redraws the calendar.
     */

    public void backOneMonth() {
        dateFocus = dateFocus.minusMonths(1);
        dateFocus=dateFocus.minusDays(7);

    }
    public void forwardOneMonth() {
        dateFocus = dateFocus.plusMonths(1);
    }

    /**
     * Draws the calendar by creating and arranging the necessary UI elements based on the current date focus.
     */
    public  void drawCalendar(Map<Integer, List<CalendarActivityModel>>  calendarActivities) {
        calendarPane.getChildren().clear();
        setYearAndMonthLabels();
        int monthMaxDate = calculateMonthMaxDate();
        int dateOffset = calculateDateOffset();
        LocalDate today = LocalDate.now();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                renderDay(i, j, monthMaxDate, dateOffset, today, calendarActivities);
            }
        }
    }



    /**
     * Creates calendar activity UI elements and adds them to the provided stack pane.
     *
     * @param currCalendarActivities The list of calendar activities for a specific date.
     * @param stackPane              The stack pane where the calendar activities will be added.
     */
    private  void createCalendarActivity(List<CalendarActivityModel> currCalendarActivities, VBox stackPane) {

        for (CalendarActivityModel currCalendarActivityModel : currCalendarActivities) {
            GridPane calendarActivityBox = createActivityBox(currCalendarActivityModel);
            // change with new logic
            configureDeleteFunctionality(currCalendarActivityModel, calendarActivityBox);
            stackPane.getChildren().add(calendarActivityBox);
        }
    }


    /**
     * Adds a new activity for the selected day.
     */
    public void addNewDayActivity(Map<Integer, List<CalendarActivityModel>> calendarActivityMap) {

        dayDeadlines.getChildren().clear();
        Label todayLBL = new Label(selectedDay + " " + monthLBL.getText() + " " + yearLBL.getText());
        todayLBL.setStyle("-fx-text-fill:  #183a4e");
        todayLBL.setPadding(new Insets(5, 5, 5, 5));
        todayLBL.setFont(Font.font("Bodoni MT Black", 17));
        dayDeadlines.getChildren().add(todayLBL);
        if ( calendarActivityMap != null) {
            createCalendarActivity(calendarActivityMap.get(selectedDay), dayDeadlines);
        }

    }

    public CalendarActivityModel prepareCalenderModel(){
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();
        ZonedDateTime time = ZonedDateTime.of(year, month, selectedDay, hrSpinner.getValue(), minSpinner.getValue(), 0, 0, dateFocus.getZone());
        time=ZonedDateTime.of(year,month,selectedDay,0,0,0,0,dateFocus.getZone());
        return new CalendarActivityModel(time, newTaskNameTF.getText());
    }



    public void renderCalender(int monthMaxDate, int dateOffset, Map<Integer, List<CalendarActivityModel>> calenderActivityMap){

    }

    private void renderDay(int i, int j, int monthMaxDate, int dateOffset, LocalDate today, Map<Integer, List<CalendarActivityModel>> calenderActivityMap) {
        VBox vDay = new VBox();
        int calculatedDate = (j + 1) + (7 * i);
        int currentDate = calculatedDate - dateOffset;
        Label date = createDateLabel(currentDate);
        Boolean valid = checkValidDate(calculatedDate, dateOffset, currentDate, monthMaxDate);
        if(valid) {
            List<CalendarActivityModel> calenderActivities = calenderActivityMap.get((currentDate));
            renderValidDay(vDay, date, calenderActivities, currentDate, today);
            setDayClickListener(vDay, currentDate, calenderActivities);
            calendarPane.add(vDay, j, i);
        }
    }

    private void setDayClickListener(VBox vDay, int currentDate, List<CalendarActivityModel> calendarActivities) {
        vDay.setOnMouseClicked(mouseEvent -> {
            dayDeadlines.getChildren().clear();
            selectedDay = currentDate;

            // Create and display the label for the clicked day
            Label clickedDayLabel = new Label(currentDate + " " + monthLBL.getText() + " " + yearLBL.getText());
            clickedDayLabel.setPadding(new Insets(5, 5, 5, 5));
            clickedDayLabel.setFont(Font.font("Bodoni MT Black", 17));
            clickedDayLabel.setStyle("-fx-text-fill:  #183a4e");
            dayDeadlines.getChildren().add(clickedDayLabel);

            // Display activities for the clicked day, if any
            if (calendarActivities != null) {
                createCalendarActivity(calendarActivities, dayDeadlines);
            }
        });
    }


    private void renderValidDay(VBox vDay, Label date, List<CalendarActivityModel> calendarActivities, int currentDate, LocalDate today) {
        vDay.getChildren().add(date);
        vDay.setStyle("-fx-background-color: #e3f6f5; -fx-background-radius: 10;-fx-border-radius: 10; -fx-border-color: #ffffff");
        vDay.setPadding(new Insets(1,1,0,1));
        GridPane.setMargin(vDay, new Insets(4));

        if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
            vDay.setStyle("-fx-background-color: #213742; -fx-background-radius: 10;-fx-border-radius: 10; -fx-border-color: #ffffff");
            date.setStyle("-fx-text-fill: #ffffff;");
            renderTodayActivities(calendarActivities);
        }

        if (calendarActivities != null) {
            renderActivitiesIndicator(vDay);
        }
    }

    private void renderTodayActivities(List<CalendarActivityModel> calendarActivities) {
        dayDeadlines.getChildren().clear();

        // Day label
        Label todayLBL = new Label(dateFocus.getDayOfMonth() + " " + monthLBL.getText() + " " + yearLBL.getText());
        todayLBL.setPadding(new Insets(5, 5, 5, 5));
        todayLBL.setFont(Font.font("Bodoni MT Black", 17));
        todayLBL.setStyle("-fx-text-fill:  #183a4e");
        dayDeadlines.getChildren().add(todayLBL);

        // If activities exist for today, display them
        if (calendarActivities != null) {
            createCalendarActivity(calendarActivities, dayDeadlines);
        }
    }



    private Boolean checkValidDate(int calculatedDate, int dateOffset, int currentDate, int monthMaxDate) {
        return calculatedDate > dateOffset && currentDate <= monthMaxDate;
    }


    private int calculateMonthMaxDate() {
        int monthMaxDate = dateFocus.getMonth().maxLength();

        //Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        return monthMaxDate;
    }

    private int calculateDateOffset() {
        return ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();
    }

    private void setYearAndMonthLabels() {
        yearLBL.setText(String.valueOf(dateFocus.getYear()));
        monthLBL.setText(String.valueOf(dateFocus.getMonth()));
    }



    private GridPane createActivityBox(CalendarActivityModel currCalendarActivityModel) {
        GridPane calendarActivityBox=new GridPane();
        configureColumns(calendarActivityBox);
        configureRow(calendarActivityBox);
        configureStyleAndMargins(calendarActivityBox);
        addTextAndImage(currCalendarActivityModel, calendarActivityBox);
        return calendarActivityBox;
    }

    private void addTextAndImage(CalendarActivityModel currCalendarActivityModel, GridPane calendarActivityBox) {
        ZonedDateTime activityTime= currCalendarActivityModel.getDate();
        Label text = createLabelText(currCalendarActivityModel, activityTime);
        ImageView imageView = createDeleteImageView();
        configureTextClickAction(text);
        configureDeleteAction(currCalendarActivityModel, imageView);

        calendarActivityBox.getChildren().addAll(text,imageView);
        GridPane.setColumnIndex(text,0);
        GridPane.setColumnIndex(imageView,1);
    }

    private void configureDeleteAction(CalendarActivityModel currCalendarActivityModel, ImageView imageView) {
        imageView.setOnMouseClicked(event -> handleDeleteAction(currCalendarActivityModel));
    }



    private void configureDeleteFunctionality(CalendarActivityModel currCalendarActivityModel, GridPane calendarActivityBox) {
        ImageView imageView = new ImageView("/deleteWhite.png");
        imageView.setFitHeight(31.0);
        imageView.setFitWidth(31.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        //
        imageView.setOnMouseClicked(event -> {
            ZonedDateTime activityTime = currCalendarActivityModel.getDate();
            ZonedDateTime ymd = ZonedDateTime.of(activityTime.getYear(), activityTime.getMonth().getValue(), selectedDay, 0, 0, 0, 0, ZonedDateTime.now().getZone());
            handleDeleteAction(currCalendarActivityModel);
        });
        //
        ImageView deleteIcon = imageView;
        configureDeleteAction(currCalendarActivityModel, deleteIcon);

        calendarActivityBox.getChildren().addAll(deleteIcon);
        GridPane.setColumnIndex(deleteIcon, 1);
    }

    private void handleDeleteAction(CalendarActivityModel currCalendarActivityModel) {
        ZonedDateTime activityTime = currCalendarActivityModel.getDate();
        ZonedDateTime ymd = ZonedDateTime.of(activityTime.getYear(), activityTime.getMonth().getValue(), selectedDay, 0, 0, 0, 0, ZonedDateTime.now().getZone());
//        calendarActivities.computeIfPresent(ymd, (k, v) -> {
//            v.removeIf(ca -> ca.getDate() == activityTime && Objects.equals(ca.getClientName(), currCalendarActivityModel.getClientName()));
//            if (v.isEmpty()) {
//                return null;
//            }
//            return v;
//        });
//        drawCalendar();
    }

    private static void configureTextClickAction(Label text) {
        text.setOnMouseClicked(mouseEvent -> {
            //On Text clicked
            System.out.println(text.getText());
        });
    }


}