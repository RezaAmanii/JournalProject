package org.group12.model.journal;

public interface IJournalEntryFactory {
    JournalEntry createJournalEntry(String content);
    JournalEntry createJournalEntry(String title, String content);
    JournalEntry createJournalEntryForDate(LocalDateTime date);
}