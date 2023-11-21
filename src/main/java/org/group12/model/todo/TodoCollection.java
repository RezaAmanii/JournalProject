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
    public void setTaskListTitle(String title, String taskListID) {
        taskListMap.get(taskListID).setTitle(title);
    }

    @Override
    public void getTaskListTitle(String taskListID) {
        taskListMap.get(taskListID).getTitle();
    }

    @Override
    public LocalDateTime getTaskListDateCreated(String taskListID) {
        return taskListMap.get(taskListID).getDateCreated();
    }

    @Override
    public String getTaskListID(String taskListID) {
        return taskListMap.get(taskListID).getID();
    }


    // Methods for editing the Tasks inside TaskLists
    @Override
    public void addBigTask(String title, String taskListID) {
        taskListMap.get(taskListID).addBigTask(title);
    }

    @Override
    public void removeBigTask(String taskListID, String bigTaskID) {
        taskListMap.get(taskListID).removeBigTask(bigTaskID);
    }

    @Override
    public void setBigTaskTitle(String title, String taskListID, String bigTaskID) {
        taskListMap.get(taskListID).setTitle(title);
    }

    @Override
    public void getBigTaskTitle(String taskListID, String bigTaskID) {
        taskListMap.get(bigTaskID).getTitle();
    }

    @Override
    public LocalDateTime getBigTaskDueDate(String taskListID, String bigTaskID) {
        return taskListMap.get(taskListID).getBigTaskDueDate(bigTaskID);
    }

    @Override
    public void setBigTaskDueDate(LocalDateTime date, String taskListID, String bigTaskID) {
        taskListMap.get(taskListID).setBigTaskDueDate(date, bigTaskID);
    }

    @Override
    public int getBigTaskPriority(String taskListID, String bigTaskID) {
        return taskListMap.get(taskListID).getBigTaskPriority(bigTaskID);
    }

    @Override
    public void setBigTaskPriority(int priority, String taskListID, String bigTaskID) {
        taskListMap.get(taskListID).setBigTaskPriority(priority, bigTaskID);
    }

    @Override
    public String getBigTaskID(String taskListID, String bigTaskID) {
        return taskListMap.get(taskListID).getBigTaskID(bigTaskID);
    }

    @Override
    public boolean getBigTaskStatus(String taskListID, String bigTaskID) {
        return taskListMap.get(taskListID).getBigTaskStatus(bigTaskID);
    }

    @Override
    public void setBigTaskCompleted(boolean status, String taskListID, String bigTaskID) {
        taskListMap.get(taskListID).setBigTaskCompleted(status, bigTaskID);
    }

    @Override
    public LocalDateTime getBigTaskDateCreated(String taskListID, String bigTaskID) {
        return taskListMap.get(taskListID).getBigTaskDateCreated(bigTaskID);
    }

    @Override
    public String getBigTaskDescription(String taskListID, String bigTaskID) {
        return taskListMap.get(taskListID).getBigTaskDescription(bigTaskID);
    }

    @Override
    public void setBigTaskDescription(String desc, String taskListID, String bigTaskID) {
        taskListMap.get(taskListID).setBigTaskDescription(desc, bigTaskID);
    }

    //
    //

    @Override
    public void addSubTask(String title, String taskListID, String bigTaskID) {
        taskListMap.get(taskListID).addSubTask(title, bigTaskID);
    }

    @Override
    public void removeSubTask(String taskListID, String bigTaskID, String subTaskID) {
        taskListMap.get(taskListID).removeSubTask(bigTaskID, subTaskID);
    }

    @Override
    public String getSubTaskID(String taskListID, String bigTaskID, String subTaskID) {
        return taskListMap.get(taskListID).getSubTaskID(bigTaskID, subTaskID);
    }

    @Override
    public String getSubTaskTitle(String taskListID, String bigTaskID, String subTaskID) {
        return taskListMap.get(taskListID).getSubTaskTitle(bigTaskID, subTaskID);
    }

    @Override
    public void setSubTaskTitle(String title, String taskListID, String bigTaskID, String subTaskID) {
        taskListMap.get(taskListID).setSubTaskTitle(title, bigTaskID, subTaskID);
    }

    @Override
    public LocalDateTime getSubTaskDateCreated(String taskListID, String bigTaskID, String subTaskID) {
        return taskListMap.get(taskListID).getSubTaskDateCreated(bigTaskID, subTaskID);
    }

    @Override
    public boolean getSubTaskStatus(String taskListID, String bigTaskID, String subTaskID) {
        return taskListMap.get(taskListID).getSubTaskStatus(bigTaskID, subTaskID);
    }

    @Override
    public void setSubTaskCompleted(boolean status, String taskListID, String bigTaskID, String subTaskID) {
        taskListMap.get(taskListID).setSubTaskCompleted(status, bigTaskID, subTaskID);
    }
}