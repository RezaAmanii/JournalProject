package org.group12.model.todo;

import org.group12.model.IDateCreated;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList implements ITaskList, INameable, IDateCreated {
    private String title;
    private LocalDateTime dateCreated;
    private final ArrayList<BigTask> tasks;
    private boolean isEmpty;
    private long ID;

    //private ArrayList<TaskListObserver> observers;
    //private TaskFactory taskFactory;

    public TaskList() {
        this.tasks = new ArrayList<>();
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
        if (this.title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

    @Override
    public LocalDateTime getDateCreated() {
        return null;
    }

    @Override
    public void addTask(BigTask task) {
        tasks.add(task);
    }

    @Override
    public void removeTask(BigTask task) {
        tasks.remove(task);
    }

    @Override
    public ArrayList<BigTask> getTasks() {
        return tasks;
    }
}
