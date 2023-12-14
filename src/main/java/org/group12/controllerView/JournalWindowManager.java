package org.group12.controllerView;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.group12.Listeners.JournalClickListener;
import org.group12.Observers.IJournalObserver;
import org.group12.controller.JournalController;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalEntry;
import org.group12.view.JournalEntryCard;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.time.format.DateTimeFormatter.ISO_DATE;

public class JournalWindowManager implements Initializable, IJournalObserver, JournalClickListener {

    @FXML
    private DatePicker entryDate;

    @FXML
    private Label entryDateLabel;
    @FXML
    public BorderPane journalEntryPane;

    private static final JournalController journalController = JournalController.getInstance();

    public static JournalEntryCard journalEntryCard = null;
    public static Journal journal = null;
    public static JournalEntry entry = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.entryDate = new DatePicker();
        this.entryDate.setValue(LocalDate.now());

        // collects todays entry, returns empty new one if one did not exist
        entry = journalController.getEntryByDate(LocalDate.now());
        createBindings();
        populateJournalEntry(entry);
        journalController.addObserver(this);

    }

    @FXML
    public void getPrevDayClick()
    {
        JournalEntry nextEntry = journalController.getEntryByDate(entryDate.getValue().minusDays(1));
        populateJournalEntry(nextEntry);
        this.entryDate.setValue(entryDate.getValue().minusDays(1));
        entry = nextEntry;
        update();


    }
    @FXML
    public void getNexDayClick()
    {
        JournalEntry nextEntry = journalController.getEntryByDate(entryDate.getValue().plusDays(1));
        populateJournalEntry(nextEntry);
        this.entryDate.setValue(entryDate.getValue().plusDays(1));
        entry = nextEntry;
        update();
    }

    @Override
    public void update() {
    }

    public void populateJournalEntry(JournalEntry journalEntry) {
        if(journalEntry != null) {
            // Create a new JournalEntryCard
            JournalEntryCard journalEntryCard = createNewEntryObject(journalEntry);
            // Clear the journalEntryPane and add the new card
            journalEntryPane.getChildren().clear();
            journalEntryPane.setCenter(journalEntryCard);
;
        } else {
            // If journalEntry is null, print an error message
            System.out.println("JournalEntry is null in populate");
        }

    }


    public JournalEntryCard createNewEntryObject(JournalEntry entry) {
        JournalEntryCard newEntryCard = new JournalEntryCard(entry.getID(), SaveLoad.getInstance().getItemsInstance());
        //newEntryCard.setClickListener(this);
        return newEntryCard;
    }



    public void createBindings() {
        entryDateLabel.textProperty()
                .bind(entryDate.valueProperty()
                        .map(date -> date.format(ISO_DATE)));
    }

}