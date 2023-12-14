package org.group12.controller;

import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.controllerView.ToDoWindowManager;
import org.group12.model.ItemsSet;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITaskList;
import org.group12.view.TaskListCards;

import java.util.ArrayList;
import java.util.List;

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


    // Methods
    public String getBigTaskTitle(String bigTaskID){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        if(bigTask != null){
            return bigTask.getTitle();
        } else {
            return "BigTask not found";
        }
    }

    public String getBigTaskDateCreated(String bigTaskID){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        if(bigTask != null){
            return bigTask.getDateCreated().toString();
        }
        return "Unknown";
    }

    public boolean getBigTaskCheckBoxStatus(String bigTaskID){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        if(bigTask != null){
            return bigTask.getStatus();
        } else {
            return false;
        }
    }

    public void setBigTaskCheckBoxStatus(String bigTaskID, boolean status){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        bigTask.setCompleted(status);
    }

    public boolean getBigTaskFavouriteStatus(String bigTaskID){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        return bigTask.isFavourite();

    }

    public void setBigTaskFavoriteStatus(String bigTaskID, boolean status){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        bigTask.setFavourite(status);
        notifyObservers();
    }

    public void renameTheTask(String bigTaskID, String newTitle){
        itemsSet.getItem(bigTaskID).setTitle(newTitle);
        notifyObservers();
    }

    public void handleRemoveTask(IBigTask bigTask){
        TaskListCards selectedList = ToDoWindowManager.lastClickedTaskListCard;
        taskListController.getTaskListByID(selectedList.getID()).removeBigTask(bigTask);
        notifyObservers();
    }

    public IBigTask getBigTaskByID(String bigTaskID){
        return (IBigTask) itemsSet.getItem(bigTaskID);
    }

    public ArrayList<IBigTask> fetchAllBigTasks(String taskListID){
        ITaskList taskList = (ITaskList) itemsSet.getItem(taskListID);
        return taskList.getBigTaskList();
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


}
