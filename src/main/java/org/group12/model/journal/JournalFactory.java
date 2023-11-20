package org.group12.model.journal;

import org.group12.model.IDFactory;

public class JournalFactory {
    // change nextID to some Tokenfunction?

    public Journal createJournal(String title, JournalEntryFactory entryFactory) {
        long ID = new IDFactory().generateUniqueID();
        return new Journal(ID, title, entryFactory);
    }

}
