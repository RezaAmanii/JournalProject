package org.group12.controllerView;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.io.IOException;

public class JournalWindowManager implements Initializable, IJournalObserver, JournalClickListener {

    @FXML
    private DatePicker entryDate;

    //private Label entryDateLabel;
    @FXML
    private Label prevDayBtn;
    @FXML
    private Label entryDateLabel;
    @FXML
    public BorderPane journalEntryPane;

    public DatePicker getEntryDate() {
        return entryDate;
    }

//    private TextArea content;
    private static final JournalController journalController = JournalController.getInstance();

    public static JournalEntryCard journalEntryCard = null;
    public static Journal journal = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entryDate = new DatePicker();
        entryDate.setValue(LocalDate.now());
        if (journalController.getEntryByDate(LocalDateTime.now()) == null) {
            System.out.println("null");
            journalController.addJournalEntry();
        }

        populateJournalEntry(journalController.getEntryByDate(LocalDateTime.now()));
        journalController.addObserver(this);

    }
    public void loadView(TextArea content,DatePicker entryDate,Label entryDateLabel, Label prevDayBtn)
    {
        //this.content = content;
        this.entryDate = entryDate;
        this.entryDateLabel = entryDateLabel;
        this.prevDayBtn = prevDayBtn;
    }



    @FXML
    public void getPrevDayClick()
    {
        JournalEntry nextEntry = journalController.getEntryByDate(entryDate.getValue().minusDays(1).atStartOfDay());
        populateJournalEntry(nextEntry);


    }
    @FXML
    public void getNexDayClick()
    {
        JournalEntry nextEntry = journalController.getEntryByDate(entryDate.getValue().plusDays(1).atStartOfDay());
        populateJournalEntry(nextEntry);
    }

    @Override
    public void update() {

    }
    //public void populateJournalEntry(JournalEntry entry){
        //journalEntryPane.getChildren().clear();
        //JournalEntryCard entrycard = createNewEntryObject(entry);
        //journalEntryPane.setCenter(entrycard);

    //}
    public void populateJournalEntry(JournalEntry journalEntry) {
        if(journalEntry != null) {
            // Create a new JournalEntryCard
            JournalEntryCard journalEntryCard = new JournalEntryCard(journalEntry.getID(), Items.getInstance());



            // Clear the journalEntryPane and add the new card
            journalEntryPane.getChildren().clear();
            journalEntryPane.setCenter(journalEntryCard);
            //journalEntryPane.setCenter(rootNode);
        } else {
            journalEntryPane.setCenter(null);
        }
    }


    public JournalEntryCard createNewEntryObject(JournalEntry entry) {
        JournalEntryCard newEntryCard = new JournalEntryCard(entry.getID(), Items.getInstance());
        //newEntryCard.setClickListener(this);
        return newEntryCard;
    }



//    public boolean deleteJournal()
//    {
//        var alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirm Delete");
//        alert.setHeaderText("Confirm");
//        alert.setContentText("Confirm deleting journal?");
//
//        var option = alert.showAndWait().orElse(ButtonType.CANCEL);
//        if(ButtonType.OK.equals(option)) {
//            return true;
//        }else
//            return false;
//    }

//    @FXML
//    void onAddEntry(MouseEvent event) {journalController.addEntryForDate(entryDateTime, journalWindowManager.prepareJournal());
//    }
//
//    @FXML
//    void onDeleteClk(MouseEvent event) {
//        String entryID = journalModel.getEntryByDate(entryDateTime).getID();
//        if(journalWindowManager.deleteJournal()) {
//            journalModel.removeEntry(entryID);
//            onPrevDayClk(event);
//        }
//    }
//
//    @FXML
//    void onNextDayClk(MouseEvent event) {
//        journalWindowManager.getNexDayClick(journalModel.getEntryByDate(journalWindowManager.getEntryDate().getValue().plusDays(1).atStartOfDay()));
//    }
//
//    @FXML
//    void onPrevDayClk(MouseEvent event) {
//        journalWindowManager.getPevDayClick(journalModel.getEntryByDate(journalWindowManager.getEntryDate().getValue().minusDays(1).atStartOfDay()));
//    }
    //public JournalEntry prepareJournal()
    //{
    //    return new JournalEntry("Posts",entryDate.getValue(),content.getText());
    //}

    //public void createBindings() {
    //    entryDateLabel.textProperty()
    //            .bind(entryDate.valueProperty()
    //                    .map(date -> date.format(ISO_DATE)));
    //}

}