package org.group12.model.todo.factories;

import org.group12.model.IDFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Singleton factory for generating IDs for Task objects.
 * This class extends the IDFactory abstract class and provides the specific implementation for Task.
 */
public class TaskIDFactory extends IDFactory {
    private static final String PREFIX = "TK";
    private static final AtomicLong counter = new AtomicLong(1);


    /**
     * Private constructor to prevent creating multiple instances of the class.
     */
    private TaskIDFactory(){

    }


    /**
     * Returns the prefix for the IDs generated by this factory.
     *
     * @return the prefix for the IDs
     */
    @Override
    protected String getPrefix() {
        return PREFIX;
    }
    /**
     * Returns the counter for the IDs generated by this factory.
     *
     * @return the counter for the IDs
     */
    @Override
    protected AtomicLong getCounter() {
        return counter;
    }

    /**
     * Returns the type of object for which this factory generates IDs.
     *
     * @return the type of object
     */
    @Override
    protected String getObjectType() {
        return "Task";
    }
}

