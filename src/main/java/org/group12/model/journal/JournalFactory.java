package org.group12.model.journal;

public class JournalFactory {
    // change nextID to some Tokenfunction?
    private static long nextID = 1;
    public Journal createJournal(String title, JournalEntryFactory entryFactory) {
        return new Journal(nextID++, title, entryFactory);
    }

}
