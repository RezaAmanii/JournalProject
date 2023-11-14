package org.group12.model;

import org.group12.observers.JournalObserver;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private List<JournalEntry> entryList;
    private boolean isEmpty;
    private List<JournalObserver> observers;
    private JournalEntryFactory entryFactory;
    public Journal(Long ID, String title, JournalEntryFactory entryFactory) {
        this.entryList = new ArrayList<>();
        this.isEmpty = true;
        this.observers = new ArrayList<>();
        this.entryFactory = entryFactory;
    }
}
