package org.group12.model.todo;

import org.group12.Observers.alternative.IItemObservable;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface ITodoCollection extends INameable, IItemObservable {
    // Methods for editing the TaskLists
    void addTaskList(String title);

    void removeTaskList(String listID);

    HashMap<String, ITaskList> getTaskListMap();
}