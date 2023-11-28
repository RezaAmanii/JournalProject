package org.group12.controller;


import org.group12.model.INameable;
import org.group12.model.todo.BigTask;
import org.group12.model.todo.TodoCollection;
import org.group12.view.TaskView;

import java.time.LocalDateTime;
import java.util.Map;
import org.group12.model.Items;


public class TaskController implements IController {
    private TodoCollection taskModel;
    private TaskView taskView;
    private Map<String, INameable> taskMap;
    private BigTask bigTask;
    private Items itemMap;



    public TaskController(TodoCollection taskModel, TaskView taskView, Items itemMap){
        this.taskModel = taskModel;
        this.taskView = taskView;
        this.taskMap = taskMap;
        //taskModel.addObserver(taskView);
    }

    public void handleAddSubTask(){
        //String newTitle = titleTextField.getText();
        String newTitle = "test";
        if(stringValidation(newTitle)){
            bigTask.addSubTask(newTitle);
        } else{
            //taskView.displayError("Title cannot be empty");
        }
    }

    public void handleRemoveSubTask(){
        //String subTaskID = subTaskField.getID();
        String subTaskID = "test";
        bigTask.removeSubTask(subTaskID);
        taskView.update();
    }


    public void handleSetTitle(String newTitle){
        if(stringValidation(newTitle)){
            bigTask.setTitle(newTitle);
            taskView.update();
        } else{
            //taskView.displayError("Title cannot be empty");
        }

    }

    public void handleSetDueDate(LocalDateTime date) {
        if (dateValidation(date)) {
            bigTask.setDueDate(date);
            taskView.update();
        } else{
            //taskView.displayError("Date cannot be in the past");
        }
    }

    public void handleSetPriority(int priority) {
        if (priority >= 0) {
            bigTask.setPriority(priority);
            taskView.update();
        } else{
            //taskView.displayError("Priority cannot be negative");
        }
    }

    public void handleSetStatus(boolean status) {
        //bigTask.setStatus(status);
        taskView.update();
    }

    public void handleGetStatus(){
        bigTask.getStatus();
        taskView.update();
    }

    private boolean stringValidation(String stringToCheck) {
        return stringToCheck != null && !stringToCheck.trim().isEmpty();
    }

    private boolean dateValidation(LocalDateTime dateToCheck) {
        return dateToCheck != null && !dateToCheck.isBefore(LocalDateTime.now());

    }
    }
