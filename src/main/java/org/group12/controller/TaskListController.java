package org.group12.controller;

import org.group12.controllerView.ToDoPageController;
import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.*;
import org.group12.view.TaskListView;
import java.util.ArrayList;


public class TaskListController implements IController {

    private TaskListView taskListView;
    private ToDoPageController toDoPageController;

    private ItemsSet items;
    private TodoCollection todoCollection;
    private static TaskListController instance;
    private Container container = Container.getInstance();



    private TaskListController(){
        this.items = Items.getInstance();
        this.todoCollection = Container.getInstance().getTodoCollection();
        this.taskListView = new TaskListView();

    }

    public Container getContainer(){
        return this.container;
    }

    public static TaskListController getInstance(){
        if(instance == null){
            instance = new TaskListController();
        }
        return instance;
    }

    // To-do lists methods
    public void handlerAddToDoList(String title){
        todoCollection.addTaskList(title);
    }

    public void handlerRemoveToDoList(ITaskList taskList){
        todoCollection.removeTaskList(taskList.getID());
    }


    public String getListsTitle(){
        return todoCollection.getTitle();
    }

    public void changeListTitle(String newTitle){
        if(TaskController.stringValidation(newTitle)){
            todoCollection.setTitle(newTitle);
        } else{
            System.out.println("Not a String");
        }

    }

    public String getID(){
        return toDoPageController.retriveID();
    }

    public ArrayList<ITaskList> fetchAllTaskLists(){
        return todoCollection.getTaskList();
    }
    public ArrayList<IBigTask> fetchAllBigTasks(String taskListID){
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return taskList.getBigTaskList();
    }





}
