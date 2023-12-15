package org.group12.model.todo.factories;

import org.group12.model.IDFactory.IDFactory;
import org.group12.model.IDFactory.IIDFactory;
import org.group12.model.IDFactory.TaskListIDFactory;
import org.group12.model.ItemsSet;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.todo.ITaskList;
import org.group12.model.todo.TaskList;

import java.io.Serializable;

/**
 * Represents a factory for creating TaskList objects.
 * This class uses a TaskListIDFactory to generate unique IDs for each TaskList.
 */
public class TaskListFactory implements Serializable {
    private IIDFactory idFactory;
    private final ItemsSet items;
    private static TaskListFactory instance = null;

    /**
     * Constructs a new TaskListFactory.
     * Initializes the TaskListIDFactory used to generate IDs.
     */
    private TaskListFactory() {
        this.idFactory = IDFactory.getInstance(TaskListIDFactory.class);
        this.items = SaveLoad.getInstance().getItemsInstance();
    }

    public static TaskListFactory getInstance() {
        if (instance == null) {
            instance = new TaskListFactory();
        }
        return instance;
    }

    /**
     * Creates a new TaskList with a unique ID and the given title.
     *
     * @param title the title of the TaskList
     * @return the created TaskList
     */
    public ITaskList createTaskList(String title) {
        String ID = idFactory.generateID();
        return new TaskList(title, ID, items);
    }
}
