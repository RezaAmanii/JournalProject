package org.group12.model.journal;

import java.time.LocalDate;

public interface IJournalEntryFactory {
    JournalEntry createJournalEntry();
    JournalEntry createJournalEntry(LocalDate date);

}