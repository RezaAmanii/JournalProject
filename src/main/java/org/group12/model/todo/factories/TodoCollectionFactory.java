package org.group12.model.todo.factories;

import org.group12.model.ItemsSet;
import org.group12.model.todo.ITaskList;
import org.group12.model.todo.TaskList;
import org.group12.model.todo.TodoCollection;

/**
 * Represents a factory for creating TodoCollection objects.
 * This class uses a TodoCollectionIDFactory to generate unique IDs for each TodoCollection.
 */
public class TodoCollectionFactory {
    private TodoCollectionIDFactory idFactory;
    private final ItemsSet items;

    /**
     * Constructs a new TodoCollection.
     * Initializes the TodoCollectionIDFactory used to generate IDs.
     */
    public TodoCollectionFactory(ItemsSet items) {
        this.idFactory = new TodoCollectionIDFactory();
        this.items = items;
    }

    /**
     * Creates a new TodoCollection with a unique ID and the given title.
     *
     * @param title the title of the TodoCollection
     * @return the created TodoCollection
     */
    public TodoCollection createTodoCollection(String title) {
        String ID = idFactory.generateID();
        return new TodoCollection(title, ID, items);
    }
}
