package org.group12.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalEntry;
import org.group12.controllerView.JournalWindowManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE;

public class JournalController implements IController {

    private Journal journalModel = Container.getInstance().getJournal();
    private JournalWindowManager journalWindowManager;

    private Items itemMap;

    @FXML
    private TextArea content;

    @FXML
    private DatePicker entryDate;

    @FXML
    private Label entryDateLabel;

    @FXML
    private Label prevDayBtn;
    LocalDateTime entryDateTime;


    @FXML
    public void initialize() {
        journalWindowManager = new JournalWindowManager();
        journalWindowManager.loadView(content, entryDate, entryDateLabel,  prevDayBtn);
        journalWindowManager.createBindings();
        entryDate.valueProperty().setValue(LocalDate.now());
        entryDateTime = entryDate.getValue().atStartOfDay();
        JournalEntry journalEntry1 = journalModel.getEntryByDate(entryDateTime);
        if(journalEntry1 != null)
            content.setText(journalEntry1.getContent());

    }


    @FXML
    void onAddEntry(MouseEvent event) {
        journalModel.addEntryForDate(entryDateTime, journalWindowManager.prepareJournal());
    }

    @FXML
    void onDeleteClk(MouseEvent event) {
        String entryID = journalModel.getEntryByDate(entryDateTime).getID();
        if(journalWindowManager.deleteJournal()) {
            journalModel.removeEntry(entryID);
            onPrevDayClk(event);
        }
    }

    @FXML
    void onNextDayClk(MouseEvent event) {
        journalWindowManager.getNexDayClick(journalModel.getEntryByDate(journalWindowManager.getEntryDate().getValue().plusDays(1).atStartOfDay()));
    }

    @FXML
    void onPrevDayClk(MouseEvent event) {
        journalWindowManager.getPevDayClick(journalModel.getEntryByDate(journalWindowManager.getEntryDate().getValue().minusDays(1).atStartOfDay()));
    }



    public JournalController(Journal journalModel, JournalWindowManager journalWindowManager, Items itemMap){
        this();
        this.journalModel = journalModel;
        this.journalWindowManager = journalWindowManager;
        this.itemMap= itemMap;
        //journalModel.addObserver(journalView);

    }

    public JournalController() {
    }



    /**
     * Adds a new journal entry to the journal model if the input is valid.
     * Validates the provided journal entry to ensure that both title and content are not empty
     * before adding it to the journal.
     *
     * @param journalEntry the journal entry to be added
     *                     (contains title and content for the new journal entry)
     */
    public void addJournalEntry(JournalEntry journalEntry){
//        if(validateAddInput(journalEntry)){
//            journalModel.addEntry(journalEntry.getTitle(), journalEntry.getContent());
//        } else{
//            //journalView.displayErrorMessage("Both title and content should not be empty.");
//        }
    }


    /**
     * Updates the content of the provided journal entry with new content if the entry and new content are valid.
     *
     * @param journalEntry the journal entry to be updated
     * @param newContent   the new content to replace the existing content in the journal entry
     */
    public void updateJournalEntry(JournalEntry journalEntry, String newContent){
//        if(journalEntry != null && !newContent.isEmpty()){
//            this.journalEntry.get().updateContent(newContent);
//        } else {
//            //journalView.displayErrorMessage("Invalid input.");
//        }
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