package org.group12.controller;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalEntry;
import org.group12.controllerView.JournalWindowManager;
import org.group12.view.JournalView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.group12.util.TextUtils.getWordCount;

/**
 * Controller class for managing journal entries.
 */
public class JournalController implements IController, IObservable {

    private Container container;
    private JournalView journalView;
    private ObjectProperty<JournalEntry> journalEntry; // not allowed here ?
    private static JournalController instance;
    private static Journal journalModel;
    private JournalWindowManager journalWindowManager;

    private static Items itemMap;
    private final List<IPlanITObserver> observers = new ArrayList<>();

    /**
     * Constructor for JournalController. Initializes the container, journal model, and item map.
     */
    public JournalController() {
        this.container = Container.getInstance();
        this.journalModel = container.getJournal();
        this.itemMap = Items.getInstance();
        //journalModel.addObserver(journalView);

    }

    /**
     * Returns the single instance of JournalController. If the instance is null, it creates a new instance.
     *
     * @return the single instance of JournalController
     */
    public static JournalController getInstance() {
        if (instance == null) {
            instance = new JournalController();
        }
        return instance;
    }

    /**
     * Returns the title of the provided journal entry.
     *
     * @param entry the journal entry to get the title from. Must not be null.
     * @return the title of the journal entry
     * @throws IllegalArgumentException if entry is null.
     */
    public static String getEntryTitle(JournalEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry cannot be null.");
        }
        return entry.getTitle();
    }
    /**
     * Sets the title of the provided journal entry.
     *
     * @param entry the journal entry to set the title for. Must not be null.
     * @param title the new title for the journal entry. Must not be null.
     * @throws IllegalArgumentException if entry is null.
     */
    public static void setEntryTitle(JournalEntry entry, String title) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry cannot be null.");
        }
        if (title == null) {
            title = "No title set";
        }
        entry.setTitle(title);
    }
    /**
     * Returns the modified timestamp of the provided journal entry in the format "yyyy-MM-dd HH:mm".
     *
     * @param entry The journal entry whose modified timestamp is to be returned. Must not be null.
     * @return The modified timestamp of the journal entry.
     * @throws IllegalArgumentException if entry is null.
     */
    public static String getEntryDateModified(JournalEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry cannot be null.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return entry.getModifiedTimestamp().format(formatter);
    }

    /**
     * Returns the number of words in the content of the provided journal entry.
     *
     * @param entry The journal entry whose content's word count is to be returned. Must not be null.
     * @return The number of words in the content of the journal entry.
     * @throws IllegalArgumentException if entry is null.
     */
    public static String getNrOfWords(JournalEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry cannot be null.");
        }
        String entryContent = entry.getContent();
        return String.valueOf(getWordCount(entryContent));
    }

    /**
     * Returns the content of the provided journal entry.
     *
     * @param entry The journal entry whose content is to be returned. Must not be null.
     * @return The content of the journal entry.
     * @throws IllegalArgumentException if entry is null.
     */
    public static String getEntryContent(JournalEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry cannot be null.");
        }
        String entryContent = entry.getContent();
        return entryContent;
    }


    /**
     * Updates the content of the provided journal entry with new content if the entry and new content are valid.
     *
     * @param journalEntry the journal entry to be updated
     * @param newContent   the new content to replace the existing content in the journal entry
     */
    public void updateJournalEntry(JournalEntry journalEntry, String newContent) {
        if(journalEntry != null){
            journalEntry.updateContent(newContent);
        } else {
            System.out.println("Saknas content!");
            //journalView.displayErrorMessage("Invalid input.");
        }
    }


    /**
     * Retrieves a journal entry created on the provided date. If no entry exists for the given date, a new entry is created.
     *
     * @param date the date for which the journal entry is to be retrieved. Must not be null.
     * @return the journal entry created on the specified date, or a new entry if no entry was found for the given date.
     * @throws IllegalArgumentException if date is null.
     */
    public JournalEntry getEntryByDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        if (journalModel.getEntryByDate(date) == null) {
            System.out.println("getentrybydate is still null so we make another one for this day");
            journalModel.addEntry(date);
        }
        return journalModel.getEntryByDate(date);
    }

    public void addObserver(IPlanITObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(IPlanITObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IPlanITObserver observer : observers) {
            observer.update();
        }
    }

}