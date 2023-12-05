package org.group12.model;

import org.group12.model.Calendar.Calendar;
import org.group12.model.journal.Journal;
import org.group12.model.todo.TodoCollection;
import org.group12.model.todo.factories.TodoCollectionFactory;

public class Container {
    private TodoCollectionFactory todoCollectionFactory;

    private Calendar calender;
    private Journal journal;
    private TodoCollection todoCollection;
    private static Container instance;

    private ItemsSet items;

    private Container() {

        this.items = Items.getInstance();
        this.todoCollectionFactory = new TodoCollectionFactory(items);
        this.todoCollection = todoCollectionFactory.createTodoCollection("MainTD");
        items.addItem(todoCollection);

        this.calender = new Calendar(items);

        //Journal
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