package org.group12.view;

import org.group12.toDoTask;
import org.group12.toDoList;
import org.group12.globals;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
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
    public Label todayToDoLBL;
    public Label importantToDoLBL;
    public VBox appendableListVbox;
    public Label activeListNameLBL;
    public VBox ongoingTasksVbox;
    public VBox completedTasksVbox;
    public GridPane addNewListBtn;
    public BorderPane mainWindowBorder;



    ZonedDateTime dateFocus;
    ZonedDateTime today;
    int selectedDay;

    ArrayList<toDoList> allLists = new ArrayList<>();
    public toDoList selectedList = new toDoList();
    public static toDoTask selectedTask=null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hrSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,ZonedDateTime.now().getHour()));
        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,ZonedDateTime.now().getMinute()));
        todoPane.setVisible(false);
        homePane.setVisible(true);
        today = ZonedDateTime.now();
        selectedDay = today.getDayOfMonth();
        dateFocus = ZonedDateTime.now();

        allLists.add(new toDoList(1,"Today", new ArrayList<>()));
        allLists.add(new toDoList(2,"Important", new ArrayList<>()));
        selectedList = allLists.get(0);
        refreshSidePanelInfo();

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


    //////////////////////////////////////// ToDo Functions
    //////////////////////////////////////

    //todo functions
    public void addNewList() {
        //
        GridPane listToAppend = new GridPane();
        toDoList newList = new toDoList(globals.createNewRandomID(globals.toDoListsIDs),"New List", new ArrayList<>());
        allLists.add(newList);
        listToAppend.setMinHeight(33.0);
        listToAppend.setMinWidth(300.0);
        listToAppend.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-color: white; -fx-border-radius: 10;");

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(10.0);
        col2.setPercentWidth(76.0);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setMinWidth(10.0);
        col3.setPercentWidth(24.0);

        listToAppend.getColumnConstraints().addAll(col2, col3);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        listToAppend.getRowConstraints().add(row1);

        TextField taskNameLBL = new TextField(newList.getListName());
        taskNameLBL.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent;");
        taskNameLBL.setEditable(false);
        taskNameLBL.setAlignment(Pos.CENTER);
        //


        GridPane.setColumnIndex(taskNameLBL, 0);
        GridPane.setHalignment(taskNameLBL, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(taskNameLBL, javafx.geometry.VPos.CENTER);
        taskNameLBL.setFont(new Font("Berlin Sans FB Demi Bold", 22.0));


        Label noOfTasks = new Label(String.valueOf(allLists.get(allLists.indexOf(newList)).getTasks().size()));
        noOfTasks.setAlignment(javafx.geometry.Pos.CENTER);
        noOfTasks.setTextFill(javafx.scene.paint.Color.WHITE);
        GridPane.setColumnIndex(noOfTasks, 1);
        GridPane.setHalignment(noOfTasks, javafx.geometry.HPos.CENTER);
        Font font2 = new Font("Berlin Sans FB Demi Bold", 22.0);
        noOfTasks.setFont(font2);

        taskNameLBL.setOnMouseClicked(event -> {
            selectedList = allLists.get(findTheToDoList(newList));////////////
            noOfTasks.setText(String.valueOf(allLists.get(findTheToDoList(selectedList)).getTasks().size()));
            refreshSidePanelInfo();
            if (event.getClickCount() == 2) {
                taskNameLBL.setEditable(true);
                taskNameLBL.requestFocus();
            }
        });

        taskNameLBL.setOnKeyPressed(event -> {
            selectedList=newList;
            noOfTasks.setText(String.valueOf(allLists.get(findTheToDoList(selectedList)).getTasks().size()));

            if (event.getCode() == KeyCode.ENTER) {
                taskNameLBL.setEditable(false);
                renameToDoList(selectedList, taskNameLBL.getText());
            }
        });

        mainWindowBorder.setOnMouseClicked(event -> {

            mainWindowBorder.getOnMouseClicked();
            selectedList = allLists.get(findTheToDoList(newList));////////////
            noOfTasks.setText(String.valueOf(allLists.get(findTheToDoList(selectedList)).getTasks().size()));

            if (event.getClickCount() > 0 && !taskNameLBL.getBoundsInParent().contains(event.getX(), event.getY())) {
                taskNameLBL.setEditable(false);
                renameToDoList(selectedList, taskNameLBL.getText());

            }
        });

        listToAppend.setOnMouseClicked(event -> {
            selectedList = allLists.get(findTheToDoList(newList));////////////
            noOfTasks.setText(String.valueOf(allLists.get(findTheToDoList(selectedList)).getTasks().size()));
            refreshSidePanelInfo();///////////////
        });

        listToAppend.getChildren().addAll(taskNameLBL, noOfTasks);

        VBox.setMargin(listToAppend, new Insets(10.0, 10.0, 0, 10.0));

        appendableListVbox.getChildren().add(listToAppend);
    }


    void renameToDoList(toDoList list, String newName) {
        allLists.get(findTheToDoList(list)).setListName(newName);
        refreshSidePanelInfo();
    }
    void renameTask(toDoTask task,String newName){
        allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(task)).setTaskName(newName);
    }
    int findTheToDoList(toDoList list) {
        for (toDoList list1 : allLists) {
            if (list1.getID() == list.getID()) return allLists.indexOf(list);
        }
        return -1;
    }

    int findTheTask(toDoTask task) {
        selectedList=allLists.get(findTheToDoList(selectedList));
        for (toDoTask task1 : selectedList.getTasks()) {
            if (task1.getID() == task.getID()) return selectedList.getTasks().indexOf(task1);
        }
        return -1;
    }


    GridPane createNewTaskObject(toDoTask task) {

        GridPane newTaskPane = new GridPane();
        newTaskPane.setMinHeight(30.0);
        newTaskPane.setMinWidth(250.0);
        newTaskPane.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-color: white; -fx-border-radius: 10;");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMinWidth(10.0);
        col1.setPercentWidth(20.0);
        col1.setPrefWidth(216.44439019097223);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(10.0);
        col2.setPercentWidth(75.0);
        col2.setPrefWidth(216.44439019097223);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setMinWidth(10.0);
        col3.setPercentWidth(20.0);
        col3.setPrefWidth(104.00005425347223);

        newTaskPane.getColumnConstraints().addAll(col1, col2, col3);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        newTaskPane.getRowConstraints().add(row1);

        TextField taskNameLBL = new TextField(task.getTaskName());
        taskNameLBL.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent;");
        taskNameLBL.setEditable(false);
        taskNameLBL.setAlignment(Pos.CENTER);
        GridPane.setHalignment(taskNameLBL, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(taskNameLBL, javafx.geometry.VPos.CENTER);
        taskNameLBL.setFont(new Font("Berlin Sans FB Demi Bold", 22.0));

        double tasksFinishedPercentage=0;
        if ((task.getCompletedSubTasks().size()+task.getToDoSubTasks().size()!=0)){
            tasksFinishedPercentage=(double)(task.getCompletedSubTasks().size() /(task.getCompletedSubTasks().size()+task.getToDoSubTasks().size()));
        }

        ProgressIndicator progressIndicator = new ProgressIndicator(tasksFinishedPercentage);
        GridPane.setHalignment(progressIndicator, javafx.geometry.HPos.CENTER);
        GridPane.setMargin(progressIndicator, new Insets(3.0, 3.0, 3.0, 3.0));

        ImageView imageView = new ImageView("notification.png");
        imageView.setFitHeight(31.0);
        imageView.setFitWidth(31.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        GridPane.setColumnIndex(taskNameLBL, 1);
        GridPane.setColumnIndex(progressIndicator, 0);

        GridPane.setColumnIndex(imageView, 2);
        GridPane.setHalignment(imageView, javafx.geometry.HPos.CENTER);
        GridPane.setMargin(imageView, new Insets(2.0, 2.0, 2.0, 2.0));
        taskNameLBL.setOnMouseClicked(event -> {
            selectedTask=task;
            if (event.getClickCount() == 2) {
                taskNameLBL.setEditable(true);
                taskNameLBL.requestFocus();
            }
        });
        imageView.setOnMouseClicked(event -> {
            selectedTask=task;
            try {
                globals.openNewForm("subTasks.fxml",selectedTask.getTaskName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        taskNameLBL.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                taskNameLBL.setEditable(false);
                renameTask(allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(task)), taskNameLBL.getText());
            }
        });

        newTaskPane.getChildren().addAll(taskNameLBL, progressIndicator, imageView);

        VBox.setMargin(newTaskPane, new Insets(10.0, 10.0, 0, 10.0));
        return newTaskPane;
    }

    public void addNewTask() {
        toDoTask newToDoTask=new toDoTask(globals.createNewRandomID(globals.toDoListsIDs),"newTask", false, ZonedDateTime.now(),new ArrayList<>(),new ArrayList<>());
        selectedList.getTasks().add(newToDoTask);
        GridPane newTask = createNewTaskObject(newToDoTask);

        ongoingTasksVbox.getChildren().add(newTask);

    }

    private void refreshSidePanelInfo() {
        selectedList=allLists.get(findTheToDoList(selectedList));
        activeListNameLBL.setText(selectedList.getListName());
        ongoingTasksVbox.getChildren().clear();
        completedTasksVbox.getChildren().clear();
        for (toDoTask task:selectedList.getTasks()){
            if (!task.isFinished())
                ongoingTasksVbox.getChildren().add(createNewTaskObject(task));
            else
                completedTasksVbox.getChildren().add(createNewTaskObject(task));

        }

    }

    //////////////////////////////////////
    ///////////////////////////////////////  End ToDo Functions

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
