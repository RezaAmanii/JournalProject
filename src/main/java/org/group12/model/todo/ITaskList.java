package org.group12.model.todo;

import org.group12.model.IDateCreated;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface ITaskList extends INameable, IDateCreated {
    String getID();

    // Methods for editing Bigtasks
    void addBigTask(String title);

    void removeBigTask(String ID);
    void setBigTaskTitle(String title, String bigTaskID);

    void getBigTaskTitle(String bigTaskID);

    LocalDateTime getBigTaskDueDate(String bigTaskID);

    void setBigTaskDueDate(LocalDateTime date, String bigTaskID);

    int getBigTaskPriority(String bigTaskID);

    void setBigTaskPriority(int priority, String bigTaskID);

    String getBigTaskID(String bigTaskID);

    boolean getBigTaskStatus(String bigTaskID);

    void setBigTaskCompleted(boolean status, String bigTaskID);

    LocalDateTime getBigTaskDateCreated(String bigTaskID);

    String getBigTaskDescription(String bigTaskID);

    void setBigTaskDescription(String desc, String bigTaskID);

    HashMap<String, IBigTask> getBigTaskMap();

    //
    // Methods for editing the subTasks inside BigTasks in TaskLists

    void addSubTask(String title, String bigTaskID);

    void removeSubTask(String bigTaskID, String subTaskID);

    String getSubTaskID(String bigTaskID, String subTaskID);

    String getSubTaskTitle(String bigTaskID, String subTaskID);

    void setSubTaskTitle(String title, String bigTaskID, String subTaskID);

    LocalDateTime getSubTaskDateCreated(String bigTaskID, String subTaskID);

    boolean getSubTaskStatus(String bigTaskID, String subTaskID);

    void setSubTaskCompleted(boolean status, String bigTaskID, String subTaskID);

    HashMap<String, ITask> getSubTaskMap(String bigTaskID);
}