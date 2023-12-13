package org.group12.controller;


import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.BigTask;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import org.group12.model.todo.TodoCollection;
import org.group12.view.TaskView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.group12.model.Items;


public class TaskController implements IController {

    // Attributes
    private ItemsSet itemSet;
    private static TaskController instance;

    // Controller
    private final BigTaskController bigTaskController;


    // Constructor
    private TaskController(){
        this.itemSet = Items.getInstance();
        this.bigTaskController = BigTaskController.getInstance();
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
        return (ITask) itemSet.getItem(taskID);
    }

    public String getSubTaskTitle(String taskID){
        return getSubTaskByID(taskID).getTitle();
    }

    public String getSubTaskDateCreated(String taskID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
        return getSubTaskByID(taskID).getDateCreated().format(formatter);
    }

    public boolean getSubTaskStatus(String taskID){
        return getSubTaskByID(taskID).getStatus();
    }

    // Setters
    public void setSubTaskStatus(String taskID, boolean status){
        getSubTaskByID(taskID).setCompleted(status);
    }

    public void renameSubTask(String taskID, String newTitle){
        getSubTaskByID(taskID).setTitle(newTitle);
    }









    public static boolean stringValidation(String stringToCheck) {
        return stringToCheck != null && !stringToCheck.trim().isEmpty();
    }

    }
