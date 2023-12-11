package org.group12.model.journal;

import java.time.LocalDateTime;

public interface IJournalEntryFactory {
    JournalEntry createJournalEntry();
    JournalEntry createJournalEntry(LocalDateTime date);

}