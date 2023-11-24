package org.group12.controller;

import org.group12.model.todo.TaskList;
import org.group12.view.TaskListView;

public class TaskListController implements IController {

    private TaskList taskListModel;
    private TaskListView taskListView;

    public TaskListController(){
//        this.taskListModel = new TaskList();
        this.taskListView = new TaskListView();
        //taskListModel.addobservers(taskListView);
    }


}
