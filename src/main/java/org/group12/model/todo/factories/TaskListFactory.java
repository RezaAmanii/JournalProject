package org.group12.model.todo.factories;

import org.group12.model.todo.ITaskList;
import org.group12.model.todo.TaskList;

/**
 * Represents a factory for creating TaskList objects.
 * This class uses a JournalIDFactory to generate unique IDs for each Journal.
 */
public class TaskListFactory {
    private TaskListIDFactory idFactory;

    /**
     * Constructs a new JournalFactory.
     * Initializes the JournalIDFactory used to generate IDs.
     */
    public TaskListFactory() {
        this.idFactory = new TaskListIDFactory();
    }

    /**
     * Creates a new Journal with a unique ID and the given title.
     *
     * @param title the title of the Journal
     * @return the created Journal
     */
    public ITaskList createTaskList(String title) {
        String ID = idFactory.generateID();
        return new TaskList(title, ID);
    }
}
