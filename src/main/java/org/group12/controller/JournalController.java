package org.group12.controller;

import org.group12.model.journal.Journal;
import org.group12.view.JournalView;


public class JournalController {

    private Journal journalModel;
    private JournalView journalView;


    public JournalController(){
        //this.journalModel = new Journal();
        this.journalView = new JournalView();
        //journalModel.addObserver(journalView);
    }


    public void handleButtonClick(){}



}
