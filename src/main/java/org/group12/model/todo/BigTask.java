package org.group12.model.todo;

import org.group12.model.todo.factories.TaskFactory;

import java.time.LocalDateTime;
import java.util.HashMap;

public class BigTask implements IBigTask {
    private String description;
    private LocalDateTime dueDate;
    private int priority;
    private final HashMap<String, ITask> subTasks;
    Task modelTask;

    private TaskFactory taskFactory;

    public BigTask(String title, String ID) {
        this.subTasks = new HashMap<>();
        this.taskFactory = new TaskFactory();
        modelTask = new Task("model", ID);
        modelTask.setTitle(title);
    }

    @Override
    public void setTitle(String title) {
        this.modelTask.setTitle(title);
    }

    @Override
    public String getTitle() {
        return modelTask.getTitle();
    }

    @Override
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(LocalDateTime date) {
        this.dueDate = date;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    // TODO choose priority implementation
    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String getID() {
        return modelTask.getID();
    }

    @Override
    public boolean getStatus() {
        return modelTask.getStatus();
    }

    @Override
    public void setCompleted(boolean status) {
        modelTask.setCompleted(status);
    }

    @Override
    public LocalDateTime getDateCreated() {
        return modelTask.getDateCreated();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String desc) {
        this.description = desc;
    }

    //
    //
    //

    @Override
    public void addSubTask(String title) {
        ITask newTask = taskFactory.createTask(title);
        subTasks.put(newTask.getID(), newTask);
    }

    @Override
    public void removeSubTask(String subTaskID) {
        subTasks.remove(subTaskID);
    }

    @Override
    public String getSubTaskID(String subTaskID) {
        return subTasks.get(subTaskID).getID();
    }

    @Override
    public String getSubTaskTitle(String subTaskID) {
        return subTasks.get(subTaskID).getTitle();
    }

    @Override
    public void setSubTaskTitle(String title, String subTaskID) {
        subTasks.get(subTaskID).setTitle(title);
    }

    @Override
    public LocalDateTime getSubTaskDateCreated(String subTaskID) {
        return subTasks.get(subTaskID).getDateCreated();
    }

    @Override
    public boolean getSubTaskStatus(String subTaskID) {
        return subTasks.get(subTaskID).getStatus();
    }

    @Override
    public void setSubTaskCompleted(boolean status, String subTaskID) {
        subTasks.get(subTaskID).setCompleted(status);
    }
}
