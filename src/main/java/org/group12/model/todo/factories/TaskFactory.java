package org.group12.model.todo.factories;

import org.group12.model.todo.Task;

/**
 * Represents a factory for creating Task objects.
 * This class uses a TaskIDFactory to generate unique IDs for each Journal.
 */
public class TaskFactory {
    private TaskIDFactory idFactory;

    /**
     * Constructs a new TaskFactory.
     * Initializes the TaskIDFactory used to generate IDs.
     */
    public TaskFactory() {
        this.idFactory = new TaskIDFactory();
    }

    /**
     * Creates a new Journal with a unique ID and the given title.
     *
     * @param title the title of the Task
     * @return the created Task
     */
    public Task createTask(String title) {
        String ID = idFactory.generateID();
        return new Task(title, ID);
    }

}
