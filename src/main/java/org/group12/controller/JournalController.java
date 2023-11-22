package org.group12.controller;

import org.group12.model.journal.Journal;
import org.group12.view.journalView;


public class JournalController {

    private Journal journalModel;
    private journalView journalView;


    public JournalController(Journal journalModel, journalView journalView){
        this.journalModel = journalModel;
        this.journalView = journalView;
        //journalModel.addObserver(journalView);
    }


    public void handleButtonClick(){

    }



}
