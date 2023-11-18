package org.group12.controller;

import org.group12.Listeners.JournalListener;
import org.group12.model.JournalEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class journalController {
    private List<JournalEntry> journalEntries;
    private List<JournalListener> listeners = new ArrayList<>();

    public journalController(){
        this.journalEntries = new ArrayList<>();
    }

    public List<JournalEntry> allJournalEntries (){
        return journalEntries;
    }

    private void addJournalEntry(JournalEntry entry){
        journalEntries.add(entry);

        for(JournalListener listener : listeners){
            listener.notifyJournalEntryAdded(entry);
        }
    }

    private void removeJournalEntry(JournalEntry entry){
        journalEntries.remove(entry);

        for(JournalListener listener : listeners){
            listener.notifyJournalEntryRemoved(entry);
        }
    }

    private void updateJournalEntry(JournalEntry entry, String newTitle){
        entry.setTitle(newTitle);

        for(JournalListener listener : listeners){
            listener.notifyJournalEntryUpdated(entry);
        }
    }

    private List<JournalEntry> getTodaysEntry(Date today){
        List<JournalEntry> todaysEntry = new ArrayList<>();
        for(JournalEntry entry : journalEntries){
            if(entry.getCreatedTimestamp() == today){
                todaysEntry.add(entry);
            }
        }
        return todaysEntry;
    }


}
