package org.group12.model.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ITaskList{
    long getID();

    void addTask(Task task);

    void removeTask(Task task);

    ArrayList<Task> getTasks();
}
