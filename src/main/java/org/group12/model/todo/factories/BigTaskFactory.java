package org.group12.model.todo.factories;

import org.group12.model.IDFactory.BigTaskIDFactory;
import org.group12.model.IDFactory.IDFactory;
import org.group12.model.IDFactory.IIDFactory;
import org.group12.model.ItemsSet;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.todo.BigTask;
import org.group12.model.todo.IBigTask;

import java.io.Serializable;

/**
 * Represents a factory for creating BigTask objects.
 * This class uses a BigTaskIDFactory to generate unique IDs for each BigTask.
 */
public class BigTaskFactory implements Serializable {
    private final IIDFactory idFactory;
    private final ItemsSet items;
    private static BigTaskFactory instance;

    /**
     * Constructs a new BigTaskFactory.
     * Initializes the BigTaskIDFactory used to generate IDs.
     */
    private BigTaskFactory() {
        this.idFactory = IDFactory.getInstance(BigTaskIDFactory.class);
        this.items = SaveLoad.getInstance().getItemsInstance();
    }

    public static BigTaskFactory getInstance() {
        if (instance == null) {
            instance = new BigTaskFactory();
        }
        return instance;
    }

    /**
     * Creates a new BigTask with a unique ID and the given title.
     *
     * @param title the title of the BigTask
     * @return the created BigTask
     */
    public IBigTask createBigTask(String title) {
        String ID = idFactory.generateID();
        return new BigTask(title, ID, items);
    }

}
