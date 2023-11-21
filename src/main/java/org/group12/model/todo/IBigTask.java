package org.group12.model.todo;

import org.group12.model.IDescription;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface IBigTask extends ITask, IDescription {
    LocalDateTime getDueDate();

    void setDueDate(LocalDateTime date);

    int getPriority();

    void setPriority(int priority);

    // Methods for editing the subTasks

    void addSubTask(String title);

    void removeSubTask(String subTaskID);

    String getSubTaskID(String subTaskID);

    String getSubTaskTitle(String subTaskID);

    void setSubTaskTitle(String title, String subTaskID);

    LocalDateTime getSubTaskDateCreated(String subTaskID);

    boolean getSubTaskStatus(String subTaskID);

    void setSubTaskCompleted(boolean status, String subTaskID);

    HashMap<String, ITask> getSubTaskMap();
}
