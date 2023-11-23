package org.group12.controller;

import org.group12.model.todo.Task;
import org.group12.view.TaskView;


public class TaskController implements IController {
    private Task taskModel;
    private TaskView taskView;;
    private String taskTitle;

    public TaskController(){

    }

    public TaskController(String taskTitle){
        this.taskModel = new Task(taskTitle);
        this.taskView = new TaskView();
        //taskModel.addObserver(taskView);
    }
}
