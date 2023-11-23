package org.group12.controller;

import org.group12.model.journal.Journal;
import org.group12.view.journalView;


public class journalController {

    private Journal journalModel;
    private journalView journalView;


    public journalController(){
        //this.journalModel = new Journal();
        this.journalView = new journalView();
        //journalModel.addObserver(journalView);
    }


    public void handleButtonClick(){}



}
