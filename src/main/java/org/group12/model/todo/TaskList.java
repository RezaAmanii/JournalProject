package org.group12.model.todo;

import org.group12.Observers.IPlanITObserver;
import org.group12.Observers.alternative.IItemObserver;
import org.group12.model.INameable;
import org.group12.model.todo.factories.BigTaskFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskList implements ITaskList {
    private String title;
    private LocalDateTime dateCreated;
    private final HashMap<String, IBigTask> bigTaskMap;
    private final String ID;
    private final BigTaskFactory bigTaskFactory;
    private final ArrayList<IItemObserver> observers;

    public TaskList(String title, String ID) {
        this.bigTaskMap = new HashMap<>();
        this.bigTaskFactory = new BigTaskFactory();
        this.ID = ID;
        setTitle(title);
        this.observers = new ArrayList<>();
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
    public HashMap<String, IBigTask> getBigTaskMap() {
        return bigTaskMap;
    }


    @Override
    public void addObserver(IItemObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IItemObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyNewItem(INameable newItem) {
        for (IItemObserver observer : observers) {
            observer.addItem(newItem);
        }
    }

    @Override
    public void notifyRemoveItem(String itemID) {
        for (IItemObserver observer : observers) {
            observer.removeItem(itemID);
        }
    }
}
