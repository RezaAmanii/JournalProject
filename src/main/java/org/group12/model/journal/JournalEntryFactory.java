package org.group12.model.journal;

import org.group12.model.IDFactory.IDFactory;
import org.group12.model.IDFactory.IIDFactory;
import org.group12.model.IDFactory.JournalEntryIDFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 * Singleton factory class for creating instances of JournalEntry.
 */

public class JournalEntryFactory implements IJournalEntryFactory {
    private IIDFactory idFactory;
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
     * @return a new JournalEntry object
     */
    public JournalEntry createJournalEntry() {
        String ID = idFactory.generateID();
        String content = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String title = dateFormat.format(new Date());
        LocalDate createdTimestamp = LocalDate.now();
        return new JournalEntry(ID, title, content, LocalDate.now(), createdTimestamp);
    }

    @Override
    public JournalEntry createJournalEntry(LocalDate date) {
        String ID = idFactory.generateID();
        String content = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String title = dateFormat.format(new Date());
        LocalDate createdTimestamp = date;
        return new JournalEntry(ID, title, content, LocalDate.now(), createdTimestamp);
    }




}
