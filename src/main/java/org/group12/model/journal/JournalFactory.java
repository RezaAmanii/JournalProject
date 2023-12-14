package org.group12.model.journal;

import org.group12.model.IDFactory.IDFactory;
import org.group12.model.IDFactory.IIDFactory;
import org.group12.model.IDFactory.JournalIDFactory;
import org.group12.model.ItemsSet;

import java.io.Serializable;

/**
 * Singleton factory class for creating instances of Journal.
 */
public class JournalFactory implements Serializable {
    private final IIDFactory idFactory;
    private static JournalFactory instance;


    /**
     * Private constructor to prevent creating multiple instances of the class.
     * Initializes the JournalIDFactory instance.
     *
     * @throws IllegalStateException if IDFactory.getInstance(JournalIDFactory.class) returns null.
     */
    private JournalFactory() {
        this.idFactory = IDFactory.getInstance(JournalIDFactory.class);
        if (this.idFactory == null) {
            throw new IllegalStateException("Failed to get an instance of JournalIDFactory.");
        }
    }

    /**
     * Returns the single instance of the class. If the instance is null, it creates a new instance.
     *
     * @return the single instance of JournalFactory
     */
    public static synchronized JournalFactory getInstance() {
        if (instance == null) {
            instance = new JournalFactory();
        }
        return instance;
    }

    /**
     * Creates a new Journal with a unique ID and the given title.
     *
     * @param title the title of the Journal. Must not be null or empty.
     * @param entryFactory the factory to create JournalEntry objects. Must not be null.
     * @param items the set of items associated with the Journal. Must not be null.
     * @return the created Journal with ID generated by idFactory, the provided title, and the provided items.
     * @throws IllegalArgumentException if title is null or empty, entryFactory is null, or items is null.
     * @throws IllegalStateException if idFactory.generateID() fails to generate an ID.
     */
    public Journal createJournal(String title, JournalEntryFactory entryFactory, ItemsSet items) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (entryFactory == null) {
            throw new IllegalArgumentException("JournalEntryFactory cannot be null.");
        }
        if (items == null) {
            throw new IllegalArgumentException("ItemsSet cannot be null.");
        }

        String ID = idFactory.generateID();
        if (ID == null || ID.isEmpty()) {
            throw new IllegalStateException("Failed to generate an ID for the journal.");
        }

        return new Journal(ID, title, entryFactory, items);
    }
}