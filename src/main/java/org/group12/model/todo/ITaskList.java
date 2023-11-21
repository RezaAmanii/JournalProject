package org.group12.model.todo;

import org.group12.model.IDateCreated;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ITaskList extends INameable, IDateCreated {
    String getID();

    // Methods for editing tasks
    void addTask(String title);

    void removeTask(String ID);

    void setTaskTitle(String title, String taskID);

    void getTaskTitle(String taskID);
}