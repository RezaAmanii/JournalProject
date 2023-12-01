package org.group12.model.journal;

import org.group12.model.IDFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * Singleton factory class for creating instances of JournalEntry.
 */

public class JournalEntryFactory {
    private JournalEntryIDFactory idFactory;
    private static JournalEntryFactory instance;

    /**
     * Private constructor to prevent creating multiple instances of the class.
     * Initializes the JournalEntryIDFactory instance.
     */
    private JournalEntryFactory() {
        this.idFactory = IDFactory.getInstance(JournalEntryIDFactory.class);
    }
    /**
     * Returns the single instance of the class. If the instance is null, it creates a new instance.
     *
     * @return the single instance of JournalEntryFactory
     */
    public static synchronized JournalEntryFactory getInstance(){
        if(instance == null){
            instance = new JournalEntryFactory();
        }
        return instance;
    }

    /**
     * Creates a new JournalEntry with a generated ID, title set to the current date,
     * provided content, and timestamps set to the current date.
     *
     * @param content the content for the new journal entry
     * @return a new JournalEntry object
     */
    public JournalEntry createJournalEntry(String content) {
        String ID = idFactory.generateID();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String title = dateFormat.format(new Date());
        LocalDateTime createdTimestamp = LocalDateTime.now();
        return new JournalEntry(ID, title, content, createdTimestamp);
    }

    /**
     * Creates a new JournalEntry with a generated ID, provided title,
     * provided content, and timestamps set to the current date.
     *
     * @param title   the title for the new journal entry
     * @param content the content for the new journal entry
     * @return a new JournalEntry object
     */
    public JournalEntry createJournalEntry(String title, String content) {
        String ID = idFactory.generateID();
        LocalDateTime createdTimestamp = LocalDateTime.now();
        return new JournalEntry(ID, title, content, createdTimestamp);
    }
}
