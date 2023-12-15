package org.group12.model.todo.factories;

import org.group12.model.IDFactory.IDFactory;
import org.group12.model.IDFactory.IIDFactory;
import org.group12.model.IDFactory.TaskIDFactory;
import org.group12.model.todo.ITask;
import org.group12.model.todo.Task;

import java.io.Serializable;

/**
 * Represents a factory for creating Task objects.
 * This class uses a TaskIDFactory to generate unique IDs for each Task.
 */
public class TaskFactory implements Serializable {
    private IIDFactory idFactory;
    private static TaskFactory instance;

    /**
     * Constructs a new TaskFactory.
     * Initializes the TaskIDFactory used to generate IDs.
     */
    private TaskFactory() {
        this.idFactory = IDFactory.getInstance(TaskIDFactory.class);
    }

    public static TaskFactory getInstance() {
        if (instance == null) {
            instance = new TaskFactory();
        }
        return instance;
    }

    /**
     * Creates a new Journal with a unique ID and the given title.
     *
     * @param title the title of the Task
     * @return the created Task
     */
    public ITask createTask(String title) {
        String ID = idFactory.generateID();
        return new Task(title, ID);
    }

}
