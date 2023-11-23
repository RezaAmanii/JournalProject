package org.group12.model.todo;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface ITodoCollection {
    // Methods for editing the TaskLists
    void addTaskList(String title);

    void removeTaskList(String listID);

    HashMap<String, ITaskList> getTaskListMap();
}