package org.group12.controller;

import org.group12.model.todo.TaskList;
import org.group12.view.taskListView;

public class TaskListController implements IController {

    private TaskList taskListModel;
    private taskListView taskListView;

    public TaskListController(){
        this.taskListModel = new TaskList();
        this.taskListView = new taskListView();
        //taskListModel.addobservers(taskListView);
    }


}
