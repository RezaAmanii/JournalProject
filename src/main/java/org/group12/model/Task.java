package org.group12.model;

import java.time.LocalDateTime;

public class Task implements ITask {
    private String title;
    private String description;
    private final LocalDateTime dateCreated;
    private LocalDateTime dueDate;
    private boolean status;
    private int priority;

    public Task(String title) {
        this.title = title;
        this.dateCreated = LocalDateTime.now();
        this.status = false;
    }

    public Task(String title, String description) {
        this(title);
        this.description = description;
    }

    public Task(String title, LocalDateTime dueDate) {
        this(title);
        this.dueDate = dueDate;
    }

    public Task(String title, boolean status) {
        this(title);
        this.status = status;
    }

    public Task(String title, int priority) {
        this(title);
        setPriority(priority);
    }

    public Task(String title, String description, LocalDateTime dueDate) {
        this(title, description);
        this.dueDate = dueDate;
    }

    public Task(String title, String description, boolean status) {
        this(title, description);
        this.status = status;
    }

    public Task(String title, String description, int priority) {
        this(title, description);
        setPriority(priority);
    }

    public Task(String title, String description, LocalDateTime dueDate, boolean status) {
        this(title, description, dueDate);
        this.status = status;
    }

    public Task(String title, String description, LocalDateTime dueDate, int priority) {
        this(title, description, dueDate);
        setPriority(priority);
    }

    public Task(String title, String description, LocalDateTime dueDate, boolean status, int priority) {
        this(title, description, dueDate, status);
        setPriority(priority);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
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

    @Override
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
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
}
