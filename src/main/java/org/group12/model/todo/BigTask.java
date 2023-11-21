package org.group12.model.todo;

import org.group12.model.IDateCreated;
import org.group12.model.IDescription;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BigTask implements IBigTask, INameable, IDateCreated, IDescription {
    private String description;
    private LocalDateTime dueDate;
    private int priority;
    ArrayList<ITask> subTasks;
    Task modelTask;

    public BigTask(String title, String ID) {
        modelTask = new Task("model", ID);
        modelTask.setTitle(title);
        this.subTasks = new ArrayList<>();
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

    @Override
    public void setPriority(int priority) {
        if (priority >= 0 && priority <= 2) this.priority = priority;
        else throw new RuntimeException("Illegal priority. Can only be 0-2");
    }

    @Override
    public ArrayList<ITask> getSubTasks() {
        return subTasks;
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
    public void setTitle(String title) {
        this.modelTask.setTitle(title);
    }

    @Override
    public String getTitle() {
        return modelTask.getTitle();
    }
}
