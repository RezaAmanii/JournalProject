package org.group12.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.group12.model.journal.Journal;
import org.group12.view.JournalView;

import java.net.URL;
import java.util.ResourceBundle;


public class JournalController implements IController, Initializable{

    @FXML
    private Label activeListNameLBL;

    @FXML
    private Label activeListNameLBL1;

    @FXML
    private BorderPane mainWindowBorder;

    @FXML
    private Label nameLBL11;

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

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }


}
