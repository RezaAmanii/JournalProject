package org.group12.controllerView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
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
import org.group12.Listeners.SubTaskCardClickListener;
import org.group12.Observers.ITaskListObserver;
import org.group12.controller.BigTaskController;
import org.group12.controller.TaskController;
import org.group12.model.Items;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import org.group12.model.todo.ITaskList;
import org.group12.view.BigTaskCard;
import org.group12.view.SubTaskCard;
import org.group12.view.TaskListCards;


import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

import static org.group12.controllerView.ToDoWindowManager.*;
import static org.group12.view.TaskListView.getInputFromUser;
import static org.group12.view.TaskView.*;


public class SubTaskWindowManager implements Initializable, ITaskListObserver, SubTaskCardClickListener {

    // Class attributes
    private ITask selectedSubTask = null;


    // Controller
    private final BigTaskController bigTaskController = BigTaskController.getInstance();
    private final TaskController taskController = TaskController.getInstance();



    // Reference to the last clicked subtask cards
    private static SubTaskCard lastClickedSubTaskCard;

    
    // FXMl Components
    @FXML public Label taskNameLabel;
    @FXML public VBox subTasksPane;
    @FXML public ImageView deleteImg;
    @FXML public AnchorPane addPane;
    @FXML public CheckBox statusCheckBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(lastClickedBigTaskCard != null) {
            taskNameLabel.setText(bigTaskController.getBigTaskByID(lastClickedBigTaskCard.getID()).getTitle());
        } else{
            taskNameLabel.setText("No task selected");
        }
    }

    // Add methods
    public SubTaskCard createNewSubTaskObject(ITask task){
        SubTaskCard subTaskCard = new SubTaskCard(task.getID(), Items.getInstance());
        subTaskCard.setSubTaskCardListener(this);

        return subTaskCard;
    }

    public void addNewSubTask(){
        String title = getInputFromUser();

        if(lastClickedBigTaskCard != null){
            taskController.handleAddSubTask(title);
            update();
        }
    }


    void renameSubTask(ITask task, String newName){
        taskController.renameSubTask(task.getID(), newName);
        update();
    }



    void refreshSubTasksPane(){

        subTasksPane.getChildren().clear();


        IBigTask bigTask = bigTaskController.getBigTaskByID(lastClickedBigTaskCard.getID());

        for (ITask task: bigTask.getUncompletedSubTasks()){
            subTasksPane.getChildren().add(createNewSubTaskObject(task));
        }
        for (ITask task: bigTask.getCompletedSubTasks()){
            subTasksPane.getChildren().add(createNewSubTaskObject(task));
        }
    }


    @Override
    public void update() {
        refreshSubTasksPane();
    }


    // View




    /*
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

     */

    private void handleSubTaskTextFieldKeyPress(ITask task, KeyEvent event, TextField subTaskTF) {
        if (event.getCode() == KeyCode.ENTER) {
            subTaskTF.setEditable(false);
            renameSubTask(task, subTaskTF.getText());
        }
    }

    private void handleSubTaskTestFieldClick(ITask task, MouseEvent event, TextField subTaskTF) {
        selectedSubTask = task;
        if (event.getClickCount() == 2) {
            subTaskTF.setEditable(true);
            subTaskTF.requestFocus();
        }
    }

    @Override
    public void onSubTaskCardClicked(SubTaskCard subTaskCard) {
        lastClickedSubTaskCard = subTaskCard;
    }
}