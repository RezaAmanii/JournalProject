package org.group12.model.todo;

import org.group12.model.IDateCreated;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface ITaskList extends INameable, IDateCreated {
    String getID();

    // Methods for editing Bigtasks
    void addBigTask(String title);

    void removeBigTask(String ID);

    HashMap<String, IBigTask> getBigTaskMap();
}