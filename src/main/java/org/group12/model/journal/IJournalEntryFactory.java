package org.group12.model.journal;

import java.time.LocalDateTime;

public interface IJournalEntryFactory {
    JournalEntry createJournalEntry(String content);
    JournalEntry createJournalEntry(String title, String content);

    JournalEntry createJournalEntryForDate(LocalDateTime date);
    //JournalEntry createJournalEntryForDate(String title, String content);
}