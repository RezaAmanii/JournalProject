package org.group12.model.todo;

import org.group12.Observers.IPlanITObserver;
import org.group12.Observers.alternative.IItemObserver;
import org.group12.model.INameable;
import org.group12.model.todo.factories.TaskFactory;

import java.time.LocalDateTime;
import java.util.HashMap;

public class BigTask implements IBigTask {
    private String description;
    private LocalDateTime dueDate;
    private int priority;
    private final HashMap<String, ITask> subTaskMap;
    private final Task modelTask;

    private final TaskFactory taskFactory;

    public BigTask(String title, String ID) {
        this.subTaskMap = new HashMap<>();
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
    // Methods for editing the subTasks

    @Override
    public void addSubTask(String title) {
        ITask newTask = taskFactory.createTask(title);
        subTaskMap.put(newTask.getID(), newTask);
    }

    @Override
    public void removeSubTask(String subTaskID) {
        subTaskMap.remove(subTaskID);
    }

    @Override
    public HashMap<String, ITask> getSubTaskMap() {
        return subTaskMap;
    }


    @Override
    public void addObserver(IItemObserver observer) {
        modelTask.addObserver(observer);
    }

    @Override
    public void removeObserver(IItemObserver observer) {
        modelTask.removeObserver(observer);
    }

    @Override
    public void notifyNewItem(INameable newItem) {
        modelTask.notifyNewItem(newItem);
    }

    @Override
    public void notifyRemoveItem(String itemID) {
        modelTask.notifyRemoveItem(itemID);
    }
}
