package org.group12.view;

import javafx.scene.image.Image;
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
    public VBox fixedListsVbox;



    ZonedDateTime dateFocus;
    ZonedDateTime today;
    int selectedDay;

    public static ArrayList<toDoList> allLists = new ArrayList<>();
    public static toDoList selectedList = new toDoList();
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
        refreshAllListVBox();
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

    GridPane createNewListObject(toDoList newList){
        GridPane listToAppend = new GridPane();
        listToAppend.setMinHeight(33.0);
        listToAppend.setMinWidth(300.0);
        listToAppend.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-color: white; -fx-border-radius: 10;");

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(10.0);
        col2.setPercentWidth(75);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setMinWidth(10.0);
        col3.setPercentWidth(25);


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
            if (event.getClickCount() == 2 &&selectedList.getID()!=1&&selectedList.getID()!=2) {
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
            refreshSidePanelInfo();
            refreshAllListVBox();
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

        return listToAppend;
    }
    public void addNewList() {
        //
        toDoList newList = new toDoList(globals.createNewRandomID(globals.toDoListsIDs),"New List", new ArrayList<>());
        allLists.add(newList);
        GridPane listToAppend=createNewListObject(newList);

        appendableListVbox.getChildren().add(listToAppend);
    }

    public void deleteSelectedList(){
        if(selectedList.getID()==1||selectedList.getID()==2){
            globals.showErrorAlert("Can't delete Today or Important Lists, select a different list.");
            return;
        }
        for (toDoList list:allLists){
            if (list.getID()==selectedList.getID()){
                allLists.remove(list);
                break;
            }
        }
        refreshAllListVBox();
        refreshSidePanelInfo();
    }
    void refreshAllListVBox(){
        selectedList=allLists.get(0);
        fixedListsVbox.getChildren().clear();
        appendableListVbox.getChildren().clear();
        for (toDoList list:allLists){
            if (list.getID()==1||list.getID()==2){
                fixedListsVbox.getChildren().add(createNewListObject(list));
            }
            else{
                allLists.get(0).getTasks().clear();
                allLists.get(1).getTasks().clear();
                for (toDoTask task:list.getTasks()){
                    if (task.getTaskDeadline().getDayOfMonth()==ZonedDateTime.now().getDayOfMonth()
                            && task.getTaskDeadline().getMonth()==ZonedDateTime.now().getMonth()
                            && task.getTaskDeadline().getYear()==ZonedDateTime.now().getYear()){
                        allLists.get(0).getTasks().removeIf(task1 -> task1.getID() == task.getID());
                        allLists.get(0).getTasks().add(task);
                    }
                    if (task.isImportant()){
                        allLists.get(1).getTasks().removeIf(task1 -> task1.getID() == task.getID());
                        allLists.get(1).getTasks().add(task);
                    }
                }
                appendableListVbox.getChildren().add(createNewListObject(list));
            }
        }
        refreshSidePanelInfo();
    }
    void renameToDoList(toDoList list, String newName) {
        if (allLists.get(findTheToDoList(list)).getID()==1||allLists.get(findTheToDoList(list)).getID()==0)return;
        allLists.get(findTheToDoList(list)).setListName(newName);
        refreshSidePanelInfo();
    }
    void renameTask(toDoTask task,String newName){
        allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(task)).setTaskName(newName);
    }
    public static int findTheToDoList(toDoList list) {
        for (toDoList list1 : allLists) {
            if (list1.getID() == list.getID()) return allLists.indexOf(list);
        }
        return -1;
    }

    public static int findTheTask(toDoTask task) {
        selectedList=allLists.get(findTheToDoList(selectedList));
        for (toDoTask task1 : selectedList.getTasks()) {
            if (task1.getID() == task.getID()) return selectedList.getTasks().indexOf(task1);
        }
        return -1;
    }


    GridPane createNewTaskObject(toDoTask task) {

        GridPane newTaskPane = new GridPane();
        newTaskPane.setMinHeight(70.0);
        newTaskPane.setMinWidth(250.0);
        newTaskPane.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-color: white; -fx-border-radius: 10;");

        ColumnConstraints col1 = new ColumnConstraints(); //progress
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMinWidth(10.0);
        col1.setPercentWidth(12.5);
        col1.setPrefWidth(216);

        ColumnConstraints col2 = new ColumnConstraints(); //name and deadline
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(10.0);
        col2.setPercentWidth(50.0);
        col2.setPrefWidth(216);

        ColumnConstraints col3 = new ColumnConstraints(); //view
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setMinWidth(10.0);
        col3.setPercentWidth(12.5);
        col3.setPrefWidth(104);

        ColumnConstraints col4 = new ColumnConstraints(); //delete
        col4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col4.setMinWidth(10.0);
        col4.setPercentWidth(12.5);
        col4.setPrefWidth(104);

        ColumnConstraints col5 = new ColumnConstraints(); //important
        col5.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col5.setMinWidth(10.0);
        col5.setPercentWidth(12.5);
        col5.setPrefWidth(104);
        newTaskPane.getColumnConstraints().addAll(col1, col2, col3,col4,col5);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(30.0);
        row1.setPercentHeight(60);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(10.0);
        row2.setPercentHeight(40);
        row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        newTaskPane.getRowConstraints().add(row1);

        Label deadlineLabel=new Label(task.getTaskDeadline().getDayOfMonth() + "/"
                + task.getTaskDeadline().getMonthValue() + "/"
                + task.getTaskDeadline().getYear() + " - "
                + task.getTaskDeadline().getHour() + ':' + task.getTaskDeadline().getMinute());
        deadlineLabel.setFont(new Font("Berlin Sans FB", 16.0));
        deadlineLabel.setStyle("-fx-text-fill: white");
        GridPane.setMargin(deadlineLabel,new Insets(3));
        GridPane.setValignment(deadlineLabel, javafx.geometry.VPos.CENTER);
        GridPane.setHalignment(deadlineLabel, javafx.geometry.HPos.CENTER);
        GridPane.setColumnIndex(deadlineLabel,1);
        GridPane.setRowIndex(deadlineLabel,1);

        ImageView imageViewImportant = new ImageView("starUnselected.png");
        if (task.isImportant())imageViewImportant.setImage(new Image("star.png"));
        imageViewImportant.setFitHeight(31.0);
        imageViewImportant.setFitWidth(31.0);
        imageViewImportant.setPickOnBounds(true);
        imageViewImportant.setPreserveRatio(true);
        GridPane.setColumnIndex(imageViewImportant, 4);
        GridPane.setRowSpan(imageViewImportant,2);

        imageViewImportant.setOnMouseClicked(event -> {
            if (task.isImportant()){
                task.setImportant(false);
                allLists.get(1).getTasks().remove(task);
                imageViewImportant.setImage(new Image("starUnselected.png"));
            }
            else{
                task.setImportant(true);
                allLists.get(1).getTasks().add(task);
                imageViewImportant.setImage(new Image("star.png"));

            }
        });

        TextField taskNameLBL = new TextField(task.getTaskName());
        taskNameLBL.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent;");
        taskNameLBL.setEditable(false);
        taskNameLBL.setAlignment(Pos.CENTER);
        GridPane.setHalignment(taskNameLBL, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(taskNameLBL, javafx.geometry.VPos.CENTER);
        taskNameLBL.setFont(new Font("Berlin Sans FB Demi Bold", 22.0));

        ImageView imageViewDelete = new ImageView("deleteWhite.png");
        imageViewDelete.setFitHeight(31.0);
        imageViewDelete.setFitWidth(31.0);
        imageViewDelete.setPickOnBounds(true);
        imageViewDelete.setPreserveRatio(true);
        GridPane.setColumnIndex(imageViewDelete, 3);
        GridPane.setRowSpan(imageViewDelete,2);

        imageViewDelete.setOnMouseClicked(event -> {
            for (toDoTask task1: selectedList.getTasks()){
                if (task.getID()==task1.getID()){
                    selectedList.getTasks().remove(task1);
                    break;
                }
            }
            refreshSidePanelInfo();
            refreshAllListVBox();
        });

        double tasksFinishedPercentage=0;
        if ((task.getSubTasks().size()!=0)){
            tasksFinishedPercentage=(((double) task.getCompletedSubTasks().size() / (double) task.getSubTasks().size()));
        }

        ProgressIndicator progressIndicator = new ProgressIndicator(tasksFinishedPercentage);
        GridPane.setHalignment(progressIndicator, javafx.geometry.HPos.CENTER);
        GridPane.setMargin(progressIndicator, new Insets(3.0, 3.0, 3.0, 3.0));

        ImageView imageView = new ImageView("viewWhite.png");
        imageView.setFitHeight(31.0);
        imageView.setFitWidth(31.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        GridPane.setColumnIndex(taskNameLBL, 1);
        GridPane.setColumnIndex(progressIndicator, 0);
        GridPane.setColumnIndex(imageView, 2);
        GridPane.setRowSpan(imageView,2);
        GridPane.setRowSpan(progressIndicator,2);




        GridPane.setHalignment(imageView, javafx.geometry.HPos.CENTER);
        GridPane.setMargin(imageView, new Insets(2.0, 2.0, 2.0, 2.0));
        taskNameLBL.setOnMouseClicked(event -> {
            selectedTask=task;
            if (event.getClickCount() == 2 &&selectedList.getID()!=1&&selectedList.getID()!=2) {
                taskNameLBL.setEditable(true);
                taskNameLBL.requestFocus();
            }
        });
        imageView.setOnMouseClicked(event -> {
            selectedTask = task;
            try {
                globals.openNewForm("view/subTasks.fxml", selectedTask.getTaskName(),false);
                if ((task.getSubTasks().size()!=0)) {
                    progressIndicator.setProgress((((double) task.getCompletedSubTasks().size() / (double) task.getSubTasks().size())));
                    System.out.println((((double) task.getCompletedSubTasks().size() / (double) task.getSubTasks().size())));
                }
                refreshSidePanelInfo();
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

        newTaskPane.getChildren().addAll(taskNameLBL, progressIndicator, imageView,imageViewDelete,deadlineLabel,imageViewImportant);

        VBox.setMargin(newTaskPane, new Insets(10.0, 10.0, 0, 10.0));
        return newTaskPane;
    }

    public void addNewTask() {
        if (selectedList.getID()==1||selectedList.getID()==2){
            globals.showErrorAlert("You can't add tasks to today or important directly, \ncreate a list to add tasks to \nand today and important lists will be updated accordingly.");
            return;
        }
        toDoTask newToDoTask=new toDoTask(globals.createNewRandomID(globals.toDoTasksIDs),"newTask", false,false, ZonedDateTime.now(),new ArrayList<>());
        selectedList.getTasks().add(newToDoTask);
        GridPane newTask = createNewTaskObject(newToDoTask);

        ongoingTasksVbox.getChildren().add(newTask);
        refreshAllListVBox();

    }

    private void refreshSidePanelInfo() {
        selectedList = allLists.get(findTheToDoList(selectedList));
        activeListNameLBL.setText(selectedList.getListName());
        ongoingTasksVbox.getChildren().clear();
        completedTasksVbox.getChildren().clear();
        Comparator<toDoTask> comparator = Comparator.comparing(toDoTask::getTaskDeadline);
        selectedList.getTasks().sort(comparator);

        for (toDoTask task:selectedList.getTasks()){
            if (task.getSubTasks().size()==task.getCompletedSubTasks().size() && task.getSubTasks().size()!=0)
                completedTasksVbox.getChildren().add(createNewTaskObject(task));

            else
                ongoingTasksVbox.getChildren().add(createNewTaskObject(task));

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
