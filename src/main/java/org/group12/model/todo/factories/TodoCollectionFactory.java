package org.group12.model.todo.factories;

import org.group12.model.IDFactory.IDFactory;
import org.group12.model.IDFactory.IIDFactory;
import org.group12.model.IDFactory.TodoCollectionIDFactory;
import org.group12.model.ItemsSet;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.todo.ITaskList;
import org.group12.model.todo.TaskList;
import org.group12.model.todo.TodoCollection;

import java.io.Serializable;

/**
 * Represents a factory for creating TodoCollection objects.
 * This class uses a TodoCollectionIDFactory to generate unique IDs for each TodoCollection.
 */
public class TodoCollectionFactory implements Serializable {
    private IIDFactory idFactory;
    private final ItemsSet items;
    private static TodoCollectionFactory instance;


    /**
     * Constructs a new TodoCollection.
     * Initializes the TodoCollectionIDFactory used to generate IDs.
     */
    private TodoCollectionFactory(){
        this.idFactory = IDFactory.getInstance(TodoCollectionIDFactory.class);
        this.items = SaveLoad.getInstance().getItemsInstance();
    }

    public static TodoCollectionFactory getInstance(){
        if(instance == null){
            instance = new TodoCollectionFactory();
        }
        return instance;
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
