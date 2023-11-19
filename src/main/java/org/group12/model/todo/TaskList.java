package org.group12.model.todo;

import org.group12.model.IDateCreated;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList implements ITaskList, INameable, IDateCreated {
    private String title;
    private LocalDateTime dateCreated;
    private final ArrayList<IBigTask> tasks;
    private long ID;

    //private ArrayList<TaskListObserver> observers;
    //private TaskFactory taskFactory;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.title = "new list";
    }

    @Override
    public long getID() {
        return ID;
    }

    @Override
    public void addTask(BigTask task) {

    }

    @Override
    public void removeTask(BigTask task) {

    }

    @Override
    public ArrayList<IBigTask> getTasks() {
        return null;
    }

    @Override
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
        if (this.title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

    @Override
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
}
