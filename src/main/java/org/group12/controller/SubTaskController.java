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

    // Singleton
    public static SubTaskController getInstance(){
        if (instance == null){
            instance = new SubTaskController();
        }
        return instance;
    }


    // Methods
    public ITask getSubTaskByID(String taskID){
        return (ITask) itemSet.getItem(taskID);

    }

    public ArrayList<ITask> getAllSubTasks(){
        IBigTask bigTask = bigTaskController.getBigTaskByID(ToDoWindowManager.lastClickedBigTaskCard.getID());
        return bigTask.getSubTaskList();
    }

    public String getSubTaskTitle(String taskID){
        ITask subTask = getSubTaskByID(taskID);
        if(subTask != null){
            return subTask.getTitle();
        } else {
            return "BigTask not found";
        }
    }

    public String getSubTaskDateCreated(String taskID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
        ITask subTask = getSubTaskByID(taskID);
        if(subTask != null){
            return subTask.getDateCreated().format(formatter);
        }
        return "Unknown";
    }

    public boolean getSubTaskStatus(String taskID){
        ITask subTask = getSubTaskByID(taskID);
        if(subTask != null){
            return subTask.getStatus();
        } else {
            return false;
        }
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
        bigTask.addSubTask(title);
        notifyObservers();
    }

    public void handleRemoveSubTask(ITask subTask){
        IBigTask selectedBigTask = bigTaskController.getBigTaskByID(ToDoWindowManager.lastClickedBigTaskCard.getID());
        selectedBigTask.removeSubTask(subTask.getID());
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
