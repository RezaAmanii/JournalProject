package org.group12.controller;


import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.Observers.ITaskListObserver;
import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITaskList;

import java.util.ArrayList;
import java.util.List;

public class BigTaskController implements IController, IObservable {

    private final ItemsSet itemsSet;
    private static BigTaskController instance;
    private final List<IPlanITObserver> observers = new ArrayList<>();

    // Constructor
    private BigTaskController() {
        this.itemsSet = Items.getInstance();
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
        return itemsSet.getItem(bigTaskID).getTitle();
    }

    public String getBigTaskDateCreated(String bigTaskID){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        return bigTask.getDateCreated().toString();
    }

    public boolean getBigTaskCheckBoxStatus(String bigTaskID){
        IBigTask bigTask = (IBigTask) itemsSet.getItem(bigTaskID);
        return bigTask.getStatus();
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
