package org.group12.model.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ITaskList{
    long getID();

    void addTask(BigTask task);

    void removeTask(BigTask task);

    ArrayList<BigTask> getTasks();
}
