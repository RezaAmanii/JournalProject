package org.group12.controller;

import org.group12.model.journal.Journal;
import org.group12.view.JournalView;


public class JournalController implements IController {

    private Journal journalModel;
    private JournalView journalView;

    public JournalController(){

    }


    public JournalController(Journal journalMorndel, JournalView joualView){
        this.journalModel = journalModel;
        this.journalView = journalView;
        //journalModel.addObserver(journalView);
    }


    public void handleButtonClick(){}



}
