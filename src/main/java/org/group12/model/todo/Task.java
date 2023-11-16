package org.group12.model.todo;

import org.group12.INameable;

import java.time.LocalDateTime;

public class Task implements ITask, INameable {
    private String title;
    private String description;
    private final LocalDateTime dateCreated;
    private LocalDateTime dueDate;
    private boolean completed;
    private int priority;
    private long ID;

    public Task(String title) {
        this.title = title;
        this.dateCreated = LocalDateTime.now();
        this.completed = false;
    }

    @Override
    public long getID() {
        return ID;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }


    public void setDescription(String desc) {
        this.description = desc;
    }

    @Override
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @Override
    public LocalDateTime getDueDate() {
        return dueDate;
    }


    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean getStatus() {
        return completed;
    }


    public void setStatus(boolean status) {
        this.completed = status;
    }


    public int getPriority() {
        return priority;
    }


    public void setPriority(int priority) {
        if (priority >= 0 && priority <= 2) this.priority = priority;
        else throw new RuntimeException("Illegal priority. Can only be 0-2");
    }
}
