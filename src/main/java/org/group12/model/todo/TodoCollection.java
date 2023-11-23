package org.group12.model.todo;

import org.group12.Observers.IPlanITObserver;
import org.group12.model.todo.factories.TaskListFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class TodoCollection implements ITodoCollection{
    private final HashMap<String, ITaskList> taskListMap;
    private final TaskListFactory taskListFactory;

    private final ArrayList<IPlanITObserver> observers;

    public TodoCollection (){
        taskListMap = new HashMap<>();
        taskListFactory = new TaskListFactory();
        this.observers = new ArrayList<>();
    }

    // Methods for editing the TaskLists
    @Override
    public void addTaskList(String title) {
        ITaskList newList = taskListFactory.createTaskList(title);
        taskListMap.put(newList.getID(), newList);
    }

    @Override
    public void removeTaskList(String taskListID) {
        taskListMap.remove(taskListID);
    }

    @Override
    public HashMap<String, ITaskList> getTaskListMap() {
        return taskListMap;
    }

    @Override
    public void addObserver(IPlanITObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPlanITObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IPlanITObserver observer : observers) {
            //observer.update();
        }
    }
}