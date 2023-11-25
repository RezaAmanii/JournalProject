package org.group12.controller;

import org.group12.model.INameable;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalEntry;
import org.group12.view.JournalView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class JournalController implements IController {

    private Journal journalModel;
    private JournalView journalView;
    private JournalEntry entryModel;
    private HashMap<String, INameable> itemMap;


    public JournalController(Journal journalModel, JournalView journalView, HashMap<String, INameable> itemMap){
        this.journalModel = journalModel;
        this.journalView = journalView;
        this.itemMap= itemMap;
        //journalModel.addObserver(journalView);
    }


    public void handleButtonClick(){}

    /**
     * Adds a new journal entry to the journal model if the input is valid.
     * Validates the provided journal entry to ensure that both title and content are not empty
     * before adding it to the journal.
     *
     * @param journalEntry the journal entry to be added
     *                     (contains title and content for the new journal entry)
     */
    public void addJournalEntry(JournalEntry journalEntry){
        if(validateAddInput(journalEntry)){
            journalModel.addEntry(journalEntry.getTitle(), journalEntry.getContent());
        } else{
            //journalView.displayErrorMessage("Both title and content should not be empty.");
        }
    }


    /**
     * Removes the provided journal entry from the journal model.
     * Checks if the provided journal entry is not null before attempting removal.
     *
     * @param journalEntry the journal entry to be removed
     */
    public void removeJournalEntry(JournalEntry journalEntry){
        if(journalEntry != null){
            journalModel.removeEntry(journalEntry);
        } else{
            //journalView.displayErrorMessage("Invalid entry!");
        }
    }


    /**
     * Updates the content of the provided journal entry with new content if the entry and new content are valid.
     *
     * @param journalEntry the journal entry to be updated
     * @param newContent   the new content to replace the existing content in the journal entry
     */
    public void updateJournalEntry(JournalEntry journalEntry, String newContent){
        if(journalEntry != null && !newContent.isEmpty()){
            entryModel.updateContent(newContent);
        } else {
            //journalView.displayErrorMessage("Invalid input.");
        }
    }


    /**
     * Retrieves a list of journal entries created on the provided date.
     *
     * @param date the date for which journal entries are to be retrieved
     * @return a list of journal entries created on the specified date,
     *         an empty list if no entries are found for the given date
     */
    public List<JournalEntry> getTodaysEntry(LocalDateTime date){
        //return List<JournalEntry> todaysEntries = entryModel.getTodaysEntry(date);
        return null;
    }


    /**
     * Retrieves all journal entries stored in the system.
     *
     * @return a list containing all journal entries stored in the system,
     *         or an empty list if there are no entries
     */
    public List<JournalEntry> getAllEntries(){
        //return List<JournalEntry> allEntries = entryModel.getAllEntries();
        return null;
    }


    /**
     * Validates the provided journal entry to ensure that it contains both non-empty content and title.
     *
     * @param journalEntry the journal entry to be validated
     * @return true if the journal entry is not null and both content and title are not empty; false otherwise
     */
    private boolean validateAddInput(JournalEntry journalEntry) {
        return journalEntry != null && !journalEntry.getContent().isEmpty() && !journalEntry.getTitle().isEmpty();
    }





    }
