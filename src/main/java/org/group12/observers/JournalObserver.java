package org.group12.observers;

import org.group12.model.Journal;

public class JournalObserver implements Observer {
    private Journal journal;

    public JournalObserver(Journal journal) {
        this.journal = journal;
    }

    @Override
    public void update() {
        throw new IllegalArgumentException("Not implemented!");
    }
}
