package org.group12.model.todo.factories;

import org.group12.model.IDFactory;
import org.group12.model.IIDFactory;
import org.group12.model.todo.ITaskList;
import org.group12.model.todo.TaskList;

/**
 * Represents a factory for creating TaskList objects.
 * This class uses a TaskListIDFactory to generate unique IDs for each TaskList.
 */
public class TaskListFactory {
    private IIDFactory idFactory;

    /**
     * Constructs a new TaskListFactory.
     * Initializes the TaskListIDFactory used to generate IDs.
     */
    public TaskListFactory() {
        this.idFactory = IDFactory.getInstance(TaskListIDFactory.class);
    }

    /**
     * Creates a new TaskList with a unique ID and the given title.
     *
     * @param title the title of the TaskList
     * @return the created TaskList
     */
    public ITaskList createTaskList(String title) {
        String ID = idFactory.generateID();
        return new TaskList(title, ID);
    }
}
