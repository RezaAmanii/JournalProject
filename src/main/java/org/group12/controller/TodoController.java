package org.group12.controller;

import org.group12.model.Items;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITask;
import org.group12.model.todo.ITaskList;
import org.group12.model.todo.ITodoCollection;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TodoController {
    private final Items items;

    public TodoController(Items items) {
        this.items = items;
    }

    // Controls for TodoCollection.
    // If multiple TodoCollections are implemented, getID(), getTitle() and setTitle() need to be added here.

    public void addTaskList(String collectionID, String title) {
        ITodoCollection collection = (ITodoCollection) items.getItem(collectionID);
        collection.addTaskList(title);
    }

    public void removeTaskList(String collectionID, String taskListID) {
        ITodoCollection collection = (ITodoCollection) items.getItem(collectionID);
        collection.removeTaskList(taskListID);
    }

    public HashMap<String, ITaskList> getTaskListMap(String collectionID) {
        ITodoCollection collection = (ITodoCollection) items.getItem(collectionID);
        return collection.getTaskListMap();
    }


    // Controls for TaskList.
    public void setTaskListTitle(String taskListID, String title) {
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        taskList.setTitle(title);
    }

    public String getTaskListTitle(String taskListID) {
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return taskList.getTitle();
    }

    public LocalDateTime getTaskListDateCreated(String taskListID) {
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return taskList.getDateCreated();
    }

    public void addBigTask(String taskListID, String title) {
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        taskList.addBigTask(title);
    }

    public void removeBigTask(String taskListID, String bigTaskID) {
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        taskList.removeBigTask(bigTaskID);
    }

    public HashMap<String, IBigTask> getBigTaskMap(String taskListID) {
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return taskList.getBigTaskMap();
    }


    // Controls for BigTask.
    public void setBigTaskTitle(String bigTaskID, String title) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        bigTask.setTitle(title);
    }

    public String getBigTaskTitle(String bigTaskID) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        return bigTask.getTitle();
    }

    public LocalDateTime getBigTaskDateCreated(String bigTaskID) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        return bigTask.getDateCreated();
    }

    public LocalDateTime getBigTaskDueDate(String bigTaskID) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        return bigTask.getDueDate();
    }

    public void setBigTaskDueDate(String bigTaskID, LocalDateTime date) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        bigTask.setDueDate(date);
    }

    public int getBigTaskPriority(String bigTaskID) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        return bigTask.getPriority();
    }

    public void setBigTaskPriority(String bigTaskID, int priority) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        bigTask.setPriority(priority);
    }

    public String getBigTaskDescription(String bigTaskID) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        return bigTask.getDescription();
    }

    public void setBigTaskDescription(String bigTaskID, String description) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        bigTask.setDescription(description);
    }

    public boolean getBigTaskStatus(String bigTaskID) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        return bigTask.getStatus();
    }

    public void setBigTaskStatus(String bigTaskID, boolean status) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        bigTask.setCompleted(status);
    }

    public void addSubTask(String bigTaskID, String title) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        bigTask.addSubTask(title);
    }

    public void removeSubTask(String bigTaskID, String subTaskID) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        bigTask.removeSubTask(subTaskID);
    }

    public HashMap<String, ITask> getSubTaskMap(String bigTaskID) {
        IBigTask bigTask = (IBigTask) items.getItem(bigTaskID);
        return bigTask.getSubTaskMap();
    }


    // Controls for Task (SubTask).

    public void setSubTaskTitle(String subTaskID, String title) {
        ITask subTask = (ITask) items.getItem(subTaskID);
        subTask.setTitle(title);
    }

    public String getSubTaskTitle(String subTaskID) {
        ITask subTask = (ITask) items.getItem(subTaskID);
        return subTask.getTitle();
    }

    public LocalDateTime getSubTaskDateCreated(String subTaskID) {
        ITask subTask = (ITask) items.getItem(subTaskID);
        return subTask.getDateCreated();
    }

    public boolean getSubTaskStatus(String subTaskID) {
        ITask subTask = (ITask) items.getItem(subTaskID);
        return subTask.getStatus();
    }

    public void setSubTaskStatus(String subTaskID, boolean status) {
        ITask subTask = (ITask) items.getItem(subTaskID);
        subTask.setCompleted(status);
    }
}
