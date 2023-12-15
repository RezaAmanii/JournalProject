package org.group12.model;

import org.group12.model.Calendar.Calendar;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalEntryFactory;
import org.group12.model.journal.JournalFactory;
import org.group12.model.todo.TodoCollection;
import org.group12.model.todo.factories.TodoCollectionFactory;

import java.io.Serializable;

/**
 * This class represents a container that holds the essential objects Calendar, Journal, and a TodoCollection.
 * It follows the Singleton design pattern, meaning only one instance of this class can exist.
 */
public class Container implements Serializable {
    private TodoCollectionFactory todoCollectionFactory;

    private Calendar calender;
    private Journal journal;
    private TodoCollection todoCollection;
    private static Container instance;
    private JournalFactory journalFactoryInstance;
    private JournalEntryFactory journalEntryFactoryInstance;

    private final ItemsSet items;

    /**
     * Private constructor to ensure only one instance of this class is created.
     * Initializes the Calendar, Journal, and a TodoCollection.
     */
    private Container() {

        this.items = SaveLoad.getInstance().getItemsInstance();

        this.todoCollectionFactory = TodoCollectionFactory.getInstance();
        this.todoCollection = todoCollectionFactory.createTodoCollection("MainTD");
        items.addItem(todoCollection);

        this.calender = new Calendar(items);

        this.journalEntryFactoryInstance = JournalEntryFactory.getInstance();
        this.journalFactoryInstance = JournalFactory.getInstance();
        journal = journalFactoryInstance.createJournal("Test Journal", journalEntryFactoryInstance, items);
    }

    /**
     * Returns the single instance of this class. If the instance does not exist, it is created.
     * @return the single instance of this class
     */
    public static Container getInstance(){
        if(instance == null){
            instance = new Container();
        }
        return instance;
    }

    /**
     * Sets the todoCollection of this container.
     * @param todoCollection the new todoCollection
     */
    public void setTodoCollection(TodoCollection todoCollection) {
        this.todoCollection = todoCollection;
    }

    /**
     * Returns the calendar of this container.
     * @return the calendar
     */
    public Calendar getCalender() {
        return calender;
    }

    /**
     * Returns the journal of this container.
     * @return the journal
     */
    public Journal getJournal() {
        return journal;
    }

    /**
     * Returns the todoCollection of this container.
     * @return the todoCollection
     */
    public TodoCollection getTodoCollection() {
        return todoCollection;
    }
}