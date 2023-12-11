package org.group12.controller;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalEntry;
import org.group12.controllerView.JournalWindowManager;
import org.group12.view.JournalView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.group12.util.TextUtils.getWordCount;

public class JournalController implements IController, IObservable {

    private Container container;
    private JournalView journalView;
    private ObjectProperty<JournalEntry> journalEntry; // not allowed here ?
    private static JournalController instance;
    private static Journal journalModel;
    private JournalWindowManager journalWindowManager;

    private static Items itemMap;
    private final List<IPlanITObserver> observers = new ArrayList<>();


    //@FXML
    //private TextArea content;

    //@FXML
    //private DatePicker entryDate;

    //@FXML
    //private Label entryDateLabel;

    //@FXML
    //private Label prevDayBtn;
    LocalDateTime entryDateTime;


    /**@FXML public void initialize() {
    journalWindowManager = new JournalWindowManager();
    journalWindowManager.loadView(content, entryDate, entryDateLabel,  prevDayBtn);
    journalWindowManager.createBindings();
    entryDate.valueProperty().setValue(LocalDate.now());
    entryDateTime = entryDate.getValue().atStartOfDay();
    JournalEntry journalEntry1 = journalModel.getEntryByDate(entryDateTime);
    if(journalEntry1 != null)
    content.setText(journalEntry1.getContent());

    }**/


    public JournalController() {
        this.container = Container.getInstance();
        this.journalModel = container.getJournal();
        this.itemMap = Items.getInstance();
        //journalModel.addObserver(journalView);

    }

    public static JournalController getInstance() {
        if (instance == null) {
            instance = new JournalController();
        }
        return instance;
    }

    public static String getEntryTitle(JournalEntry entry) {
        return entry.getTitle();

    }

    public static String getEntryDateModified(JournalEntry entry) {
        return entry.getModifiedTimestamp().toString();
    }

    public static String getNrOfWords(JournalEntry entry) {
        String entryContent = entry.getContent();
        return String.valueOf(getWordCount(entryContent));
    }

    public static List<JournalEntry> fetchEntry() {
        return journalModel.getEntries();
    }


    /**
     * Adds a new journal entry to the journal model if the input is valid.
     * Validates the provided journal entry to ensure that both title and content are not empty
     * before adding it to the journal.
     *
     * @param journalEntry the journal entry to be added
     *                     (contains title and content for the new journal entry)
     */
    //public void addJournalEntry(JournalEntry journalEntry) {
//        if(validateAddInput(journalEntry)){
//            journalModel.addEntry(journalEntry.getTitle(), journalEntry.getContent());
//        } else{
//            //journalView.displayErrorMessage("Both title and content should not be empty.");
//        }
//    }
    public void addJournalEntry(){
        journalModel.addEntry();
    }


    /**
     * Updates the content of the provided journal entry with new content if the entry and new content are valid.
     *
     * @param journalEntry the journal entry to be updated
     * @param newContent   the new content to replace the existing content in the journal entry
     */
    public void updateJournalEntry(JournalEntry journalEntry, String newContent) {
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
     * an empty list if no entries are found for the given date
     */
    public JournalEntry getEntryByDate(LocalDateTime date) {
        //return List<JournalEntry> todaysEntries = entryModel.getTodaysEntry(date);
        if (journalModel.getEntryByDate(date) == null) {
            journalModel.addEntry(date);
        }
        return journalModel.getEntryByDate(date);
    }


    /**
     * Retrieves all journal entries stored in the system.
     *
     * @return a list containing all journal entries stored in the system,
     * or an empty list if there are no entries
     */
    public List<JournalEntry> getAllEntries() {
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

    public void addObserver(IPlanITObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(IPlanITObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IPlanITObserver observer : observers) {
            observer.update();
        }
    }

    public void onAddEntry(MouseEvent mouseEvent) {
    }
}