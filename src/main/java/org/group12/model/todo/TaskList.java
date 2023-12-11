package org.group12.model.todo;

import org.group12.model.ItemsSet;
import org.group12.model.todo.factories.BigTaskFactory;


import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a task list that contains big tasks.
 */
public class TaskList implements ITaskList {
    private String title;
    private LocalDateTime dateCreated;
    private final ArrayList<IBigTask> bigTaskList;
    private final String ID;
    private final BigTaskFactory bigTaskFactory;
    private final ItemsSet items;

    /**
     * Constructs a TaskList object with the given title and ID.
     *
     * @param title The title of the task list.
     * @param ID    The ID of the task list.
     */
    public TaskList(String title, String ID, ItemsSet items) {
        this.bigTaskList = new ArrayList<>();
        this.bigTaskFactory = new BigTaskFactory(items);
        this.ID = ID;
        setTitle(title);
        this.items = items;
        this.dateCreated = LocalDateTime.now();

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
    public String addBigTask(String title) {
        IBigTask newTask = bigTaskFactory.createBigTask(title);
        bigTaskList.add(newTask);
        items.addItem(newTask);
        return newTask.getID();
    }

    /**
     * Removes a big task from the task list.
     *
     * @param task The big task to be removed.
     */
    @Override
    public void removeBigTask(IBigTask task) {
        bigTaskList.remove(task);
        items.removeItem(task.getID());
    }

    /**
     * Gets the map of big tasks in the task list.
     *
     * @return The map of big tasks in the task list.
     */
    @Override
    public ArrayList<IBigTask> getBigTaskList() {
        return bigTaskList;
    }
}
