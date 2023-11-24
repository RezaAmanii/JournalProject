package org.group12.model.todo;

import org.group12.Observers.IObservable;
import org.group12.model.IDescription;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface IBigTask extends ITask, IDescription, IObservable {
    LocalDateTime getDueDate();

    void setDueDate(LocalDateTime date);

    int getPriority();

    void setPriority(int priority);

    // Methods for editing the subTasks

    void addSubTask(String title);

    void removeSubTask(String subTaskID);

    HashMap<String, ITask> getSubTaskMap();
}
