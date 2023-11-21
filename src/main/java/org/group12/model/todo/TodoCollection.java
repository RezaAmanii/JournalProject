package org.group12.model.todo;

import org.group12.model.todo.factories.TaskListFactory;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TodoCollection implements ITodoCollection{
    private final HashMap<String, ITaskList> taskListMap;
    private final TaskListFactory taskListFactory;

    public TodoCollection (){
        taskListMap = new HashMap<>();
        taskListFactory = new TaskListFactory();
    }

    // Methods for editing the TaskLists
    @Override
    public void addTaskList(String title) {
        ITaskList newList = taskListFactory.createTaskList(title);
        taskListMap.put(newList.getID(), newList);
    }

    @Override
    public void removeTaskList(String listID) {
        taskListMap.remove(listID);
    }

    @Override
    public void setListTitle(String title, String listID) {
        taskListMap.get(listID).setTitle(title);
    }

    @Override
    public void getListTitle(String listID) {
        taskListMap.get(listID).getTitle();
    }

    @Override
    public LocalDateTime getListDateCreated(String listID) {
        return taskListMap.get(listID).getDateCreated();
    }


    // Methods for editing the Tasks inside TaskLists
    @Override
    public void addTask(String listID, String taskID) {
        taskListMap.get(listID).addTask(taskID);
    }

    @Override
    public void removeTask(String listID, String taskID) {
        taskListMap.get(listID).removeTask(taskID);
    }

    @Override
    public void setTaskTitle(String title, String listID, String taskID) {
        taskListMap.get(listID).setTitle(title);
    }

    @Override
    public void getTaskTitle(String listID, String taskID) {
        taskListMap.get(listID).getTitle();
    }
}
