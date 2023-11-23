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
    public void removeTaskList(String taskListID) {
        taskListMap.remove(taskListID);
    }

    @Override
    public HashMap<String, ITaskList> getTaskListMap() {
        return taskListMap;
    }
}