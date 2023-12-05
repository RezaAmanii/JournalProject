package org.group12.model.journal;

import org.group12.model.IDFactory.IDFactory;
import org.group12.model.IDFactory.IIDFactory;
import org.group12.model.IDFactory.JournalIDFactory;
import org.group12.model.ItemsSet;

/**
 * Singleton factory class for creating instances of Journal.
 */
public class JournalFactory {
    private IIDFactory idFactory;
    private static JournalFactory instance;


    /**
     * Private constructor to prevent creating multiple instances of the class.
     * Initializes the JournalIDFactory instance.
     */
    private JournalFactory() {
        this.idFactory = IDFactory.getInstance(JournalIDFactory.class);
    }
    /**
     * Returns the single instance of the class. If the instance is null, it creates a new instance.
     *
     * @return the single instance of JournalFactory
     */
    public static synchronized  JournalFactory getInstance(){
        if(instance == null){
            instance = new JournalFactory();
        }
        return instance;
    }
    /**
     * Creates a new Journal with a unique ID and the given title.
     *
     * @param title the title of the Journal
     * @param entryFactory the factory to create JournalEntry objects
     * @return the created Journal
     */
    public Journal createJournal(String title, JournalEntryFactory entryFactory, ItemsSet items) {
        String ID = idFactory.generateID();
        return new Journal(ID, title, entryFactory, items);
    }

}
