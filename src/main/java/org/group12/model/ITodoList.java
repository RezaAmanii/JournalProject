package org.group12.model;

import java.util.ArrayList;

public interface ITodoList extends INameable{
    void addTask(Task task);

    void removeTask(Task task);

    ArrayList<Task> getTasks();
}
