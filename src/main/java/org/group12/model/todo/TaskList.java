package org.group12.model.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList implements ITaskList {
    private String title;
    private LocalDateTime dateCreated;
    private final ArrayList<Task> tasks;
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

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
        if (this.title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }


    public LocalDateTime getDateCreated() {
        return null;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
