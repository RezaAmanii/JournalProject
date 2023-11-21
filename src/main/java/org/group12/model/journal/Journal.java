package org.group12.model.journal;

import org.group12.observers.JournalObserver;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a Journal with a list of entries and associated functionality.
 */
public class Journal {
    private List<JournalEntry> entryList;
    private boolean isEmpty;
    private List<JournalObserver> observers;
    private JournalEntryFactory entryFactory;
    private String title;
    /**
     * Constructs a Journal with the given ID, title, and entry factory.
     *
     * @param ID            the ID of the journal
     * @param title         the title of the journal
     * @param entryFactory  the factory for creating journal entries
     */
    public Journal(String ID, String title, JournalEntryFactory entryFactory) {
        this.entryList = new ArrayList<>();
        this.isEmpty = true;
        this.observers = new ArrayList<>();
        this.entryFactory = entryFactory;
        this.title = title;
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
     * Adds a new entry to the journal with the provided title and content.
     *
     * @param title   the title for the new journal entry
     * @param content the content for the new journal entry
     */
    public void addEntry(String title, String content){
        JournalEntry newEntry = entryFactory.createJournalEntry(content);
        entryList.add(newEntry);
        isEmpty = false;
        notifyObservers();
    }

    /**
     * Notifies all registered observers when an entry is added or removed.
     */
    private void notifyObservers() {
        for (JournalObserver observer : observers) {
            observer.update();
        }
    }

    /**
     * Removes the specified entry from the journal.
     *
     * @param entry the entry to be removed
     */
    public void removeEntry(JournalEntry entry){
        entryList.remove(entry);
        isEmpty = entryList.isEmpty();
        notifyObservers();

    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer the observer to be added
     */
    public void addObserver(JournalObserver observer){
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer the observer to be removed
     */
    public void removeObserver(JournalObserver observer){
        observers.remove(observer);
        
    }

}
