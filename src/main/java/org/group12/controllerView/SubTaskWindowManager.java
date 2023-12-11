package org.group12.controllerView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.group12.controller.BigTaskController;
import org.group12.controller.TaskController;
import org.group12.model.todo.ITask;



import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

import static org.group12.controllerView.ToDoWindowManager.*;
import static org.group12.view.TaskView.*;

/**
 * Controller class for managing subtasks in the application.
 */
public class SubTaskWindowManager implements Initializable {

    static public ITask selectedSubTask = null;
    private final BigTaskController bigTaskController = BigTaskController.getInstance();
    private final TaskController taskController = TaskController.getInstance();
    
    // FXMl Components
    public Label taskNameLabel;
    public VBox subTasksPane;
    public ImageView deleteImg;
    public AnchorPane addPane;
    public CheckBox statusCheckBox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskNameLabel.setText(selectedTask.getTitle());
        this.deleteImg.setVisible(true);
        Image deleteImg = new Image("deleteWhite.png");
        this.deleteImg.setImage(deleteImg);



        //deadlineTF.setText(deadline.get().getDayOfMonth() + "/" + deadline.get().getMonthValue() + "/" + deadline.get().getYear() + " - " + deadline.get().getHour() + ':' + deadline.get().getMinute());
        refreshSubTasksPane();
    }


    public GridPane createNewSubTaskObject(ITask task){
        GridPane newTaskPane = createNewTaskPane();
        Pane checkBoxPane = createCheckBoxPane(task);
        TextField subTaskTF = createSubTaskTextField(task);
        newTaskPane.getChildren().addAll(subTaskTF, checkBoxPane);
        return newTaskPane;
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
            handleSubTaskTestFieldClick(task, event, subTaskTF);
        });
        subTaskTF.setOnKeyPressed(event -> {
            handleSubTaskTextFieldKeyPress(task, event, subTaskTF);
        });
        return subTaskTF;
    }

    private void handleSubTaskTextFieldKeyPress(ITask task, KeyEvent event, TextField subTaskTF) {
        if (event.getCode() == KeyCode.ENTER) {
            subTaskTF.setEditable(false);
            renameSubTask(task, subTaskTF.getText());
        }
    }

    private static void handleSubTaskTestFieldClick(ITask task, MouseEvent event, TextField subTaskTF) {
        selectedSubTask = task;
        if (event.getClickCount() == 2) {
            subTaskTF.setEditable(true);
            subTaskTF.requestFocus();
        }
    }

    public void addNewSubTask(){
        String title = getInputFromUser();
        String subTaskID = selectedTask.addSubTask(title);

        selectedSubTask = taskController.getSubTaskByID(subTaskID);
        subTasksPane.getChildren().add(createNewSubTaskObject(selectedSubTask));
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

    void renameSubTask(ITask task, String newName){
        taskController.getSubTaskByID(task.getID()).setTitle(newName);
    }



    void refreshSubTasksPane(){

        subTasksPane.getChildren().clear();

        for (ITask task: selectedTask.getUncompletedSubTasks()){
            subTasksPane.getChildren().add(createNewSubTaskObject(task));
        }
        for (ITask task: selectedTask.getCompletedSubTasks()){
            subTasksPane.getChildren().add(createNewSubTaskObject(task));
        }
    }





}