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

    private final ItemsSet items;

    private static Container instance;

    public static Container getInstance() {
        if(instance == null) {
            instance = new Container(Items.getInstance());
        }
        return instance;
    }

    public Container(ItemsSet items) {
        this.items = items;

        this.todoCollectionFactory = new TodoCollectionFactory(items);
        this.todoCollection = todoCollectionFactory.createTodoCollection("MainTD");
        items.addItem(todoCollection);

        this.calender = new Calendar();

        this.journal = new Journal("tempID", "temp title", null);
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