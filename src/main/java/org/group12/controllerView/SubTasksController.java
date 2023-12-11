package org.group12.controllerView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.group12.controller.BigTaskController;
import org.group12.controller.TaskController;
import org.group12.model.toDoSubTask.Globals;
import org.group12.model.toDoSubTask.SubTask;
import org.group12.model.toDoSubTask.ToDoTask;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import org.group12.model.todo.ITaskList;


import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

import static org.group12.controllerView.ToDoWindowManager.*;

/**
 * Controller class for managing subtasks in the application.
 */
public class SubTasksController implements Initializable {

    static public ITask selectedSubTask = null;
    private final BigTaskController bigTaskController = BigTaskController.getInstance();
    private final TaskController taskController = TaskController.getInstance();
    
    // FXMl Components
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
    SimpleObjectProperty<LocalDateTime> deadline = new SimpleObjectProperty<LocalDateTime>(this, "deadline");

    /**
     * Initializes the subtask view.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskNameLBL.setText(selectedTask.getTitle());
        hrSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,ZonedDateTime.now().getHour()));
        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,ZonedDateTime.now().getMinute()));
        changeDeadlineDP.setValue(LocalDate.now());
        if(deadline != null && deadline.get() != null){
            deadlineTF.setText(deadline.get().getDayOfMonth() + "/" + deadline.get().getMonthValue() + "/" + deadline.get().getYear() + " - " + deadline.get().getHour() + ':' + deadline.get().getMinute());
        }else{
            deadline.set(LocalDateTime.now());
        }
        //deadlineTF.setText(deadline.get().getDayOfMonth() + "/" + deadline.get().getMonthValue() + "/" + deadline.get().getYear() + " - " + deadline.get().getHour() + ':' + deadline.get().getMinute());
        refreshSubTasksPane();
    }

    /**
     * Saves the new deadline for the task.
     */
    public void saveNewDeadline(){
        deadline.set(LocalDateTime.of(changeDeadlineDP.getValue().getYear()
                , changeDeadlineDP.getValue().getMonthValue()
                , changeDeadlineDP.getValue().getDayOfMonth()
                , hrSpinner.getValue(), minSpinner.getValue()
                , 0, 0));
        deadlineTF.setText(deadline.get().getDayOfMonth() + "/"
                + deadline.get().getMonthValue() + "/"
                + deadline.get().getYear() + " - "
                + deadline.get().getHour() + ':' + deadline.get().getMinute());
        //allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(selectedTask)).setTaskDeadline(deadline);
        refreshSubTasksPane();

    }

    
    public void removeSubTask(){
        ITask subTask = taskController.getSubTaskByID(selectedSubTask.getID());
        if(subTask != null){
            subTask.setTitle("Removed");
            bigTaskController.getBigTaskByID(selectedTask.getID()).removeSubTask(selectedSubTask.getID());
            refreshSubTasksPane();
        }

    }

    public String getInputFromUser(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New SubTask");
        dialog.setHeaderText("Enter the name of the new SubTask");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return "New subtask";
    }



    public void addNewSubTask(){
        String title = getInputFromUser();
        String subTaskID = selectedTask.addSubTask(title);
        ITask newSubTask = taskController.getSubTaskByID(subTaskID);

        selectedSubTask = newSubTask;
        subTasksPane.getChildren().add(createNewSubTaskObject(selectedSubTask));
        refreshSubTasksPane();

    }

    


    /**
     * Refreshes the subtasks pane.
     */
    void refreshSubTasksPane(){

        subTasksPane.getChildren().clear();

        for (ITask task: selectedTask.getUncompletedSubTasks()){
            subTasksPane.getChildren().add(createNewSubTaskObject(task));
        }
        for (ITask task: selectedTask.getCompletedSubTasks()){
            subTasksPane.getChildren().add(createNewSubTaskObject(task));
        }


    }

    public GridPane createNewSubTaskObject(ITask task){
        GridPane newTaskPane = createNewTaskPane();
        Pane checkBoxPane = createCheckBoxPane(task);
        TextField subTaskTF = createSubTaskTextField(task);

        newTaskPane.getChildren().addAll(subTaskTF, checkBoxPane);
        return newTaskPane;
    }


    void renameSubTask(ITask task, String newName){
        taskController.getSubTaskByID(task.getID()).setTitle(newName);
    }



    private Pane createCheckBoxPane(ITask task){
        Pane pane = new Pane();
        pane.setMaxHeight(35.0);
        pane.setMaxWidth(31.0);
        pane.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        CheckBox checkBox = getCheckBox(task);
        pane.getChildren().add(checkBox);
        GridPane.setMargin(pane, new Insets(10.0, 10.0, 10.0, 10.0));
        pane.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));

        GridPane.setHalignment(pane, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(pane, javafx.geometry.VPos.CENTER);
        return pane;
    }

    private CheckBox getCheckBox(ITask task) {
        CheckBox checkBox = new CheckBox();
        checkBox.setLayoutX(1.0);
        checkBox.setMnemonicParsing(false);
        checkBox.setPrefHeight(35.0);
        checkBox.setPrefWidth(32.0);
        checkBox.setSelected(task.getStatus());

        checkBox.setOnMouseClicked(event -> {
            //allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(selectedTask)).getSubTasks().get(findTheSubTask(task)).setFinished(checkBox.isSelected());
            refreshSubTasksPane();
        });

        checkBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        return checkBox;
    }

    private TextField createSubTaskTextField(ITask task){
        TextField subTaskTF = new TextField(task.getTitle());
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
        return subTaskTF;
    }

    private void addColumnConstraints(GridPane pane){
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
        pane.getColumnConstraints().addAll(col1, col2);
    }

    private void addRowConstraints(GridPane pane){
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        pane.getRowConstraints().add(row1);
    }



    private GridPane createNewTaskPane() {
        GridPane newTaskPane=new GridPane();
        newTaskPane.setMinHeight(57.0);
        newTaskPane.setMinWidth(306.0);
        newTaskPane.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: white;");
        addColumnConstraints(newTaskPane);
        addRowConstraints(newTaskPane);
        VBox.setMargin(newTaskPane, new Insets(10.0, 10.0, 10.0, 10.0));
        return newTaskPane;
    }



}