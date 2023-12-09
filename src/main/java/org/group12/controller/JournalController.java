package org.group12.controller;

import org.group12.model.Container;
import org.group12.model.INameable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.beans.value.ObservableObjectValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.group12.model.Container;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalEntry;
import org.group12.view.JournalView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.group12.model.Items;

import static java.time.format.DateTimeFormatter.ISO_DATE;

public class JournalController implements IController {

    private Journal journalModel;
    private Container container;
    private JournalView journalView;
    private ObjectProperty<JournalEntry> journalEntry;

//    private HashMap<String, INameable> itemMap;
    private Items itemMap;

    @FXML
    private TextArea content;

    @FXML
    private DatePicker entryDate;

    @FXML
    private Label entryDateLabel;

    @FXML
    private Label prevDayBtn;

    @FXML
    void onAddEntry(MouseEvent event) {
        journalEntry.get().updateContent(content.getText());
        journalModel.addEntryForDate(entryDate.getValue(), journalEntry.get());
    }

    @FXML
    void onDeleteClk(MouseEvent event) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Confirm");
        alert.setContentText("Confirm deleting journal?");

        var option = alert.showAndWait().orElse(ButtonType.CANCEL);
        if(ButtonType.OK.equals(option)) {
            journalModel.removeEntry(entryDate.getValue());
            onPrevDayClk(event);
        }
    }

    @FXML
    void onNextDayClk(MouseEvent event) {
        var currentVal = entryDate.valueProperty().get();
        entryDate.valueProperty().set(currentVal.plusDays(1));
    }

    @FXML
    void onPrevDayClk(MouseEvent event) {
        var currentVal = entryDate.valueProperty().get();
        entryDate.valueProperty().set(currentVal.minusDays(1));
    }



    public JournalController(){
        this.itemMap = Items.getInstance();
        this.journalModel = container.getJournal();
        //journalModel.addObserver(journalView);
    }
/*
    @FXML
    public void initialize() {
        createBindings();

        entryDate.valueProperty().setValue(LocalDateTime.now());
    }

    private void createBindings() {
        journalEntry = new SimpleObjectProperty<>(journalModel.getEntryForDate(LocalDateTime.now()));
        journalEntry.bind(entryDate.valueProperty().map(journalModel::getEntryForDate));
        journalEntry.addListener((observable, oldValue, newValue) -> content.setText(newValue.getContent()));

        entryDateLabel.textProperty()
                .bind(entryDate.valueProperty()
                        .map(date -> date.format(ISO_DATE)));
    }

 */


    /**
     * Adds a new journal entry to the journal model if the input is valid.
     * Validates the provided journal entry to ensure that both title and content are not empty
     * before adding it to the journal.
     *
     * @param journalEntry the journal entry to be added
     *                     (contains title and content for the new journal entry)
     */
    public void addJournalEntry(JournalEntry journalEntry){
        if(validateAddInput(journalEntry)){
            journalModel.addEntry(journalEntry.getTitle(), journalEntry.getContent());
        } else{
            //journalView.displayErrorMessage("Both title and content should not be empty.");
        }
    }


    /**
     * Removes the provided journal entry from the journal model.
     * Checks if the provided journal entry is not null before attempting removal.
     *
     * @param journalEntry the journal entry to be removed
     */
    public void removeJournalEntry(JournalEntry journalEntry){
        if(journalEntry != null){
            journalModel.removeEntry(journalEntry);
        } else{
            //journalView.displayErrorMessage("Invalid entry!");
        }
    }


    /**
     * Updates the content of the provided journal entry with new content if the entry and new content are valid.
     *
     * @param journalEntry the journal entry to be updated
     * @param newContent   the new content to replace the existing content in the journal entry
     */
    public void updateJournalEntry(JournalEntry journalEntry, String newContent){
        if(journalEntry != null && !newContent.isEmpty()){
//            entryModel.updateContent(newContent);
            this.journalEntry.get().updateContent(newContent);
        } else {
            //journalView.displayErrorMessage("Invalid input.");
        }
    }


    /**
     * Retrieves a list of journal entries created on the provided date.
     *
     * @param date the date for which journal entries are to be retrieved
     * @return a list of journal entries created on the specified date,
     *         an empty list if no entries are found for the given date
     */
    public List<JournalEntry> getTodaysEntry(LocalDateTime date){
        //return List<JournalEntry> todaysEntries = entryModel.getTodaysEntry(date);
        return null;
    }


    /**
     * Retrieves all journal entries stored in the system.
     *
     * @return a list containing all journal entries stored in the system,
     *         or an empty list if there are no entries
     */
    public List<JournalEntry> getAllEntries(){
        //return List<JournalEntry> allEntries = entryModel.getAllEntries();
        return null;
    }


    /**
     * Validates the provided journal entry to ensure that it contains both non-empty content and title.
     *
     * @param journalEntry the journal entry to be validated
     * @return true if the journal entry is not null and both content and title are not empty; false otherwise
     */
    private boolean validateAddInput(JournalEntry journalEntry) {
        return journalEntry != null && !journalEntry.getContent().isEmpty() && !journalEntry.getTitle().isEmpty();
    }





    }
