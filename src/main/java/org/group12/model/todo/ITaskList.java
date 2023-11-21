package org.group12.model.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ITaskList{
    String getID();

    void addTask(String title);

    void removeTask(String ID);

    ArrayList<IBigTask> getTasks();
}