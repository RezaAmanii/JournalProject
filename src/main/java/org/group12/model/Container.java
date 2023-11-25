package org.group12.model;

import org.group12.Observers.alternative.IItemObservable;
import org.group12.Observers.alternative.IItemObserver;
import org.group12.model.Calendar.Calendar;
import org.group12.model.journal.Journal;
import org.group12.model.todo.TodoCollection;
import org.group12.model.todo.factories.TodoCollectionFactory;

import java.util.ArrayList;

public class Container implements IItemObservable {
    private TodoCollectionFactory todoCollectionFactory;

    private Calendar calender;
    private Journal journal;
    private TodoCollection todoCollection;

    private final ArrayList<IItemObserver> observers;

    public Container(IItemObserver items) {
        this.observers = new ArrayList<>();
        addObserver(items);
        this.todoCollectionFactory = new TodoCollectionFactory();

        this.calender = new Calendar();
        this.journal = new Journal("tempID", "temp title", null);
        this.todoCollection = todoCollectionFactory.createTodoCollection("MainTD");

        notifyNewItem(todoCollection);
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

    @Override
    public void addObserver(IItemObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IItemObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyNewItem(INameable newItem) {
        for (IItemObserver observer : observers) {
            observer.addItem(newItem);
        }
    }

    @Override
    public void notifyRemoveItem(INameable newItem) {
        for (IItemObserver observer : observers) {
            observer.removeItem(newItem);
        }
    }
}