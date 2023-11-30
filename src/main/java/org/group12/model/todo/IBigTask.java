package org.group12.model.todo;

import org.group12.model.IDescription;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface IBigTask extends ITask, IDescription {
    LocalDateTime getDueDate();

    void setDueDate(LocalDateTime date);

    boolean isFavourite();

    void setFavourite(boolean status);

    // Methods for editing the subTasks

    void addSubTask(String title);

    void removeSubTask(String subTaskID);

    HashMap<String, ITask> getSubTaskMap();
}
