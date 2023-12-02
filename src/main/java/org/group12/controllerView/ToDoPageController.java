package org.group12.controllerView;

import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.group12.model.toDoSubTask.Globals;
import org.group12.model.toDoSubTask.ToDoList;
import org.group12.model.toDoSubTask.ToDoTask;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * This class is a controller for the ToDo Page.
 * It implements the Initializable interface.
 */
public class ToDoPageController implements Initializable {

    public VBox fixedListsVbox;
    public Label todayToDoLBL;
    public Label importantToDoLBL;
    public VBox appendableListVbox;
    public Label activeListNameLBL;
    public VBox ongoingTasksVbox;
    public VBox completedTasksVbox;
    public GridPane addNewListBtn;
    public BorderPane mainWindowBorder;


    public static ArrayList<ToDoList> allLists = new ArrayList<>();
    public static ToDoList selectedList = new ToDoList();
    public static ToDoTask selectedTask=null;

    /**
     * Initializes the ToDoPageController.
     * It checks if there are any existing lists, and if not, adds the "Today" and "Important" lists.
     * Then, it refreshes the list VBox and side panel information.
     *
     * @param location The URL location.
     * @param resources The ResourceBundle resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (allLists.size()==0){
            allLists.add(new ToDoList(1,"Today", new ArrayList<>()));
            allLists.add(new ToDoList(2,"Important", new ArrayList<>()));
        }
        refreshAllListVBox();
        refreshSidePanelInfo();
    }

    /**
     Creates a new list object in the GUI for the given ToDoList.

     * @param newList The ToDoList for which the list object is created.
     * @return The GridPane representing the new list object.
     */

    public  GridPane createNewListObject(ToDoList newList){
        GridPane listToAppend = createListPane();
        TextField taskNameLBL = createTaskNameLabel(newList);
        Label noOfTasks = createNumberOfTaskLabel(newList);
        setTaskNameLabelEventHandler(newList, taskNameLBL, noOfTasks);
        listToAppend.getChildren().addAll(taskNameLBL, noOfTasks);
        VBox.setMargin(listToAppend, new Insets(10.0, 10.0, 0, 10.0));
        return listToAppend;
    }

