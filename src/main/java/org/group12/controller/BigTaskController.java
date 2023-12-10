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

    private ItemsSet itemsSet;
    private static BigTaskController instance;
    private List<IPlanITObserver> observers = new ArrayList<>();

    private BigTaskController() {
        this.itemsSet = Items.getInstance();
    }

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

    public String getBigTaskDueDate(String bigTaskID){
        //return itemsSet.getItem(bigTaskID).getDueDate();
        return "DueDate 9/12/2023";
    }

    public boolean getBigTaskCheckBoxStatus(String bigTaskID){
        //return itemsSet.getItem(bigTaskID).getCheckBoxStatus();
        return false;
    }

    public void setBigTaskCheckBoxStatus(String bigTaskID, boolean status){
        //itemsSet.getItem(bigTaskID).setCheckBoxStatus(status);
    }

    public boolean getBigTaskFavouriteStatus(String bigTaskID){
        //return itemsSet.getItem(bigTaskID).getFavouriteStatus();
        return true;
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
