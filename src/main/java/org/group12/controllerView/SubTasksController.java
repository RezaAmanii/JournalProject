package org.group12.controllerView;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.group12.model.toDoSubTask.Globals;
import org.group12.model.toDoSubTask.SubTask;


import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

import static org.group12.controllerView.ToDoPageController.*;

/**
 * Controller class for managing subtasks in the application.
 */
public class SubTasksController implements Initializable {

    static public SubTask selectedSubTask=null;

    public Label taskNameLBL;
    public VBox subTasksPane;
    public AnchorPane deletePane;
    public ImageView deleteImg;
    public TextField deadlineTF;
    public AnchorPane addPane;
    public ImageView addImg;
    public Spinner<Integer> hrSpinner;
    public Spinner<Integer> minSpinner;
    public AnchorPane savePaneBTN;
    public DatePicker changeDeadlineDP;
    ZonedDateTime deadline= selectedTask.getTaskDeadline();

    /**
     * Initializes the subtask view.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskNameLBL.setText(selectedTask.getTaskName());
        hrSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,ZonedDateTime.now().getHour()));
        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,ZonedDateTime.now().getMinute()));
        changeDeadlineDP.setValue(LocalDate.now());

        deadlineTF.setText(deadline.getDayOfMonth() + "/" + deadline.getMonthValue() + "/" + deadline.getYear() + " - " + deadline.getHour() + ':' + deadline.getMinute());
        refreshSubTasksPane();
    }

    /**
     * Saves the new deadline for the task.
     */
    public void saveNewDeadline(){
        ZonedDateTime dd=ZonedDateTime.now();
        deadline= ZonedDateTime.of(changeDeadlineDP.getValue().getYear()
                , changeDeadlineDP.getValue().getMonthValue()
                , changeDeadlineDP.getValue().getDayOfMonth()
                , hrSpinner.getValue(), minSpinner.getValue()
                , 0, 0, dd.getZone());
        deadlineTF.setText(deadline.getDayOfMonth() + "/"
                + deadline.getMonthValue() + "/"
                + deadline.getYear() + " - "
                + deadline.getHour() + ':' + deadline.getMinute());
        allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(selectedTask)).setTaskDeadline(deadline);
        refreshSubTasksPane();

    }

    /**
     * Removes the selected subtask.
     */
    public void removeSubTask(){
        if (selectedSubTask!=null)
            allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(selectedTask)).getSubTasks().remove(selectedSubTask);
        refreshSubTasksPane();
    }

    /**
     * Adds a new subtask.
     */
    public void addNewSubTask(){
        SubTask subTask=new SubTask(Globals.createNewSeqID(Globals.toDoSubTasksIDs),"newSubTask",false);
        selectedSubTask=subTask;
        allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(selectedTask)).getSubTasks().add(subTask);
        subTasksPane.getChildren().add(createNewSubTaskObject(subTask));
        refreshSubTasksPane();

    }

    /**
     * Refreshes the subtasks pane.
     */
    void refreshSubTasksPane(){
        subTasksPane.getChildren().clear();

        for (SubTask task: selectedTask.getUnfinishedSubTasks()){
            subTasksPane.getChildren().add(createNewSubTaskObject(task));
        }
        for (SubTask task: selectedTask.getCompletedSubTasks()){
            subTasksPane.getChildren().add(createNewSubTaskObject(task));
        }
    }

    /**
     * Finds the index of the specified subtask within the selected task's subtasks.
     *
     * @param task The subtask to find.
     * @return The index of the subtask if found, otherwise -1.
     */
    public static int findTheSubTask(SubTask task) {
        selectedList=allLists.get(findTheToDoList(selectedList));
        selectedTask=selectedList.getTasks().get(findTheTask(selectedTask));
        for (SubTask task1 : selectedTask.getSubTasks()) {
            if (task1.getID() == task.getID()) return selectedTask.getSubTasks().indexOf(task1);
        }
        return -1;
    }

    /**
     * Renames the specified subtask with a new name.
     *
     * @param task     The subtask to rename.
     * @param newName  The new name for the subtask.
     */
    void renameSubTask(SubTask task, String newName){
        allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(selectedTask)).getSubTasks().get(findTheSubTask(task)).setSubTaskName(newName);
    }

    /**
     * Creates a new subtask object.
     *
     * @param task The subtask to create the object for.
     * @return The created GridPane object representing the subtask.
     */
    GridPane createNewSubTaskObject(SubTask task){
        GridPane newTaskPane=new GridPane();
        newTaskPane.setMinHeight(57.0);
        newTaskPane.setMinWidth(306.0);
        newTaskPane.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: white;");

        // Column Constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMaxWidth(148.111083984375);
        col1.setMinWidth(10.0);
        col1.setPercentWidth(16.0);
        col1.setPrefWidth(48.888875325520836);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMaxWidth(266.3333053588867);
        col2.setMinWidth(10.0);
        col2.setPercentWidth(84.0);
        col2.setPrefWidth(215.11112467447913);

        newTaskPane.getColumnConstraints().addAll(col1, col2);

        // Row Constraints
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        newTaskPane.getRowConstraints().add(row1);

        // VBox.margin
        VBox.setMargin(newTaskPane, new Insets(10.0, 10.0, 10.0, 10.0));

        // Children
        Pane pane = new Pane();
        pane.setMaxHeight(35.0);
        pane.setMaxWidth(31.0);
        pane.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        GridPane.setHalignment(pane, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(pane, javafx.geometry.VPos.CENTER);
        CheckBox checkBox = new CheckBox();
        checkBox.setLayoutX(1.0);
        checkBox.setMnemonicParsing(false);
        checkBox.setPrefHeight(35.0);
        checkBox.setPrefWidth(32.0);
        checkBox.setSelected(task.isFinished());

        checkBox.setOnMouseClicked(event -> {
            allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(selectedTask)).getSubTasks().get(findTheSubTask(task)).setFinished(checkBox.isSelected());
            refreshSubTasksPane();
        });

        checkBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        pane.getChildren().add(checkBox);
        GridPane.setMargin(pane, new Insets(10.0, 10.0, 10.0, 10.0));
        pane.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));

        TextField subTaskTF = new TextField(task.getSubTaskName());
        subTaskTF.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent;");
        subTaskTF.setEditable(false);
        subTaskTF.setAlignment(Pos.CENTER);

        GridPane.setColumnIndex(subTaskTF, 1);
        GridPane.setValignment(subTaskTF, javafx.geometry.VPos.CENTER);
        GridPane.setMargin(subTaskTF, new Insets(0, 0, 0, 5.0));
        subTaskTF.setFont(new Font("Berlin Sans FB Demi Bold", 29.0));
        subTaskTF.setOnMouseClicked(event -> {
            selectedSubTask = task;
            if (event.getClickCount() == 2) {
                subTaskTF.setEditable(true);
                subTaskTF.requestFocus();
            }
        });
        subTaskTF.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                subTaskTF.setEditable(false);
                renameSubTask(task,subTaskTF.getText());
            }
        });
        newTaskPane.getChildren().addAll(subTaskTF,pane);

        return newTaskPane;
    }
}