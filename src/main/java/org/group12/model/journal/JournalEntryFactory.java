package org.group12.model.journal;

import org.group12.model.IDFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * Factory class for creating instances of JournalEntry.
 */

public class JournalEntryFactory {
    private JournalEntryIDFactory idFactory;

    public JournalEntryFactory() {
        this.idFactory = JournalEntryIDFactory.getInstance();
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
