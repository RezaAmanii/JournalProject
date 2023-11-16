package org.group12.controller;

import org.group12.model.TaskList;

import java.util.ArrayList;
import java.util.List;

public class journalController {
    private List<TaskList> allJournalEntriesList;


    public List<TaskList> getAllJournalEntriesList(){
        return allJournalEntriesList;
    }

    // Constructor makes a new list of type TodoList
    public journalController(){
        allJournalEntriesList = new ArrayList<>();
    }

    public void createJournalEntry(){

    }

    public void removeJournalEntry(){

    }

    public void updateJournalEntry(){

    }

    public List<TaskList> getTodaysEntry(){
        return null;
    }


}
