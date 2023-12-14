package org.group12.model.journal;

import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.INameable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JournalEntry implements INameable, IObservable, Serializable, IEntry {
    private String ID;
    private String title;
    private final LocalDate entryDate;
    private final LocalDate createdTimestamp;
    private LocalDateTime modifiedTimestamp;
    private String content;
    private final List<IPlanITObserver> observers;

    /**
     * Constructor for creating a new JournalEntry object.
     *
     * @param ID               unique identifier for the journal entry. Must not be null or empty.
     * @param title            title of the journal entry. Must not be null or empty.
     * @param content          content of the journal entry. Must not be null.
     * @param entryDate        date of the journal entry. Must not be null.
     * @param createdTimestamp timestamp when the journal entry was created. Must not be null.
     */
    public JournalEntry(String ID, String title, String content, LocalDate entryDate, LocalDate createdTimestamp) {
        this.ID = ID;
        this.title = title;
        this.entryDate = entryDate;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = createdTimestamp.atStartOfDay();
        this.content = content;
        this.observers = new ArrayList<>();
    }


    /**
     * Updates the content of the journal entry, updates the modified timestamp, and notifies all observers.
     *
     * @param newContent the new content for the journal entry.
     */
    public void updateContent(String newContent) {
        this.content = newContent;
        this.modifiedTimestamp = LocalDateTime.now();
        notifyObservers();
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Updates the title of the journal entry, updates the modified timestamp to the current date and time, and notifies all observers.
     *
     * @param newTitle the new title for the journal entry. If null, it will be treated as an empty string.
     */
    public void setTitle(String newTitle) {
        if (newTitle == null) {
            newTitle = "No title set";
        }
        this.title = newTitle;
        this.modifiedTimestamp = LocalDateTime.now();
        notifyObservers();
    }

    /**
     * Gets the unique identifier for the journal entry.
     *
     * @return the unique identifier for the journal entry
     */
    public String getID() {
        return ID;
    }

    /**
     * Gets the title of the journal entry.
     *
     * @return the title of the journal entry
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the timestamp when the journal entry was created.
     *
     * @return the timestamp when the journal entry was created
     */
    public LocalDate getDateCreated() {
        return createdTimestamp;
    }

    /**
     * Gets the last modified timestamp for the journal entry.
     *
     * @return the last modified timestamp for the journal entry
     */
    public LocalDateTime getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    /**
     * Gets the content of the journal entry.
     *
     * @return the content of the journal entry
     */
    public String getContent() {
        return content;
    }

    /**
     * Adds an observer to the journal entry.
     *
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(IPlanITObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the journal entry.
     *
     * @param observer the observer to be removed
     */
    @Override
    public void removeObserver(IPlanITObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of the journal entry.
     */
    @Override
    public void notifyObservers() {
        observers.forEach(IPlanITObserver::update);
    }
}
