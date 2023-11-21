package org.group12.model.todo;

import org.group12.model.todo.factories.BigTaskFactory;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TaskList implements ITaskList {
    private String title;
    private LocalDateTime dateCreated;
    private final HashMap<String, IBigTask> bigTaskMap;
    private final String ID;

    private final BigTaskFactory bigTaskFactory;

    public TaskList(String title, String ID) {
        this.bigTaskMap = new HashMap<>();
        this.bigTaskFactory = new BigTaskFactory();
        this.ID = ID;
        setTitle(title);
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    // Methods for editing tasks
    @Override
    public void addTask(String title) {
        IBigTask newTask = bigTaskFactory.createBigTask(title);
        bigTaskMap.put(newTask.getID(), newTask);
    }

    @Override
    public void removeTask(String ID) {
        bigTaskMap.remove(ID);
    }

    @Override
    public void setTaskTitle(String title, String taskID) {
        bigTaskMap.get(taskID).setTitle(title);
    }

    @Override
    public void getTaskTitle(String taskID) {
        bigTaskMap.get(taskID).getTitle();
    }
}
