package org.group12.model.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ITodoCollection {
    // Methods for editing the TaskLists
    void addTaskList(String title);

    void removeTaskList(String listID);

    void setTaskListTitle(String title, String listID);

    void getTaskListTitle(String listID);

    LocalDateTime getTaskListDateCreated(String listID);

    String getTaskListID(String taskListID);

    // Methods for editing the Tasks inside TaskLists
    void addBigTask(String listID, String taskID);

    void removeBigTask(String listID, String taskID);

    void setBigTaskTitle(String title, String listID, String taskID);

    void getBigTaskTitle(String listID, String taskID);

    LocalDateTime getBigTaskDueDate(String taskListID, String bigTaskID);

    void setBigTaskDueDate(LocalDateTime date, String taskListID, String bigTaskID);

    int getBigTaskPriority(String taskListID, String bigTaskID);

    void setBigTaskPriority(int priority, String taskListID, String bigTaskID);

    String getBigTaskID(String taskListID, String bigTaskID);

    boolean getBigTaskStatus(String taskListID, String bigTaskID);

    void setBigTaskCompleted(boolean status, String taskListID, String bigTaskID);

    LocalDateTime getBigTaskDateCreated(String taskListID, String bigTaskID);

    String getBigTaskDescription(String taskListID, String bigTaskID);

    void setBigTaskDescription(String desc, String taskListID, String bigTaskID);

    void addSubTask(String title, String taskListID, String bigTaskID);

    void removeSubTask(String taskListID, String bigTaskID, String subTaskID);

    String getSubTaskID(String taskListID, String bigTaskID, String subTaskID);

    String getSubTaskTitle(String taskListID, String bigTaskID, String subTaskID);

    void setSubTaskTitle(String title, String taskListID, String bigTaskID, String subTaskID);

    LocalDateTime getSubTaskDateCreated(String taskListID, String bigTaskID, String subTaskID);

    boolean getSubTaskStatus(String taskListID, String bigTaskID, String subTaskID);

    void setSubTaskCompleted(boolean status, String taskListID, String bigTaskID, String subTaskID);
}