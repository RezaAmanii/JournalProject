package org.group12.controller;

import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.controllerView.ToDoWindowManager;
import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import org.group12.model.todo.ITaskList;
import org.group12.view.TaskListCard;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages operations related to IBigTask instances, handles their retrieval, modification, and updates observers.
 */
public class BigTaskController implements IController, IObservable {

    private final ItemsSet itemsSet;
    private static BigTaskController instance;
    private final List<IPlanITObserver> observers = new ArrayList<>();
    private final TaskListController taskListController;

    // Constructor
    private BigTaskController() {
        this.itemsSet = SaveLoad.getInstance().getItemsInstance();
        this.taskListController = TaskListController.getInstance();
    }

    // Singleton
    public static BigTaskController getInstance() {
        if (instance == null) {
            instance = new BigTaskController();
        }
        return instance;
    }


    /**
     * Retrieves the title of a specific IBigTask.
     *
     * @param bigTaskID The unique identifier of the IBigTask.
     * @return The title of the specified IBigTask or "BigTask not found" if not found.
     */
    public String getBigTaskTitle(String bigTaskID){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        if(bigTask != null){
            return bigTask.getTitle();
        } else {
            return "BigTask not found";
        }
    }

    /**
     * Retrieves the creation date of a specific IBigTask formatted as a string.
     *
     * @param bigTaskID The unique identifier of the IBigTask.
     * @return The creation date of the specified IBigTask formatted as "yyyy-MM-dd HH:mm" or "Unknown" if not found.
     */
    public String getBigTaskDateCreated(String bigTaskID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        if(bigTask != null){
            return bigTask.getDateCreated().format(formatter);
        }
        return "Unknown";
    }

    /**
     * Retrieves the checkbox status of a specific IBigTask.
     *
     * @param bigTaskID The unique identifier of the IBigTask.
     * @return The checkbox status of the specified IBigTask or false if not found.
     */
    public boolean getBigTaskCheckBoxStatus(String bigTaskID){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        if(bigTask != null){
            return bigTask.getStatus();
        } else {
            return false;
        }
    }

    /**
     * Sets the checkbox status of a specific IBigTask.
     *
     * @param bigTaskID The unique identifier of the IBigTask.
     * @param status    The status to set for the checkbox.
     */
    public void setBigTaskCheckBoxStatus(String bigTaskID, boolean status){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        bigTask.setCompleted(status);
    }

    /**
     * Retrieves the favorite status of a specific IBigTask.
     *
     * @param bigTaskID The unique identifier of the IBigTask.
     * @return The favorite status of the specified IBigTask.
     */
    public boolean getBigTaskFavouriteStatus(String bigTaskID){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        return bigTask.isFavourite();

    }

    /**
     * Sets the favorite status of a specific IBigTask.
     *
     * @param bigTaskID The unique identifier of the IBigTask.
     * @param status    The status to set for the favorite.
     */
    public void setBigTaskFavoriteStatus(String bigTaskID, boolean status){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        bigTask.setFavourite(status);
//        notifyObservers();
    }

    /**
     * Renames a specific IBigTask.
     *
     * @param bigTaskID The unique identifier of the IBigTask.
     * @param newTitle  The new title to set for the IBigTask.
     */
    public void renameTheTask(String bigTaskID, String newTitle){
        itemsSet.getItem(bigTaskID).setTitle(newTitle);
        notifyObservers();
    }

    /**
     * Handles the removal of a specific IBigTask.
     *
     * @param bigTask The IBigTask instance to remove.
     */
    public void handleRemoveTask(IBigTask bigTask){
        TaskListCard selectedList = ToDoWindowManager.lastClickedTaskListCard;
        taskListController.getTaskListByID(selectedList.getID()).removeBigTask(bigTask);
        notifyObservers();
    }

    /**
     * Handles the addition of a new IBigTask with the provided title.
     *
     * @param title The title for the new IBigTask.
     */
    public void handleAddTask(String title){
        TaskListCard selectedTaskListCard = ToDoWindowManager.lastClickedTaskListCard;
        ITaskList taskList = taskListController.getTaskListByID(selectedTaskListCard.getID());
        taskList.addBigTask(title);
        notifyObservers();
    }

    /**
     * Retrieves an IBigTask instance by its unique identifier.
     *
     * @param bigTaskID The unique identifier of the IBigTask.
     * @return The IBigTask instance corresponding to the provided ID.
     */
    public IBigTask getBigTaskByID(String bigTaskID){
        return (IBigTask) itemsSet.getItem(bigTaskID);
    }


    // Observer methods
    @Override
    public void addObserver(IPlanITObserver observer) {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(IPlanITObserver observer){
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IPlanITObserver observer : observers){
            observer.update();
        }

    }


    public boolean bigTaskContains(IBigTask bigTask, ITask subTask) {
        if (bigTask != null && subTask != null) {
            // Assuming there's a method in IBigTask to get its associated tasks
            List<ITask> tasks = bigTask.getSubTaskList();
            return tasks.contains(subTask);
        }
        return false;
    }
}
