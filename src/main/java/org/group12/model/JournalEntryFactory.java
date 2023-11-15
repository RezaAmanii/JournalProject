package org.group12.model;

public class JournalEntryFactory {
    // change nextID to some Tokenfunction?
    private static long nextID = 1;

    public JournalEntry createJournalEntry(String title, String content) {
        return new JournalEntry(nextID++, title, content);
    }
}
