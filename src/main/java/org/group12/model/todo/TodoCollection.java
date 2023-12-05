package org.group12.model.todo;

import org.group12.model.ItemsSet;
import org.group12.model.todo.factories.TaskListFactory;

import java.util.HashMap;

/**
 * Represents a collection of todo lists.
 * Implements the ITodoCollection interface.
 */
public class TodoCollection implements ITodoCollection{
    String ID;
    String title;
    private final HashMap<String, ITaskList> taskListMap;
    private final TaskListFactory taskListFactory;

    private final ItemsSet items;

    /**
     * Constructs a new TodoCollection object with the specified title and ID.
     * Initializes the taskListMap, taskListFactory, title, and ID.
     *
     * @param title the title of the TodoCollection
     * @param ID the ID of the TodoCollection
     */
    public TodoCollection (String title, String ID, ItemsSet items){
        taskListMap = new HashMap<>();
        taskListFactory = new TaskListFactory(items);
        this.items = items;
        this.title = title;
        this.ID = ID;
    }

    // Methods for editing the TaskLists

    /**
     * Adds a new task list to the collection with the specified title.
     * Creates a new task list using the taskListFactory and adds it to the taskListMap.
     * Adds the task list to items.
     *
     * @param title the title of the new task list
     */
    @Override
    public void addTaskList(String title) {
        ITaskList newList = taskListFactory.createTaskList(title);
        taskListMap.put(newList.getID(), newList);
        items.addItem(newList);
    }

    /**
     * Removes the task list with the specified ID from the collection.
     * Removes the task list from the taskListMap.
     * Removes the task list items.
     *
     * @param taskListID the ID of the task list to be removed
     */
    @Override
    public void removeTaskList(String taskListID) {
        taskListMap.remove(taskListID);
        items.removeItem(taskListID);
    }

    /**
     * Returns the task list map of the TodoCollection.
     *
     * @return the task list map
     */
    @Override
    public HashMap<String, ITaskList> getTaskListMap() {
        return taskListMap;
    }

    /**
     * Returns the ID of the collection.
     *
     * @return the ID of the collection
     */
    @Override
    public String getID() {
        return ID;
    }

    /**
     * Sets the title of the collection.
     *
     * @param title the new title of the collection
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the title of the collection.
     *
     * @return the title of the collection
     */
    @Override
    public String getTitle() {
        return title;
    }
}