package org.group12.model.todo;

import org.group12.Observers.alternative.IItemObserver;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a subtask in a BigTask.
 */
public class Task implements ITask {
    private String title;
    private final LocalDateTime dateCreated;
    private boolean completed;
    private final String ID;
    private final ArrayList<IItemObserver> observers;

    /**
     * Constructs a new Task object with the given title and ID.
     *
     * @param title The title of the task.
     * @param ID The ID of the task.
     */
    public Task(String title, String ID) {
        this.title = title;
        this.ID = ID;
        this.dateCreated = LocalDateTime.now();
        this.completed = false;
        this.observers = new ArrayList<>();
    }

    /**
     * Returns the ID of the task.
     *
     * @return The ID of the task.
     */
    @Override
    public String getID() {
        return ID;
    }

    /**
     * Returns the title of the task.
     *
     * @return The title of the task.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     *
     * @param title The new title of the task.
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the date and time when the task was created.
     *
     * @return The date and time when the task was created.
     */
    @Override
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the status of the task.
     *
     * @return The status of the task.
     */
    @Override
    public boolean getStatus() {
        return completed;
    }

    /**
     * Sets the status of the task.
     *
     * @param status The new status of the task.
     */
    @Override
    public void setCompleted(boolean status) {
        this.completed = status;
    }

    /**
     * Adds an observer to the task.
     *
     * @param observer The observer to be added.
     */
    @Override
    public void addObserver(IItemObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the task.
     *
     * @param observer The observer to be removed.
     */
    @Override
    public void removeObserver(IItemObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers about a new item added to the task.
     *
     * @param newItem The new item added to the task.
     */
    @Override
    public void notifyNewItem(INameable newItem) {
        for (IItemObserver observer : observers) {
            observer.addItem(newItem);
        }
    }

    /**
     * Notifies all observers about an item removed from the task.
     *
     * @param itemID The ID of the item removed from the task.
     */
    @Override
    public void notifyRemoveItem(String itemID) {
        for (IItemObserver observer : observers) {
            observer.removeItem(itemID);
        }
    }
}
