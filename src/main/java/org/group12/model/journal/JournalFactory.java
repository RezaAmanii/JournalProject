package org.group12.model.journal;

import org.group12.model.IDFactory;

public class JournalFactory {
    private JournalIDFactory idFactory;

    public JournalFactory() {
        this.idFactory = new JournalIDFactory();
    }
    public Journal createJournal(String title, JournalEntryFactory entryFactory) {
        String ID = idFactory.generateID();
        return new Journal(ID, title, entryFactory);
    }

}
