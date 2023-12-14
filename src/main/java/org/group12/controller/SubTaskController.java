package org.group12.controller;


import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.controllerView.ToDoWindowManager;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.group12.model.Items;

/**
 * Controls and manages operations related to subtasks in the application.
 * Implements IObservable for observing changes.
 */
public class SubTaskController implements IController, IObservable {

    // Attributes
    private final ItemsSet itemSet;
    private static SubTaskController instance;
    private final List<IPlanITObserver> observers;


    // Controller
    private final BigTaskController bigTaskController;


    // Constructor
    private SubTaskController(){
        this.itemSet = Items.getInstance();
        this.bigTaskController = BigTaskController.getInstance();
        this.observers = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of SubTaskController.
     * @return The singleton instance of SubTaskController.
     */
    public static SubTaskController getInstance(){
        if (instance == null){
            instance = new SubTaskController();
        }
        return instance;
    }


    /**
     * Retrieves a subtask by its unique identifier.
     * @param taskID The identifier of the subtask.
     * @return The subtask with the given ID if found; otherwise, null.
     */
    public ITask getSubTaskByID(String taskID){
        return (ITask) itemSet.getItem(taskID);

    }

    /**
     * Retrieves all subtasks associated with the currently selected big task.
     * @return The list of all subtasks for the selected big task.
     */
    public ArrayList<ITask> getAllSubTasks(){
        IBigTask bigTask = bigTaskController.getBigTaskByID(ToDoWindowManager.lastClickedBigTaskCard.getID());
        return bigTask.getSubTaskList();
    }

    /**
     * Retrieves the title of a subtask by its unique identifier.
     * @param taskID The identifier of the subtask.
     * @return The title of the subtask if found; otherwise, "Subtask not found".
     */
    public String getSubTaskTitle(String taskID){
        ITask subTask = getSubTaskByID(taskID);
        if(subTask != null){
            return subTask.getTitle();
        } else {
            return "BigTask not found";
        }
    }

    /**
     * Retrieves the creation date of a subtask by its unique identifier.
     * @param taskID The identifier of the subtask.
     * @return The creation date of the subtask if found; otherwise, "Unknown".
     */
    public String getSubTaskDateCreated(String taskID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
        ITask subTask = getSubTaskByID(taskID);
        if(subTask != null){
            return subTask.getDateCreated().format(formatter);
        }
        return "Unknown";
    }

    /**
     * Retrieves the status of a subtask by its unique identifier.
     * @param taskID The identifier of the subtask.
     * @return The status of the subtask if found; otherwise, false.
     */
    public boolean getSubTaskStatus(String taskID){
        ITask subTask = getSubTaskByID(taskID);
        if(subTask != null){
            return subTask.getStatus();
        } else {
            return false;
        }
    }

    /**
     * Sets the completion status of a subtask.
     * @param taskID The identifier of the subtask.
     * @param status The completion status to set for the subtask.
     */
    public void setSubTaskStatus(String taskID, boolean status){
        getSubTaskByID(taskID).setCompleted(status);
    }

    /**
     * Renames a subtask.
     * @param taskID The identifier of the subtask.
     * @param newTitle The new title to assign to the subtask.
     */
    public void renameSubTask(String taskID, String newTitle){
        getSubTaskByID(taskID).setTitle(newTitle);
    }

    /**
     * Handles the addition of a new subtask to the currently selected big task.
     * @param title The title of the new subtask to add.
     */
    public void handleAddSubTask(String title){
        IBigTask bigTask = bigTaskController.getBigTaskByID(ToDoWindowManager.lastClickedBigTaskCard.getID());
        bigTask.addSubTask(title);
        notifyObservers();
    }

    /**
     * Handles the removal of a subtask.
     * @param subTask The subtask to remove.
     */
    public void handleRemoveSubTask(ITask subTask){
        IBigTask selectedBigTask = bigTaskController.getBigTaskByID(ToDoWindowManager.lastClickedBigTaskCard.getID());
        selectedBigTask.removeSubTask(subTask.getID());
        notifyObservers();

    }

    /**
     * Validates a string to check if it is not null or empty.
     * @param stringToCheck The string to validate.
     * @return True if the string is not null and not empty; otherwise, false.
     */
    public static boolean stringValidation(String stringToCheck) {
        return stringToCheck != null && !stringToCheck.trim().isEmpty();
    }



    // Observer methods
    @Override
    public void addObserver(IPlanITObserver observer) {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(IPlanITObserver observer) {
        observers.remove(observer);

    }

    @Override
    public void notifyObservers() {
        for(IPlanITObserver observer : observers){
            observer.update();
        }

    }
}
