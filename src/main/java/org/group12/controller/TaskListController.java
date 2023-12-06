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

    private ItemsSet items;
    private TodoCollection todoCollection;
    private static TaskListController instance;
    private Container container = Container.getInstance();
    private List<IPlanITObserver> observers = new ArrayList<>();


    private TaskListController() {
        this.items = Items.getInstance();
        this.todoCollection = Container.getInstance().getTodoCollection();
        this.taskListView = new TaskListView();

    }

    public Container getContainer() {
        return this.container;
    }

    public static TaskListController getInstance() {
        if (instance == null) {
            instance = new TaskListController();
        }
        return instance;
    }


    // To-do lists methods
    public String handlerAddToDoList(String title) {
        return todoCollection.addTaskList(title);
    }


    public void handlerRemoveToDoList(ITaskList taskList) {
        todoCollection.removeTaskList(taskList.getID());
    }


    public String getListsTitle() {
        return todoCollection.getTitle();
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

    public IBigTask getBigTaskByID(String bigTaskID){
        return (IBigTask) items.getItem(bigTaskID);
    }


    public ITask getSubTaskByID(String taskID){
        return (ITask) items.getItem(taskID);
    }



    public ITaskList getTaskListByTitle(String title){
        for(ITaskList taskList : todoCollection.getTaskList()){
            if(taskList.getTitle().equals(title)){
                return taskList;
            }
        }
        return null;
    }

    public IBigTask getTaskByID(String taskID){
        return (IBigTask) items.getItem(taskID);
    }

    public void removeAnyObject(String ID){
        items.removeItem(ID);
    }

    public ArrayList<ITaskList> fetchAllTaskLists(){
        return todoCollection.getTaskList();
    }
    public ArrayList<IBigTask> fetchAllBigTasks(String taskListID){
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return taskList.getBigTaskList();
    }


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
