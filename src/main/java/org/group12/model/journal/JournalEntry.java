package org.group12.model.journal;

import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.IDateCreated;
import org.group12.model.INameable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JournalEntry implements INameable, IDateCreated, IObservable {
    private String ID;
    private String title;
    private LocalDateTime createdTimestamp;
    private LocalDateTime modifiedTimestamp;
    private String content;
    private List<IPlanITObserver> observers;

    /**
     * Constructor for creating a new JournalEntry object.
     *
     * @param ID               unique identifier for the journal entry
     * @param title            title of the journal entry
     * @param content          content of the journal entry
     * @param createdTimestamp timestamp when the journal entry was created
     */
    public JournalEntry(String ID, String title, String content, LocalDateTime createdTimestamp) {
        this.ID = ID;
        this.title = title;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = createdTimestamp;
        this.content = content;
        this.observers = new ArrayList<>();
    }

    /**
     * Removes the content from the journal entry and notifies all observers.
     */
    public void removeContentFromEntry() {
        this.content = null;
        this.modifiedTimestamp = LocalDateTime.now();
        notifyObservers();
    }

    /**
     * Updates the content of the journal entry, updates the modified timestamp, and notifies all observers.
     *
     * @param newContent the new content for the journal entry
     */
    public void updateContent(String newContent) {
        this.content = newContent;
        this.modifiedTimestamp = LocalDateTime.now();
        notifyObservers();
    }

    /**
     * Updates the title of the journal entry, updates the modified timestamp, and notifies all observers.
     *
     * @param newTitle the new title for the journal entry
     */
    public void setTitle(String newTitle) {
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
    public LocalDateTime getDateCreated() {
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
