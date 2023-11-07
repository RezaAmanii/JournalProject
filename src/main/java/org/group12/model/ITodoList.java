package org.group12.model;

import java.util.ArrayList;

public interface ITodoList {
    void addTask(Task task);

    void removeTask(Task task);

    ArrayList<Task> getTasks();
}
