package org.group12.controller;


import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.controllerView.ToDoWindowManager;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.BigTask;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import org.group12.model.todo.TodoCollection;
import org.group12.view.TaskView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.group12.model.Items;


public class TaskController implements IController, IObservable {

    // Attributes
    private ItemsSet itemSet;
    private static TaskController instance;
    private List<IPlanITObserver> observers;
    private final ToDoWindowManager toDoWindowManager;

    // Controller
    private final BigTaskController bigTaskController;


    // Constructor
    private TaskController(){
        this.itemSet = Items.getInstance();
        this.bigTaskController = BigTaskController.getInstance();
        this.observers = new ArrayList<>();
        this.toDoWindowManager = new ToDoWindowManager();
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


    public void handleAddSubTask(String title){
        IBigTask bigTask = bigTaskController.getBigTaskByID(ToDoWindowManager.lastClickedBigTaskCard.getID());
        String subTaskID = bigTask.addSubTask(title);
        bigTask.addSubTask(subTaskID);
        notifyObservers();
    }

    public void handleRemoveSubTask(ITask subTask){
        IBigTask bigTask = bigTaskController.getBigTaskByID(subTask.getID());
        bigTask.removeSubTask(subTask.getID());
        notifyObservers();

    }









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
