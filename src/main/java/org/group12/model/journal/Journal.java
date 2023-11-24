package org.group12.model.journal;


import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.INameable;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a Journal with a list of entries and associated functionality.
 */
public class Journal implements INameable, IObservable{
    private List<JournalEntry> entryList;
    private JournalEntryFactory entryFactory;
    private String title;
    private final String ID;
    private List<IPlanITObserver> observers;
    /**
     * Constructs a Journal with the given ID, title, and entry factory.
     *
     * @param ID            the ID of the journal
     * @param title         the title of the journal
     * @param entryFactory  the factory for creating journal entries
     */
    public Journal(String ID, String title, JournalEntryFactory entryFactory) {
        this.entryList = new ArrayList<>();
        this.entryFactory = entryFactory;
        this.title = title;
        this.ID = ID;
        this.observers = new ArrayList<>();
    }


    /**
     * Adds a new entry to the journal with the provided title and content.
     * Notifies all observers of the journal and the new entry.
     *
     * @param title   the title for the new journal entry
     * @param content the content for the new journal entry
     */
    public void addEntry(String title, String content){
        JournalEntry newEntry = entryFactory.createJournalEntry(content);
        for (IPlanITObserver observer : observers) {
            newEntry.addObserver(observer);
        }
        entryList.add(newEntry);
        notifyObservers();
    }

    /**
     * Removes the specified entry from the journal.
     * Notifies all observers of the journal.
     *
     * @param entry the entry to be removed
     */
    public void removeEntry(JournalEntry entry){
        entryList.remove(entry);
        notifyObservers();
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
     * Gets a list of the entries in the journal.
     *
     * @return a list of entries in the journal
     */
    public List<JournalEntry> getEntries() {
        return entryList;
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
