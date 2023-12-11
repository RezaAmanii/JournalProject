package org.group12.controller;

import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.*;
import org.group12.view.TaskListView;
import java.util.ArrayList;
import java.util.List;


public class TaskListController implements IController, IObservable {

    private TaskListView taskListView;

    private final ItemsSet items;
    private final TodoCollection todoCollection;
    private static TaskListController instance;
    private final Container container = Container.getInstance();
    private final List<IPlanITObserver> observers = new ArrayList<>();

    // Constructor
    private TaskListController() {
        this.items = Items.getInstance();
        this.todoCollection = Container.getInstance().getTodoCollection();
        this.taskListView = new TaskListView();
    }

    // Singleton
    public static TaskListController getInstance() {
        if (instance == null) {
            instance = new TaskListController();
        }
        return instance;
    }


    // TaskList methods
    public String handlerAddToDoList(String title) {
        return todoCollection.addTaskList(title);
    }

    public void handlerRemoveToDoList(ITaskList taskList) {
        todoCollection.removeTaskList(taskList);
        notifyObservers();
    }

    public void changeListTitle(String TaskListID, String newTitle) {
        ITaskList taskList = (ITaskList) items.getItem(TaskListID);
        if (taskList != null){
            if (TaskController.stringValidation(newTitle)) {
                taskList.setTitle(newTitle);
            } else {
                System.out.println("Not a String");
            }

        } else {
            System.out.println("TaskList not found");
        }
    }

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

    public ArrayList<ITaskList> fetchAllTaskLists(){
        return todoCollection.getTaskList();
    }

    public String getTaskListTitle(String taskListID){
        return items.getItem(taskListID).getTitle();
    }

    public String getTaskListDateCreated(String taskListID){
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return taskList.getDateCreated().toString();

    }

    public void renameTaskList(String taskListID, String newTitle){
        items.getItem(taskListID).setTitle(newTitle);
        notifyObservers();
    }
    public String getNrOfBigTasks(String taskListID){
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return String.valueOf(taskList.getBigTaskList().size());
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
