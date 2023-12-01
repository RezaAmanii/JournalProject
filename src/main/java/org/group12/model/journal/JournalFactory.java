package org.group12.model.journal;

import org.group12.model.IDFactory;
/**
 * Represents a factory for creating Journal objects.
 * This class uses a JournalIDFactory to generate unique IDs for each Journal.
 */
public class JournalFactory {
    private JournalIDFactory idFactory;

    /**
     * Constructs a new JournalFactory.
     * Initializes the JournalIDFactory used to generate IDs.
     */
    public JournalFactory() {
        this.idFactory = JournalIDFactory.getInstance();
    }

    /**
     * Creates a new Journal with a unique ID and the given title.
     *
     * @param title the title of the Journal
     * @param entryFactory the factory to create JournalEntry objects
     * @return the created Journal
     */
    public Journal createJournal(String title, JournalEntryFactory entryFactory) {
        String ID = idFactory.generateID();
        return new Journal(ID, title, entryFactory);
    }

}
