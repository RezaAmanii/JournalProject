package org.group12.Listeners;

import org.group12.model.journal.JournalEntry;

public interface JournalListener extends PlanITListener {
    void notifyJournalEntryAdded(JournalEntry entry);
    void notifyJournalEntryUpdated(JournalEntry entry);
    void notifyJournalEntryRemoved(JournalEntry entry);

}
