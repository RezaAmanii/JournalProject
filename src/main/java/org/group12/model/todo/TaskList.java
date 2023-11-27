package org.group12.model.todo;

import org.group12.Observers.alternative.IItemObserver;
import org.group12.model.INameable;
import org.group12.model.todo.factories.BigTaskFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a task list that contains big tasks.
 */
public class TaskList implements ITaskList {
    private String title;
    private LocalDateTime dateCreated;
    private final HashMap<String, IBigTask> bigTaskMap;
    private final String ID;
    private final BigTaskFactory bigTaskFactory;
    private final ArrayList<IItemObserver> observers;

    /**
     * Constructs a TaskList object with the given title and ID.
     *
     * @param title The title of the task list.
     * @param ID    The ID of the task list.
     */
    public TaskList(String title, String ID) {
        this.bigTaskMap = new HashMap<>();
        this.bigTaskFactory = new BigTaskFactory();
        this.ID = ID;
        setTitle(title);
        this.observers = new ArrayList<>();
    }

    /**
     * Gets the ID of the task list.
     *
     * @return The ID of the task list.
     */
    @Override
    public String getID() {
        return ID;
    }

    /**
     * Sets the title of the task list.
     *
     * @param title The title of the task list.
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the title of the task list.
     *
     * @return The title of the task list.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Gets the date when the task list was created.
     *
     * @return The date when the task list was created.
     */
    @Override
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * Adds a new big task to the task list.
     *
     * @param title The title of the big task.
     */
    @Override
    public void addBigTask(String title) {
        IBigTask newTask = bigTaskFactory.createBigTask(title);
        bigTaskMap.put(newTask.getID(), newTask);
    }

    /**
     * Removes a big task from the task list.
     *
     * @param ID The ID of the big task to be removed.
     */
    @Override
    public void removeBigTask(String ID) {
        bigTaskMap.remove(ID);
    }

    /**
     * Gets the map of big tasks in the task list.
     *
     * @return The map of big tasks in the task list.
     */
    @Override
    public HashMap<String, IBigTask> getBigTaskMap() {
        return bigTaskMap;
    }

    /**
     * Adds an observer to the task list.
     *
     * @param observer The observer to be added.
     */
    @Override
    public void addObserver(IItemObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the task list.
     *
     * @param observer The observer to be removed.
     */
    @Override
    public void removeObserver(IItemObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies the observers about a new item added to the task list.
     *
     * @param newItem The new item added to the task list.
     */
    @Override
    public void notifyNewItem(INameable newItem) {
        for (IItemObserver observer : observers) {
            observer.addItem(newItem);
        }
    }

    /**
     * Notifies the observers about an item removed from the task list.
     *
     * @param itemID The ID of the item removed from the task list.
     */
    @Override
    public void notifyRemoveItem(String itemID) {
        for (IItemObserver observer : observers) {
            observer.removeItem(itemID);
        }
    }
}
