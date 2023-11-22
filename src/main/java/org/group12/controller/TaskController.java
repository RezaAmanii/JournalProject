package org.group12.controller;

import org.group12.model.todo.Task;
import org.group12.view.taskView;


public class TaskController {
    private Task taskModel;
    private taskView taskView;;
    private String taskTitle;

    public TaskController(String taskTitle){
        //this.taskModel = new Task(taskTitle);
        this.taskView = new taskView();
        //taskModel.addObserver(taskView);
    }
}
