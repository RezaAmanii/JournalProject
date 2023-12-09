package org.group12.controller;


import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITaskList;
import java.util.List;

public class BigTaskController implements IController {

    private ItemsSet itemsSet;
    private static BigTaskController instance;

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
    }



}
