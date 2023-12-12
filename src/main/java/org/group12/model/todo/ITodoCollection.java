package org.group12.model.todo;

import org.group12.model.INameable;

import java.util.ArrayList;
import java.util.HashMap;

public interface ITodoCollection extends INameable {
    // Methods for editing the TaskLists
    String addTaskList(String title);

    void removeTaskList(ITaskList list);

    ArrayList<ITaskList> getTaskList();
}