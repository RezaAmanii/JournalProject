package org.group12.controller;

import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TaskListController implements IController, IObservable {

    // Attributes
    private final ItemsSet items;
    private final TodoCollection todoCollection;
    private static TaskListController instance;
    private final List<IPlanITObserver> observers = new ArrayList<>();

    // Constructor
    private TaskListController() {
        this.items = Items.getInstance();
        this.todoCollection = Container.getInstance().getTodoCollection();

    }

    // Singleton
    public static TaskListController getInstance() {
        if (instance == null) {
            instance = new TaskListController();
        }
        return instance;
    }


    // Add and remove methods
    public String handlerAddToDoList(String title) {
        return todoCollection.addTaskList(title);
    }

    public void handlerRemoveToDoList(ITaskList taskList) {
        todoCollection.removeTaskList(taskList);
        notifyObservers();
    }

    // Rename methods
    public void changeListTitle(String TaskListID, String newTitle) {
        ITaskList taskList = (ITaskList) items.getItem(TaskListID);
        if (taskList != null){
            if (SubTaskController.stringValidation(newTitle)) {
                taskList.setTitle(newTitle);
            } else {
                System.out.println("Not a String");
            }

        } else {
            System.out.println("TaskList not found");
        }
        notifyObservers();
    }

    // Getting methods
    public ITaskList getTaskListByID(String taskListID){
        return (ITaskList) items.getItem(taskListID);
    }

    public ITaskList getTaskListByTitle(String title){
        for(ITaskList taskList : todoCollection.getTaskList()){
            if(taskList.getTitle().equals(title)){
                return taskList;
            }
        }
        return null;
    }

    public String getNrOfBigTasks(String taskListID){
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return String.valueOf(taskList.getBigTaskList().size());
    }

    public String getTaskListDateCreated(String taskListID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return taskList.getDateCreated().format(formatter);

    }
    public String getTaskListTitle(String taskListID){
        return items.getItem(taskListID).getTitle();
    }

    public ArrayList<ITaskList> getTasksLists(){
        return todoCollection.getTaskList();
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
