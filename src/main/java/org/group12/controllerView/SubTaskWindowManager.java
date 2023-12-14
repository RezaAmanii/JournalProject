package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.group12.Listeners.SubTaskCardClickListener;
import org.group12.Observers.ITaskListObserver;
import org.group12.controller.BigTaskController;
import org.group12.controller.TaskController;
import org.group12.model.Items;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import org.group12.view.SubTaskCard;
import java.net.URL;
import java.util.*;
import static org.group12.controllerView.ToDoWindowManager.*;
import static org.group12.view.TaskListView.getInputFromUser;



public class SubTaskWindowManager implements Initializable, ITaskListObserver, SubTaskCardClickListener {


    // Controller
    private final BigTaskController bigTaskController = BigTaskController.getInstance();
    private final TaskController taskController = TaskController.getInstance();


    // Reference to the last clicked subtask cards
    private SubTaskCard lastClickedSubTaskCard;

    
    // FXMl Components
    @FXML public Label taskNameLabel;
    @FXML public VBox subTasksPane;
    @FXML public AnchorPane addPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bigTaskController.addObserver(this);
        if(lastClickedBigTaskCard != null) {
            taskNameLabel.setText(bigTaskController.getBigTaskByID(lastClickedBigTaskCard.getID()).getTitle());
        } else{
            taskNameLabel.setText("No task selected");
        }
        update();
    }

    // Add methods
    public SubTaskCard createNewSubTaskObject(ITask task){
        SubTaskCard newSubTaskCard = new SubTaskCard(task.getID(), Items.getInstance());
        newSubTaskCard.setSubTaskCardListener(this);

        return newSubTaskCard;
    }

    public void addNewSubTask(){
        String title = getInputFromUser();

        if(lastClickedBigTaskCard != null){
            taskController.handleAddSubTask(title);
            update();
        }
    }



    void refreshSubTasksPane(){

        subTasksPane.getChildren().clear();
        IBigTask bigTask = bigTaskController.getBigTaskByID(lastClickedBigTaskCard.getID());
        if(bigTask != null){
            for (ITask task: taskController.getAllSubTasks()) {
                subTasksPane.getChildren().add(createNewSubTaskObject(task));
            }
        }

    }

    // Observer update method
    @Override
    public void update() {
        refreshSubTasksPane();
    }


    // On card clicked method
    @Override
    public void onSubTaskCardClicked(SubTaskCard subTaskCard) {
        lastClickedSubTaskCard = subTaskCard;
    }
}