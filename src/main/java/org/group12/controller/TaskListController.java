package org.group12.controller;

import org.group12.model.Items;
import org.group12.model.todo.TaskList;
import org.group12.model.todo.TodoCollection;
import org.group12.view.TaskListView;

public class TaskListController implements IController {

    private TaskListView taskListView;
    private TodoCollection todoCollection;
    private Items items;


    public TaskListController(TodoCollection todoCollection, Items items){
        this.todoCollection = todoCollection;
        this.items = items;

        this.taskListView = new TaskListView();
        //taskListModel.addobservers(taskListView);
    }


    public void addEventHandler(String title){
        todoCollection.addTaskList(title);
    }


}
