package org.group12.model;

import org.group12.model.Calendar.Calendar;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalEntryFactory;
import org.group12.model.journal.JournalFactory;
import org.group12.model.todo.TodoCollection;
import org.group12.model.todo.factories.TodoCollectionFactory;

import java.io.Serializable;

public class Container implements Serializable {
    private TodoCollectionFactory todoCollectionFactory;

    private Calendar calender;
    private Journal journal;
    private TodoCollection todoCollection;
    private static Container instance;
    private JournalFactory journalFactoryInstance;
    private JournalEntryFactory journalEntryFactoryInstance;

    private final ItemsSet items;

    private Container() {

        this.items = SaveLoad.getInstance().getItemsInstance();

        this.todoCollectionFactory = TodoCollectionFactory.getInstance();
        this.todoCollection = todoCollectionFactory.createTodoCollection("MainTD");
        items.addItem(todoCollection);

        this.calender = new Calendar(items);

        //Journal
        //this.journal = new Journal("0", "temp title", null);
        this.journalEntryFactoryInstance = JournalEntryFactory.getInstance();
        this.journalFactoryInstance = JournalFactory.getInstance();
        journal = journalFactoryInstance.createJournal("Test Journal", journalEntryFactoryInstance, items);
    }

    public static Container getInstance(){
        if(instance == null){
            instance = new Container();
        }
        return instance;
    }
    public void setTodoCollection(TodoCollection todoCollection) {
        this.todoCollection = todoCollection;
    }

    public Calendar getCalender() {
        return calender;
    }

    public Journal getJournal() {
        return journal;
    }

    public TodoCollection getTodoCollection() {
        return todoCollection;
    }
}