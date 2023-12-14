package org.group12.model.journal;

import java.time.LocalDate;

public interface IJournalEntryFactory {
    IEntry createJournalEntry(LocalDate date);

}