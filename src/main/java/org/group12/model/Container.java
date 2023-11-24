package org.group12.model;

import org.group12.model.Calendar.Calendar;
import org.group12.model.journal.Journal;
import org.group12.model.todo.TodoCollection;

import java.util.HashMap;

public class Container {

    private Calendar calender;
    private Journal journal;
    private TodoCollection todoCollection;
    private final HashMap<String, INameable> itemMap;

    public Container() {
        this.calender = new Calendar();
        this.journal = new Journal("tempID", "temp title", null);
        this.todoCollection = new TodoCollection();
        this.itemMap = new HashMap<>();;
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

    public HashMap<String, INameable> getItemMap() {
        return itemMap;
    }
}
