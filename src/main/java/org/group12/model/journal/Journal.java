package org.group12.model.journal;


import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Journal with a list of entries and associated functionality.
 */
public class Journal implements INameable, IObservable, Serializable {
    private final IJournalEntryFactory entryFactory;
    private String title;
    private final String ID;
    private final List<IPlanITObserver> observers;
    private final ItemsSet items;
    private final Map<LocalDate,IEntry> entries;
    /**
     * Constructs a Journal with the given ID, title, and entry factory.
     *
     * @param ID            the ID of the journal. Must not be null.
     * @param title         the title of the journal. Must not be null.
     * @param entryFactory  the factory for creating journal entries. Must not be null.
     * @param items         the items set to add the journal entries to. Must not be null.
     */
    protected Journal(String ID, String title, IJournalEntryFactory entryFactory, ItemsSet items) {
        this.entryFactory = entryFactory;
        this.title = title;
        this.ID = ID;
        this.observers = new ArrayList<>();
        this.items = items;
        this.entries = new HashMap<>();
    }

    /**
     * Sets the title of the journal and notifies all observers.
     *
     * @param title the new title of the journal
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
        notifyObservers();

    }

    /**
     * Gets the title of the journal.
     *
     * @return the title of the journal
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the ID of the journal.
     *
     * @return the ID of the journal
     */
    public String getID(){ return ID; }


    /**
     * Retrieves a JournalEntry by its date.
     *
     * @param date The date of the JournalEntry to retrieve. Must not be null.
     * @return The JournalEntry with the specified date, or null if no such JournalEntry exists.
     * @throws IllegalArgumentException if date is null.
     */
    public IEntry getEntryByDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        return entries.get(date);
    }


    /**
     * Adds a new JournalEntry with the specified date, notifies all observers.
     *
     * @param date The date of the JournalEntry to add. Must not be null.
     * @throws IllegalArgumentException if date is null.
     */
    public void addEntry(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        IEntry newEntry = entryFactory. createJournalEntry(date);
        for (IPlanITObserver observer : observers) {
            newEntry.addObserver(observer);
        }
        entries.put(date, newEntry);
        items.addItem(newEntry);
        notifyObservers();
    }

    /**
     * Adds an observer to the journal.
     *
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(IPlanITObserver observer) {
        observers.add(observer);

    }
    /**
     * Removes an observer from the journal.
     *
     * @param observer the observer to be removed
     */
    @Override
    public void removeObserver(IPlanITObserver observer) {
        observers.remove(observer);

    }
    /**
     * Notifies all observers of the journal.
     */
    @Override
    public void notifyObservers() {
        observers.forEach(IPlanITObserver::update);

    }

}
