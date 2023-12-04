package org.group12.controller;

import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.*;
import org.group12.model.todo.factories.TodoCollectionFactory;
import org.group12.view.TaskListView;

import java.util.HashMap;

public class TaskListController implements IController {

    private TaskListView taskListView;
    private ItemsSet items;
    private TodoCollection todoCollection;
    private TaskList taskList;
    private BigTask bigTask;
    private Task subTask;



    public TaskListController(){
        this.items = Items.getInstance();
        TodoCollectionFactory collectionFactory = new TodoCollectionFactory(items);

        this.todoCollection = collectionFactory.createTodoCollection("My Todo Collection");
        this.taskListView = new TaskListView();

    }

    // To-do lists methods
    public void handlerAddToDoList(String title){
        if(TaskController.stringValidation(title)){
            todoCollection.addTaskList(title);
        } else{
            System.out.println("Not a String");
        }

    }


    public void handlerRemoveToDoList(ITaskList taskList){
        todoCollection.removeTaskList(taskList.getID());
    }

    public HashMap<String, ITaskList> handlerGetTaskListMap(){
        return todoCollection.getTaskListMap();
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


    // Big-tasks methods




}