    // Refactored createNewListObject
    private TextField createTaskNameLabel(ToDoList newList){
        TextField taskNameLBL = new TextField(newList.getListName());
        taskNameLBL.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent;");
        taskNameLBL.setEditable(false);
        taskNameLBL.setAlignment(Pos.CENTER);
        GridPane.setHalignment(taskNameLBL, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(taskNameLBL, javafx.geometry.VPos.CENTER);
        taskNameLBL.setFont(new Font("Berlin Sans FB Demi Bold", 22.0));
        return taskNameLBL;
    }

    private GridPane createListPane() {
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

        return listToAppend;
    }

    private Label createNumberOfTaskLabel(ToDoList newList){
        Label noOfTasks = new Label(String.valueOf(allLists.get(allLists.indexOf(newList)).getTasks().size()));
        noOfTasks.setAlignment(javafx.geometry.Pos.CENTER);
        noOfTasks.setTextFill(javafx.scene.paint.Color.WHITE);
        GridPane.setColumnIndex(noOfTasks, 1);
        GridPane.setHalignment(noOfTasks, javafx.geometry.HPos.CENTER);
        Font font2 = new Font("Berlin Sans FB Demi Bold", 22.0);
        noOfTasks.setFont(font2);
        return noOfTasks;
    }

    private void setTaskNameLabelEventHandler(ToDoList newList, TextField taskNameLBL, Label noOfTask){
        taskNameLBL.setOnMouseClicked(event -> {
            handleTaskNameLabelClick(event, newList, taskNameLBL, noOfTask);
        });

        taskNameLBL.setOnKeyPressed(event -> {
            handleTaskNameLabelKeyPress(newList, taskNameLBL, noOfTask, event);
        });
    }

    private void handleTaskNameLabelClick(MouseEvent event, ToDoList newList, TextField taskNameLBL, Label noOfTask){
        selectedList = allLists.get(findTheToDoList(newList));
        noOfTask.setText(String.valueOf(allLists.get(findTheToDoList(selectedList)).getTasks().size()));
        refreshSidePanelInfo();
        if (event.getClickCount() == 2 &&selectedList.getID()!=1&&selectedList.getID()!=2) {
            taskNameLBL.setEditable(true);
            taskNameLBL.requestFocus();
        }
    }

    private void handleTaskNameLabelKeyPress(ToDoList newList, TextField taskNameLBL, Label noOfTask, KeyEvent event) {
        selectedList = newList;
        noOfTask.setText(String.valueOf(allLists.get(findTheToDoList(selectedList)).getTasks().size()));

        if (event.getCode() == KeyCode.ENTER) {
            taskNameLBL.setEditable(false);
            renameToDoList(selectedList, taskNameLBL.getText());
        }
    }





    /**
     * Adds a new to-do list to the application.
     */
    public void addNewList() {
        ToDoList newList = new ToDoList(Globals.createNewRandomID(Globals.toDoListsIDs),"New List", new ArrayList<>());
        allLists.add(newList);
        GridPane listToAppend=createNewListObject(newList);

        appendableListVbox.getChildren().add(listToAppend);
    }

    /**
     * Deletes the selected to-do list from the application.
     */
    public void deleteSelectedList(){
        if(selectedList.getID()==1||selectedList.getID()==2){
            Globals.showErrorAlert("Can't delete Today or Important Lists, select a different list.");
            return;
        }
        for (ToDoList list:allLists){
            if (list.getID()==selectedList.getID()){
                allLists.remove(list);
                break;
            }
        }
        refreshAllListVBox();
        refreshSidePanelInfo();
    }

    /**
     * Refreshes the VBox that contains all the to-do lists in the UI.
     */
    public void refreshAllListVBox(){
        selectedList=allLists.get(0);
        fixedListsVbox.getChildren().clear();
        appendableListVbox.getChildren().clear();
        for (ToDoList list:allLists){
            if (list.getID()==1||list.getID()==2){
                fixedListsVbox.getChildren().add(createNewListObject(list));
            }
            else{
                allLists.get(0).getTasks().clear();
                allLists.get(1).getTasks().clear();
                for (ToDoTask task:list.getTasks()){
                    if (task.getTaskDeadline().getDayOfMonth()== ZonedDateTime.now().getDayOfMonth()
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

        /**
         * Renames the given to-do list with a new name.
         *
         * @param list    The to-do list to rename.
         * @param newName The new name for the list.
         */
    public  void renameToDoList(ToDoList list, String newName) {
        if (allLists.get(findTheToDoList(list)).getID()==1||allLists.get(findTheToDoList(list)).getID()==0)return;
        allLists.get(findTheToDoList(list)).setListName(newName);
        refreshSidePanelInfo();
    }

    /**
     * Renames the given to-do task with a new name.
     *
     * @param task    The to-do task to rename.
     * @param newName The new name for the task.
     */
    void renameTask(ToDoTask task, String newName){
        allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(task)).setTaskName(newName);
    }

    /**
     * Finds the index of the given to-do list in the allLists collection.
     *
     * @param list The to-do list to find.
     * @return The index of the list in the allLists collection, or -1 if not found.
     */
    public static int findTheToDoList(ToDoList list) {
        for (ToDoList list1 : allLists) {
            if (list1.getID() == list.getID()) return allLists.indexOf(list);
        }
        return -1;
    }


    /**
     * Finds the index of the given to-do task in the selectedList's tasks.
     *
     * @param task The to-do task to find.
     * @return The index of the task in the selectedList's tasks, or -1 if not found.
     */
    public static int findTheTask(ToDoTask task) {
        selectedList=allLists.get(findTheToDoList(selectedList));
        for (ToDoTask task1 : selectedList.getTasks()) {
            if (task1.getID() == task.getID()) return selectedList.getTasks().indexOf(task1);
        }
        return -1;
    }

    public  GridPane createNewTaskObject(ToDoTask task) {

        GridPane newTaskPane = createTaskPane();
        Label deadLineLabel = createDeadlineLabel(task);
        ImageView imageViewImportant = createImportantImageView(task);
        ImageView imageViewDelete = createDeleteImageView(task);
        ImageView imageView = createViewImageView(task);
        TextField taskNameLabel = createTaskNameTextField(task);
        ProgressIndicator progressIndicator = createProgressIndicator(task);
        setTaskNameEditEvent(taskNameLabel, task);

        setViewEvent(imageView, task, progressIndicator);

        imageViewImportant.setOnMouseClicked(event -> {
            if (task.isImportant()){
                task.setImportant(false);
                allLists.get(1).getTasks().remove(task);
                imageViewImportant.setImage(new Image("/starUnselected.png"));
            }
            else{
                task.setImportant(true);
                allLists.get(1).getTasks().add(task);
                imageViewImportant.setImage(new Image("/star.png"));

            }
        });


        imageViewDelete.setOnMouseClicked(event -> {
            for (ToDoTask task1: selectedList.getTasks()){
                if (task.getID()==task1.getID()){
                    selectedList.getTasks().remove(task1);
                    break;
                }
            }
            refreshSidePanelInfo();
            refreshAllListVBox();
        });



        GridPane.setColumnIndex(taskNameLabel, 1);
        GridPane.setColumnIndex(progressIndicator, 0);
        GridPane.setRowSpan(progressIndicator,2);


        GridPane.setHalignment(imageView, HPos.CENTER);
        GridPane.setMargin(imageView, new Insets(2.0, 2.0, 2.0, 2.0));
        taskNameLabel.setOnMouseClicked(event -> {
            selectedTask=task;
            if (event.getClickCount() == 2 &&selectedList.getID()!=1&&selectedList.getID()!=2) {
                taskNameLabel.setEditable(true);
                taskNameLabel.requestFocus();
            }
        });
        imageView.setOnMouseClicked(event -> {
            selectedTask = task;
            try {
                Globals.openNewForm("/org/group12/view/subTasks.fxml", selectedTask.getTaskName(),false);
                if ((task.getSubTasks().size()!=0)) {
                    progressIndicator.setProgress((((double) task.getCompletedSubTasks().size() / (double) task.getSubTasks().size())));
                    System.out.println((((double) task.getCompletedSubTasks().size() / (double) task.getSubTasks().size())));
                }
                refreshSidePanelInfo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        taskNameLabel.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                taskNameLabel.setEditable(false);
                renameTask(allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(task)), taskNameLabel.getText());
            }
        });

        newTaskPane.getChildren().addAll(taskNameLabel, progressIndicator, imageView,imageViewDelete,deadLineLabel,imageViewImportant);

        VBox.setMargin(newTaskPane, new Insets(10.0, 10.0, 0, 10.0));
        return newTaskPane;
    }

    private GridPane createTaskPane(){
        GridPane newTaskPane = new GridPane();
        newTaskPane.setMinHeight(70.0);
        newTaskPane.setMinWidth(250.0);
        newTaskPane.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-color: white; -fx-border-radius: 10;");
        newTaskPane.getColumnConstraints().addAll(
                createColumnConstraints(12.5, 216),
                createColumnConstraints(50.0,216),
                createColumnConstraints(12.5, 104),
                createColumnConstraints(12.5, 104),
                createColumnConstraints(12.5, 104)
        );
        newTaskPane.getRowConstraints().addAll(
                createRowConstraints(60),
                createRowConstraints(40)
        );
        return newTaskPane;
    }

    private ColumnConstraints createColumnConstraints(double width, double prefWidth){
        double minWidth = 10.0;
        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.SOMETIMES);
        col.setMinWidth(minWidth);
        col.setPercentWidth(width);
        col.setPrefWidth(prefWidth);
        return col;
    }

    private RowConstraints createRowConstraints(double height){
        double minHeight = 10.0;
        RowConstraints row = new RowConstraints();
        row.setMinHeight(minHeight);
        row.setPercentHeight(height);
        row.setPrefHeight(height);
        return row;
    }

    private Label createDeadlineLabel(ToDoTask task){
        Label deadlineLabel = new Label(task.getTaskDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyy - HH:mm")));
        deadlineLabel.setFont(new Font("Berlin Sans FB", 16.0));
        deadlineLabel.setStyle("-fx-text-fill: white");
        GridPane.setMargin(deadlineLabel,new Insets(3));
        GridPane.setValignment(deadlineLabel, javafx.geometry.VPos.CENTER);
        GridPane.setHalignment(deadlineLabel, javafx.geometry.HPos.CENTER);
        GridPane.setColumnIndex(deadlineLabel,1);
        GridPane.setRowIndex(deadlineLabel,1);
        return deadlineLabel;
    }

    private ImageView createImportantImageView(ToDoTask task){
        ImageView imageViewImportant = new ImageView(task.isImportant() ? "/star.png" : "/starUnselected.png");
        imageViewImportant.setFitHeight(31.0);
        imageViewImportant.setFitWidth(31.0);
        imageViewImportant.setPickOnBounds(true);
        imageViewImportant.setPreserveRatio(true);
        GridPane.setColumnIndex(imageViewImportant, 4);
        GridPane.setRowSpan(imageViewImportant,2);
        return imageViewImportant;
    }

    private ImageView createDeleteImageView(ToDoTask task){
        ImageView imageViewDelete = new ImageView("/deleteWhite.png");
        imageViewDelete.setFitHeight(31.0);
        imageViewDelete.setFitWidth(31.0);
        imageViewDelete.setPickOnBounds(true);
        imageViewDelete.setPreserveRatio(true);
        GridPane.setColumnIndex(imageViewDelete, 3);
        GridPane.setRowSpan(imageViewDelete,2);
        return imageViewDelete;
    }

    private TextField createTaskNameTextField(ToDoTask task){
        TextField taskNameLBL = new TextField(task.getTaskName());
        taskNameLBL.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent;");
        taskNameLBL.setEditable(false);
        taskNameLBL.setAlignment(Pos.CENTER);
        taskNameLBL.setFont(new Font("Berlin Sans FB Demi Bold", 22.0));
        GridPane.setHalignment(taskNameLBL, HPos.CENTER);
        GridPane.setValignment(taskNameLBL, VPos.CENTER);
        return taskNameLBL;
    }

    private ProgressIndicator createProgressIndicator(ToDoTask task){
        double tasksFinishPrecentage = calculateTaskPrecentage(task);
        ProgressIndicator progressIndicator = new ProgressIndicator(tasksFinishPrecentage);
        GridPane.setHalignment(progressIndicator, HPos.CENTER);
        GridPane.setMargin(progressIndicator, new Insets(3.0, 3.0, 3.0, 3.0));
        return progressIndicator;
    }

    private double calculateTaskPrecentage(ToDoTask task) {
        double precentageCompleted = 0;
        if(!task.getSubTasks().isEmpty()){
            precentageCompleted = (double) task.getCompletedSubTasks().size() / task.getSubTasks().size();
        } else{
            return 0;
        }
        return precentageCompleted;
    }

    private ImageView createViewImageView(ToDoTask task){
        ImageView imageView = new ImageView("/viewWhite.png");
        imageView.setFitHeight(31.0);
        imageView.setFitWidth(31.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        GridPane.setColumnIndex(imageView, 2);
        GridPane.setRowSpan(imageView,2);

        return imageView;
    }

    private void setTaskNameEditEvent(TextField taskNameLabel, ToDoTask task){
        taskNameLabel.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2 && selectedList.getID() != 1 && selectedList.getID() != 2){
                taskNameLabel.setEditable(true);
                taskNameLabel.requestFocus();
            }
        });

        taskNameLabel.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                taskNameLabel.setEditable(false);
                renameTask((allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(task))), taskNameLabel.getText());
            }
        });
    }

    private void setViewEvent(ImageView imageView, ToDoTask task, ProgressIndicator progressIndicator){
        imageView.setOnMouseClicked(event -> {
            selectedTask = task;
            try {
                Globals.openNewForm("/org/group12/view/subTasks.fxml", selectedTask.getTaskName(),false);
                if ((!task.getSubTasks().isEmpty())) {
                    progressIndicator.setProgress((((double) task.getCompletedSubTasks().size() / (double) task.getSubTasks().size())));
                    System.out.println((((double) task.getCompletedSubTasks().size() / (double) task.getSubTasks().size())));
                }
                refreshSidePanelInfo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }


    /**
     * Adds a new task to the selected list.
     * Displays an error message if the selected list is "today" or "important".
     */
    public void addNewTask() {
        if (selectedList.getID()==1||selectedList.getID()==2){
            Globals.showErrorAlert("You can't add tasks to today or important directly, \ncreate a list to add tasks to \nand today and important lists will be updated accordingly.");
            return;
        }
        ToDoTask newToDoTask=new ToDoTask(Globals.createNewRandomID(Globals.toDoTasksIDs),"newTask", false,false, ZonedDateTime.now(),new ArrayList<>());
        selectedList.getTasks().add(newToDoTask);
        GridPane newTask = createNewTaskObject(newToDoTask);

        ongoingTasksVbox.getChildren().add(newTask);
        refreshAllListVBox();

    }


    /**
     * Refreshes the side panel information based on the selected list.
     * Updates the active list name label, ongoing tasks VBox, and completed tasks VBox.
     */
    public  void refreshSidePanelInfo() {
        selectedList=allLists.get(findTheToDoList(selectedList));
        activeListNameLBL.setText(selectedList.getListName());
        ongoingTasksVbox.getChildren().clear();
        completedTasksVbox.getChildren().clear();
        Comparator<ToDoTask> comparator = Comparator.comparing(ToDoTask::getTaskDeadline);
        selectedList.getTasks().sort(comparator);

        for (ToDoTask task:selectedList.getTasks()){
            if (task.getSubTasks().size()==task.getCompletedSubTasks().size() && task.getSubTasks().size()!=0)
                completedTasksVbox.getChildren().add(createNewTaskObject(task));

            else
                ongoingTasksVbox.getChildren().add(createNewTaskObject(task));

        }

    }
}
