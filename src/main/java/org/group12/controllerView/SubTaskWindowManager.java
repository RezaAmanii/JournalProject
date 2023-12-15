package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.group12.Listeners.SubTaskCardClickListener;
import org.group12.Observers.ITodoObserver;
import org.group12.controller.BigTaskController;
import org.group12.controller.SubTaskController;
import org.group12.model.Items;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import org.group12.view.SubTaskCard;
import org.group12.view.SubTaskView;
import java.net.URL;
import java.util.*;
import static org.group12.controllerView.ToDoWindowManager.*;



/**
 * Manages the display and actions related to subtasks within a task.
 */
public class SubTaskWindowManager implements Initializable, ITodoObserver, SubTaskCardClickListener {


    // Corresponding Controllers
    private final BigTaskController bigTaskController = BigTaskController.getInstance();
    private final SubTaskController subTaskController = SubTaskController.getInstance();

    // Corresponding View
    private final SubTaskView subTaskView = new SubTaskView();


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

    /**
     * Creates a new SubTaskCard object for a given task.
     *
     * @param task The task for which a SubTaskCard needs to be created.
     * @return The created SubTaskCard object.
     */
    public SubTaskCard createNewSubTaskObject(ITask task){
        SubTaskCard newSubTaskCard = new SubTaskCard(task.getID(), Items.getInstance(), subTasksPane);
        newSubTaskCard.setSubTaskCardListener(this);

        return newSubTaskCard;
    }

    /**
     * Adds a new subtask to the currently selected big task.
     */
    public void addNewSubTask(){
        String title = subTaskView.getInputFromUser();

        if(lastClickedBigTaskCard != null){
            subTaskController.handleAddSubTask(title);
            update();
        }
    }


    /**
     * Refreshes the subTasksPane with the updated subtasks related to the currently selected big task.
     */
    void refreshSubTasksPane(){

        subTasksPane.getChildren().clear();
        IBigTask bigTask = bigTaskController.getBigTaskByID(lastClickedBigTaskCard.getID());
        if(bigTask != null){
            for (ITask task: subTaskController.getAllSubTasks()) {
                subTasksPane.getChildren().add(createNewSubTaskObject(task));
            }
        }
    }

    /**
     * Updates the display of subtasks when there's a change.
     */
    @Override
    public void update() {
        refreshSubTasksPane();
    }


    /**
     * Sets the last clicked subtask card.
     *
     * @param subTaskCard The last clicked SubTaskCard object.
     */
    @Override
    public void onSubTaskCardClicked(SubTaskCard subTaskCard) {
        lastClickedSubTaskCard = subTaskCard;
    }
}