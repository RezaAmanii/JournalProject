package org.group12.model.journal;

import org.group12.model.IDFactory.IDFactory;
import org.group12.model.IDFactory.IIDFactory;
import org.group12.model.IDFactory.JournalEntryIDFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Singleton factory class for creating instances of JournalEntry.
 */
public class JournalEntryFactory implements IJournalEntryFactory, Serializable {
    private final IIDFactory idFactory;
    private static JournalEntryFactory instance;

    /**
     * Private constructor to prevent creating multiple instances of the class.
     * Initializes the JournalEntryIDFactory instance.
     *
     * @throws IllegalStateException if IDFactory.getInstance(JournalEntryIDFactory.class) fails to get an instance of JournalEntryIDFactory.
     */
    private JournalEntryFactory() {
        this.idFactory = IDFactory.getInstance(JournalEntryIDFactory.class);
        if (this.idFactory == null) {
            throw new IllegalStateException("Failed to get an instance of JournalEntryIDFactory.");
        }
    }

    /**
     * Returns the single instance of the class. If the instance is null, it creates a new instance.
     *
     * @return the single instance of JournalEntryFactory
     */
    public static synchronized JournalEntryFactory getInstance() {
        if (instance == null) {
            instance = new JournalEntryFactory();
        }
        return instance;
    }

    /**
     * Creates a new JournalEntry with a generated ID, title set to the current date,
     * provided content, and timestamps set to the current date.
     *
     * @param date The date when the JournalEntry is created. Must not be null.
     * @return a new JournalEntry object with ID generated by idFactory, title set to the current date, empty content, and createdTimestamp set to the provided date.
     * @throws IllegalArgumentException if date is null.
     * @throws IllegalStateException    if idFactory.generateID() fails to generate an ID.
     */
    @Override
    public IEntry createJournalEntry(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        String ID = idFactory.generateID();
        if (ID == null || ID.isEmpty()) {
            throw new IllegalStateException("Failed to generate an ID for the journal entry.");
        }

        String content = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String title = date.format(formatter);

        return new JournalEntry(ID, title, content, date);
    }
}