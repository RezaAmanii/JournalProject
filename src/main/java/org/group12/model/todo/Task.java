package org.group12.model.todo;

import org.group12.Observers.IPlanITObserver;
import org.group12.Observers.alternative.IItemObserver;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task implements ITask {
    private String title;
    private final LocalDateTime dateCreated;
    private boolean completed;
    private final String ID;
    private final ArrayList<IItemObserver> observers;

    public Task(String title, String ID) {
        this.title = title;
        this.ID = ID;
        this.dateCreated = LocalDateTime.now();
        this.completed = false;
        this.observers = new ArrayList<>();
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
