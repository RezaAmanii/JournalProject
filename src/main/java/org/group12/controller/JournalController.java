package org.group12.controller;

import org.group12.model.INameable;
import org.group12.model.journal.Journal;
import org.group12.view.JournalView;

import java.util.HashMap;


public class JournalController implements IController {

    private Journal journalModel;
    private JournalView journalView;

    private HashMap<String, INameable> itemMap;


    public JournalController(Journal journalModel, HashMap<String, INameable> itemMap){
        this.journalModel = journalModel;
        this.itemMap= itemMap;
        //journalModel.addObserver(journalView);
    }


    public void handleButtonClick(){}



}
