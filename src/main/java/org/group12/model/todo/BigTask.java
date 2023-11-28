package org.group12.model.todo;

import org.group12.Observers.items_observers.IItemObserver;
import org.group12.model.INameable;
import org.group12.model.todo.factories.TaskFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a big task that contains subtasks.
 */
public class BigTask implements IBigTask {
    private String description;
    private LocalDateTime dueDate;
    private int priority;
    private final HashMap<String, ITask> subTaskMap;
    private final Task modelTask;
    private final TaskFactory taskFactory;
    private final ArrayList<IItemObserver> observers;

    /**
     * Constructs a BigTask object with the given title and ID.
     *
     * @param title The title of the big task.
     * @param ID    The ID of the big task.
     */
    public BigTask(String title, String ID) {
        this.observers = new ArrayList<>();
        this.subTaskMap = new HashMap<>();
        this.taskFactory = new TaskFactory();
        modelTask = new Task("model", ID);
        modelTask.setTitle(title);
    }

    /**
     * Sets the title of the big task.
     *
     * @param title The title to be set.
     */
    @Override
    public void setTitle(String title) {
        this.modelTask.setTitle(title);
    }

    /**
     * Gets the title of the big task.
     *
     * @return The title of the big task.
     */
    @Override
    public String getTitle() {
        return modelTask.getTitle();
    }

    /**
     * Gets the due date of the big task.
     *
     * @return The due date of the big task.
     */
    @Override
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the big task.
     *
     * @param date The due date to be set.
     */
    @Override
    public void setDueDate(LocalDateTime date) {
        this.dueDate = date;
    }

    /**
     * Gets the priority of the big task.
     *
     * @return The priority of the big task.
     */
    @Override
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the big task.
     *
     * @param priority The priority to be set.
     */
    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Gets the ID of the big task.
     *
     * @return The ID of the big task.
     */
    @Override
    public String getID() {
        return modelTask.getID();
    }

    /**
     * Gets the status of the big task.
     *
     * @return The status of the big task.
     */
    @Override
    public boolean getStatus() {
        return modelTask.getStatus();
    }

    /**
     * Sets the completion status of the big task.
     *
     * @param status The completion status to be set.
     */
    @Override
    public void setCompleted(boolean status) {
        modelTask.setCompleted(status);
    }

    /**
     * Gets the date created of the big task.
     *
     * @return The date created of the big task.
     */
    @Override
    public LocalDateTime getDateCreated() {
        return modelTask.getDateCreated();
    }

    /**
     * Gets the description of the big task.
     *
     * @return The description of the big task.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the big task.
     *
     * @param desc The description to be set.
     */
    @Override
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Adds a subtask to the big task.
     *
     * @param title The title of the subtask to be added.
     */
    @Override
    public void addSubTask(String title) {
        ITask newTask = taskFactory.createTask(title);
        subTaskMap.put(newTask.getID(), newTask);
        notifyNewItem(newTask);
    }

    /**
     * Removes a subtask from the big task.
     *
     * @param subTaskID The ID of the subtask to be removed.
     */
    @Override
    public void removeSubTask(String subTaskID) {
        subTaskMap.remove(subTaskID);
    }

    /**
     * Gets the map of subtasks in the big task.
     *
     * @return The map of subtasks in the big task.
     */
    @Override
    public HashMap<String, ITask> getSubTaskMap() {
        return subTaskMap;
    }

    /**
     * Adds an observer to the task.
     *
     * @param observer The observer to be added.
     */
    @Override
    public void addItemObserver(IItemObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the task.
     *
     * @param observer The observer to be removed.
     */
    @Override
    public void removeItemObserver(IItemObserver observer) {
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
