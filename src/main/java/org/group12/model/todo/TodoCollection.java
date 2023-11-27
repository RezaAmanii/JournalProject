package org.group12.model.todo;

import org.group12.Observers.IPlanITObserver;
import org.group12.Observers.alternative.IItemObserver;
import org.group12.model.INameable;
import org.group12.model.Items;
import org.group12.model.todo.factories.TaskListFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class TodoCollection implements ITodoCollection{
    String ID;
    String title;
    private final HashMap<String, ITaskList> taskListMap;
    private final TaskListFactory taskListFactory;

    private final ArrayList<IItemObserver> observers;

    public TodoCollection (String title, String ID){
        taskListMap = new HashMap<>();
        taskListFactory = new TaskListFactory();
        this.observers = new ArrayList<>();
        this.title = title;
        this.ID = ID;
    }

    // Methods for editing the TaskLists
    @Override
    public void addTaskList(String title) {
        ITaskList newList = taskListFactory.createTaskList(title);
        taskListMap.put(newList.getID(), newList);
        notifyNewItem(newList);
    }

    @Override
    public void removeTaskList(String taskListID) {
        taskListMap.remove(taskListID);
        notifyRemoveItem(taskListID);
    }

    @Override
    public HashMap<String, ITaskList> getTaskListMap() {
        return taskListMap;
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