package org.group12.controller;


import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.BigTask;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import org.group12.model.todo.TodoCollection;
import org.group12.view.TaskView;

import java.time.LocalDateTime;
import java.util.Map;
import org.group12.model.Items;


public class TaskController implements IController {
    private ItemsSet itemsSet;
    private static TaskController instance;


    // Constructor
    private TaskController(){
        this.itemsSet = Items.getInstance();
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

    public static boolean dateValidation(LocalDateTime dateToCheck) {
        return dateToCheck != null && !dateToCheck.isBefore(LocalDateTime.now());

    }
    }
