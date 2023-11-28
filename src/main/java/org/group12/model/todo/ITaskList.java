package org.group12.model.todo;

import org.group12.Observers.items_observers.IItemObservable;
import org.group12.model.IDateCreated;
import org.group12.model.INameable;

import java.util.HashMap;

public interface ITaskList extends INameable, IDateCreated, IItemObservable {
    // Methods for editing Bigtasks
    void addBigTask(String title);

    void removeBigTask(String ID);

    HashMap<String, IBigTask> getBigTaskMap();
}