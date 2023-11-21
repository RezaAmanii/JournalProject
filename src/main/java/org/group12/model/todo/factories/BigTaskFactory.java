package org.group12.model.todo.factories;

import org.group12.model.todo.BigTask;
import org.group12.model.todo.IBigTask;

/**
 * Represents a factory for creating BigTask objects.
 * This class uses a BigTaskIDFactory to generate unique IDs for each BigTask.
 */
public class BigTaskFactory {
    private final BigTaskIDFactory idFactory;

    /**
     * Constructs a new TaskFactory.
     * Initializes the TaskIDFactory used to generate IDs.
     */
    public BigTaskFactory() {
        this.idFactory = new BigTaskIDFactory();
    }

    /**
     * Creates a new BigTask with a unique ID and the given title.
     *
     * @param title the title of the BigTask
     * @return the created BigTask
     */
    public IBigTask createBigTask(String title) {
        String ID = idFactory.generateID();
        return new BigTask(title, ID);
    }

}
