package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.group12.Observers.IJournalObserver;
import org.group12.controller.JournalController;
import org.group12.model.ItemsSet;
import org.group12.model.journal.IEntry;
import org.group12.model.journal.JournalEntry;
import org.group12.util.CastHelper;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * JournalEntryCard class represents a single journal entry in the UI.
 * It provides methods to interact with the journal entry, such as saving changes.
 */

public class JournalEntryCard extends AnchorPane implements Initializable, IJournalObserver {
    private final String ID;
    private final JournalController controller;
    private final IEntry entry;
    private final CardUpdater cardUpdater;

    // UI elements
    @FXML
    private Label titleLabel;
    @FXML
    private TextArea content;
    @FXML
    private Label dateModified;
    @FXML
    private Label NrOfWords;


    /**
     * Constructs a JournalEntryCard with the specified ID and items.
     * Initializes the controller, entry, cardUpdater, and loads the FXML file.
     *
     * @param ID    The unique identifier for the journal entry. Must not be null.
     * @param items The set of items associated with the journal entry. Must not be null.
     *
     * @throws NullPointerException if ID or items is null.
     * @throws ClassCastException if items.getItem(ID) cannot be cast to JournalEntry.
     * @throws RuntimeException if the FXML file cannot be loaded.
     */
    public JournalEntryCard(String ID, ItemsSet items){
        this.ID = ID;
        this.controller = JournalController.getInstance();

        this.entry = CastHelper.castObject(JournalEntry.class, items.getItem(ID));
        this.cardUpdater = new CardUpdater();
        FXMLLoaderService fxmlLoaderService = new FXMLLoaderService();
        fxmlLoaderService.loadFXML(this, this, "JournalEntryCard.fxml");
    }
    /**
     * Initializes the JournalEntryCard after its root element has been completely processed.
     * Sets the text of various labels (titleLabel, content, dateModified, NrOfWords) using methods from the JournalController class.
     * Calls the update() method to refresh the UI.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     *
     * @throws NullPointerException if entry is null.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.titleLabel.setText(JournalController.getEntryTitle(entry));
        this.content.setText(JournalController.getEntryContent(entry));
        this.dateModified.setText(JournalController.getEntryDateModified(entry));
        this.NrOfWords.setText(JournalController.getNrOfWords(entry));
        update();
    }

    /**
     * Handles a double click event on the JournalEntryCard.
     * Opens a dialog to rename the entry, and updates the entry title if a new title is provided.
     *
     * @throws NullPointerException if this.entry or controller is null.
     */
    private void handleDoubleClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rename entry");
        dialog.setHeaderText("Enter new title");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(title -> {
            controller.setEntryTitle(this.entry, title);
            update();
        });
    }
    /**
     * Sets a mouse click event handler on the JournalEntryCard.
     * If the card is double-clicked, it calls the handleDoubleClick() method.
     */
    private void setDoubleClickEvent() {
        setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDoubleClick();
            }
        });
    }
    /**
     * Handles a click event on the title of the JournalEntryCard.
     * Sets a double click event handler on the card when the title is clicked.
     */
    @FXML
    public void titleClicked() {
        setDoubleClickEvent();
    }

    /**
     * Handles a click event on the save button of the JournalEntryCard.
     * Updates the content of the associated journal entry with the current text in the content area.
     * Prints a message to the console and updates the UI.
     */
    @FXML
    private void saveButtonClicked(MouseEvent event){
        controller.updateJournalEntry(this.entry, this.content.getText());
        System.out.println("saving");
        update();
    }
    /**
     * Handles a key event in the content area of the JournalEntryCard.
     * Updates the content of the associated journal entry with the current text in the content area.
     * Updates the UI and sets the caret position back to its original position.
     *
     * @param event The KeyEvent triggered by user input in the content area.
     */
    @FXML
    private void saveContent(KeyEvent event){
        // Get the current caret position
        int caretPosition = content.getCaretPosition();

        // Update the content
        controller.updateJournalEntry(this.entry, this.content.getText());

        // Update the UI
        update();

        // Set the caret position back to its original position
        content.positionCaret(caretPosition);
    }

    /**
     * Handles a click event on the delete button of the JournalEntryCard.
     * Updates the content of the associated journal entry to an empty string and updates the UI.
     *
     * @param event The MouseEvent triggered by the click on the delete button.
     */
    @FXML
    private void deleteButtonClicked(MouseEvent event){
         controller.clearJournalEntry(entry);
         update();
    }


    /**
     * Updates the JournalEntryCard by calling the updateJournalEntryCard method of the CardUpdater.
     * Passes the necessary parameters to the updateJournalEntryCard method.
     */
    public void update() {
        cardUpdater.updateJournalEntryCard(entry, controller, titleLabel, content, dateModified, NrOfWords);
    }

    /**
     * Returns the ID of the JournalEntryCard.
     *
     * @return The ID of the JournalEntryCard.
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns the JournalEntry associated with the JournalEntryCard.
     *
     * @return The JournalEntry associated with the JournalEntryCard.
     */
    public IEntry getEntry(){
        return entry;
    }



}
