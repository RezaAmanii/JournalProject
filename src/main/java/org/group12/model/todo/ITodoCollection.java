package org.group12.model.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ITodoCollection {
    // Methods for editing the TaskLists
    void addTaskList(String title);

    void removeTaskList(String listID);

    void setListTitle(String title, String listID);

    void getListTitle(String listID);

    LocalDateTime getListDateCreated(String listID);

    // Methods for editing the Tasks inside TaskLists
    void addTask(String listID, String taskID);

    void removeTask(String listID, String taskID);

    void setTaskTitle(String title, String listID, String taskID);

    void getTaskTitle(String listID, String taskID);
}