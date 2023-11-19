package org.group12.model.todo;

import java.util.ArrayList;

public interface ITodoCollection {
    ArrayList<ITaskList> getTaskLists();
    void addTaskList();
    void remove();
    void edit();
}
