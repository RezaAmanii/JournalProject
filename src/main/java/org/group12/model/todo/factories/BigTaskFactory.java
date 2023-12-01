package org.group12.model.todo.factories;

import org.group12.model.IDFactory;
import org.group12.model.IIDFactory;
import org.group12.model.todo.BigTask;
import org.group12.model.todo.IBigTask;

/**
 * Represents a factory for creating BigTask objects.
 * This class uses a BigTaskIDFactory to generate unique IDs for each BigTask.
 */
public class BigTaskFactory {
    private final IIDFactory idFactory;

    /**
     * Constructs a new BigTaskFactory.
     * Initializes the BigTaskIDFactory used to generate IDs.
     */
    public BigTaskFactory() {
        this.idFactory = IDFactory.getInstance(BigTaskIDFactory.class);
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
