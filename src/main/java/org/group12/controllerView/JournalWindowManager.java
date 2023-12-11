package org.group12.controllerView;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.group12.Listeners.JournalClickListener;
import org.group12.Observers.IJournalObserver;
import org.group12.controller.JournalController;
import org.group12.model.Items;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalEntry;
import org.group12.view.JournalEntryCard;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class JournalWindowManager implements Initializable {



    private TextArea content;


    private DatePicker entryDate;


    private Label entryDateLabel;


    private Label prevDayBtn;
    @FXML
    private Label entryDateLabel;
    @FXML
    public BorderPane journalEntryPane;

    public DatePicker getEntryDate() {
        return entryDate;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (journalController.getEntryByDate(LocalDateTime.now()) == null)
            journalController.addJournalEntry();
        //populateJournalEntry(journalController.getEntryByDate(LocalDateTime.now()));
        journalController.addObserver(this);

    }
    public void loadView(TextArea content,DatePicker entryDate,Label entryDateLabel, Label prevDayBtn)
    {
        //this.content = content;
        this.entryDate = entryDate;
        this.entryDateLabel = entryDateLabel;
        this.prevDayBtn = prevDayBtn;
    }


    //public JournalEntry prepareJournal()
    //{
    //    return new JournalEntry("Posts",entryDate.getValue(),content.getText());
    //}

    //public void createBindings() {
    //    entryDateLabel.textProperty()
    //            .bind(entryDate.valueProperty()
    //                    .map(date -> date.format(ISO_DATE)));
    //}
    @FXML
    public void getPrevDayClick()
    {
        JournalEntry nextEntry = journalController.getEntryByDate(entryDate.getValue().minusDays(1).atStartOfDay());
        populateJournalEntry(nextEntry);


    }

    public void getNexDayClick(JournalEntry journalEntry)
    {
        JournalEntry nextEntry = journalController.getEntryByDate(entryDate.getValue().plusDays(1).atStartOfDay());
        populateJournalEntry(nextEntry);
    }

}