package org.group12.model.journal;

import org.group12.observers.JournalObserver;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private List<JournalEntry> entryList;
    private boolean isEmpty;
    private List<JournalObserver> observers;
    private JournalEntryFactory entryFactory;
    private String title;
    public Journal(Long ID, String title, JournalEntryFactory entryFactory) {
        this.entryList = new ArrayList<>();
        this.isEmpty = true;
        this.observers = new ArrayList<>();
        this.entryFactory = entryFactory;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void addEntry(String title, String content){
        JournalEntry newEntry = entryFactory.createJournalEntry(title, content);
        entryList.add(newEntry);
        isEmpty = false;
        notifyObservers();
    }

    private void notifyObservers() {
        for (JournalObserver observer : observers) {
            observer.update();
        }
    }

    public void removeEntry(JournalEntry entry){
        entryList.remove(entry);
        isEmpty = entryList.isEmpty();
        notifyObservers();

    }
    public void addObserver(JournalObserver observer){
        observers.add(observer);
    }
    public void removeObserver(JournalObserver observer){
        observers.remove(observer);
        
    }
}
