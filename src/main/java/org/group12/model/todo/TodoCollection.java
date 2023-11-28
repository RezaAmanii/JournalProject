package org.group12.model.todo;

import org.group12.Observers.items_observers.IItemObserver;
import org.group12.model.INameable;
import org.group12.model.todo.factories.TaskListFactory;

import java.util.ArrayList;
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

    private final ArrayList<IItemObserver> observers;

    /**
     * Constructs a new TodoCollection object with the specified title and ID.
     * Initializes the taskListMap, taskListFactory, observers, title, and ID.
     *
     * @param title the title of the TodoCollection
     * @param ID the ID of the TodoCollection
     */
    public TodoCollection (String title, String ID){
        taskListMap = new HashMap<>();
        taskListFactory = new TaskListFactory();
        this.observers = new ArrayList<>();
        this.title = title;
        this.ID = ID;
    }

    // Methods for editing the TaskLists

    /**
     * Adds a new task list to the collection with the specified title.
     * Creates a new task list using the taskListFactory and adds it to the taskListMap.
     * Notifies the Item observers about the new item.
     *
     * @param title the title of the new task list
     */
    @Override
    public void addTaskList(String title) {
        ITaskList newList = taskListFactory.createTaskList(title);
        taskListMap.put(newList.getID(), newList);
        notifyNewItem(newList);
        newList.addItemObserver(observers.get(0));
    }

    /**
     * Removes the task list with the specified ID from the collection.
     * Removes the task list from the taskListMap.
     * Notifies the observers about the removed item.
     *
     * @param taskListID the ID of the task list to be removed
     */
    @Override
    public void removeTaskList(String taskListID) {
        taskListMap.remove(taskListID);
        notifyRemoveItem(taskListID);
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

    /**
     * Adds an observer to the collection.
     *
     * @param observer the observer to be added
     */
    @Override
    public void addItemObserver(IItemObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the collection.
     *
     * @param observer the observer to be removed
     */
    @Override
    public void removeItemObserver(IItemObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies the observers about a new item added to the collection.
     *
     * @param newItem the new item added to the collection
     */
    @Override
    public void notifyNewItem(INameable newItem) {
        for (IItemObserver observer : observers) {
            observer.addItem(newItem);
        }
    }

    /**
     * Notifies the observers about an item removed from the collection.
     *
     * @param itemID the ID of the item removed from the collection
     */
    @Override
    public void notifyRemoveItem(String itemID) {
        for (IItemObserver observer : observers) {
            observer.removeItem(itemID);
        }
    }
}