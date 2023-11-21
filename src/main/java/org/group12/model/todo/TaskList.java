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

    @Override
    public String getID() {
        return ID;
    }

    // Methods for editing Bigtasks
    @Override
    public void addBigTask(String title) {
        IBigTask newTask = bigTaskFactory.createBigTask(title);
        bigTaskMap.put(newTask.getID(), newTask);
    }

    @Override
    public void removeBigTask(String ID) {
        bigTaskMap.remove(ID);
    }
    @Override
    public void setBigTaskTitle(String title, String bigTaskID) {
        bigTaskMap.get(bigTaskID).setTitle(title);
    }

    @Override
    public void getBigTaskTitle(String bigTaskID) {
        bigTaskMap.get(bigTaskID).getTitle();
    }

    @Override
    public LocalDateTime getBigTaskDueDate(String bigTaskID) {
        return bigTaskMap.get(bigTaskID).getDueDate();
    }

    @Override
    public void setBigTaskDueDate(LocalDateTime date, String bigTaskID) {
        bigTaskMap.get(bigTaskID).setDueDate(date);
    }

    @Override
    public int getBigTaskPriority(String bigTaskID) {
        return bigTaskMap.get(bigTaskID).getPriority();
    }

    @Override
    public void setBigTaskPriority(int priority, String bigTaskID) {
        bigTaskMap.get(bigTaskID).setPriority(priority);
    }

    @Override
    public String getBigTaskID(String bigTaskID) {
        return bigTaskMap.get(bigTaskID).getID();
    }

    @Override
    public boolean getBigTaskStatus(String bigTaskID) {
        return bigTaskMap.get(bigTaskID).getStatus();
    }

    @Override
    public void setBigTaskCompleted(boolean status, String bigTaskID) {
        bigTaskMap.get(bigTaskID).setCompleted(status);
    }

    @Override
    public LocalDateTime getBigTaskDateCreated(String bigTaskID) {
        return bigTaskMap.get(bigTaskID).getDateCreated();
    }

    @Override
    public String getBigTaskDescription(String bigTaskID) {
        return bigTaskMap.get(bigTaskID).getDescription();
    }

    @Override
    public void setBigTaskDescription(String desc, String bigTaskID) {
        bigTaskMap.get(bigTaskID).setDescription(desc);
    }

    @Override
    public HashMap<String, IBigTask> getBigTaskMap() {
        return bigTaskMap;
    }

    //
    // Methods for editing the subTasks inside BigTasks in TaskLists

    @Override
    public void addSubTask(String title, String bigTaskID) {
        bigTaskMap.get(bigTaskID).addSubTask(title);
    }

    @Override
    public void removeSubTask(String bigTaskID, String subTaskID) {
        bigTaskMap.get(bigTaskID).removeSubTask(subTaskID);
    }

    @Override
    public String getSubTaskID(String bigTaskID, String subTaskID) {
        return bigTaskMap.get(bigTaskID).getSubTaskID(subTaskID);
    }

    @Override
    public String getSubTaskTitle(String bigTaskID, String subTaskID) {
        return bigTaskMap.get(bigTaskID).getSubTaskTitle(subTaskID);
    }

    @Override
    public void setSubTaskTitle(String title, String bigTaskID, String subTaskID) {
        bigTaskMap.get(bigTaskID).setSubTaskTitle(title, subTaskID);
    }

    @Override
    public LocalDateTime getSubTaskDateCreated(String bigTaskID, String subTaskID) {
        return bigTaskMap.get(bigTaskID).getSubTaskDateCreated(subTaskID);
    }

    @Override
    public boolean getSubTaskStatus(String bigTaskID, String subTaskID) {
        return bigTaskMap.get(bigTaskID).getSubTaskStatus(subTaskID);
    }

    @Override
    public void setSubTaskCompleted(boolean status, String bigTaskID, String subTaskID) {
        bigTaskMap.get(bigTaskID).setSubTaskCompleted(status, subTaskID);
    }

    @Override
    public HashMap<String, ITask> getSubTaskMap(String bigTaskID) {
        return bigTaskMap.get(bigTaskID).getSubTaskMap();
    }
}
