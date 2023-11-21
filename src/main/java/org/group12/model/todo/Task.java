package org.group12.model.todo;

import java.time.LocalDateTime;

public class Task implements ITask {
    private String title;
    private final LocalDateTime dateCreated;
    private boolean completed;
    private final String ID;

    public Task(String title, String ID) {
        this.title = title;
        this.ID = ID;
        this.dateCreated = LocalDateTime.now();
        this.completed = false;
    }

    @Override
    public String getID() {
        return ID;
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
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @Override
    public boolean getStatus() {
        return completed;
    }

    @Override
    public void setCompleted(boolean status) {
        this.completed = status;
    }
}
