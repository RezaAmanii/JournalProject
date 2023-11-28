package org.group12.model;

import org.group12.Observers.items_observers.IItemObservable;
import org.group12.Observers.items_observers.IItemObserver;
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
        addItemObserver(items);

        this.todoCollectionFactory = new TodoCollectionFactory();
        this.todoCollection = todoCollectionFactory.createTodoCollection("MainTD");
        notifyNewItem(todoCollection);
        this.todoCollection.addItemObserver(items);

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

    @Override
    public void addItemObserver(IItemObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeItemObserver(IItemObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyNewItem(INameable newItem) {
        for (IItemObserver observer : observers) {
            observer.addItem(newItem);
        }
    }

    @Override
    public void notifyRemoveItem(String itemID) {
        for (IItemObserver observer : observers) {
            observer.removeItem(itemID);
        }
    }
}