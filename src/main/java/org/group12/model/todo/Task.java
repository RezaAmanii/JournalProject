package org.group12.model.todo;

import org.group12.model.IDateCreated;
import org.group12.model.INameable;

import java.time.LocalDateTime;

public class Task implements ITask, INameable, IDateCreated {
    private String title;
    private final LocalDateTime dateCreated;
    private boolean completed;
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
