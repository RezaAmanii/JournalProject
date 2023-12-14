package org.group12.controller;

import org.group12.model.ItemsSet;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;

public class TaskController implements IController {
    private ItemsSet itemsSet;
    private static TaskController instance;


    // Constructor
    private TaskController(){
        this.itemsSet = SaveLoad.getInstance().getItemsInstance();
    }

    // Singleton
    public static TaskController getInstance(){
        if (instance == null){
            instance = new TaskController();
        }
        return instance;
    }

    // Methods
    public ITask getSubTaskByID(String taskID){
        return (ITask) itemsSet.getItem(taskID);
    }

    public IBigTask getTaskByID(String taskID){
        return (IBigTask) itemsSet.getItem(taskID);
    }

    public static boolean stringValidation(String stringToCheck) {
        return stringToCheck != null && !stringToCheck.trim().isEmpty();
    }

}
