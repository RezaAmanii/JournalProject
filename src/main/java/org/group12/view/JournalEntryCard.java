package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.group12.Listeners.JournalClickListener;
import org.group12.Observers.IJournalObserver;
import org.group12.controller.JournalController;
import org.group12.model.ItemsSet;
import org.group12.model.journal.JournalEntry;
import org.group12.util.CastHelper;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class JournalEntryCard extends AnchorPane implements Initializable, IJournalObserver {
    private final String ID;
    private final ItemsSet items;
    private final JournalController controller;
    private final JournalEntry entry;
    private final CardUpdater cardUpdater;
    @FXML
    private Label titleLabel;
    @FXML
    private TextArea content;
    @FXML
    private Label dateModified;
    @FXML
    private Label NrOfWords;
    @FXML
    private ImageView saveButton;
    @FXML
    private ImageView deleteButton;

    private JournalClickListener clickListener;

    public JournalEntryCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.controller = JournalController.getInstance();

        this.entry = CastHelper.castObject(JournalEntry.class, items.getItem(ID));
        this.cardUpdater = new CardUpdater(items);
        FXMLLoaderService fxmlLoaderService = new FXMLLoaderService();
        fxmlLoaderService.loadFXML(this, this, "JournalEntryCard.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeFields();
        update();
    }


    private void initializeFields() {
        this.titleLabel.setText(JournalController.getEntryTitle(entry));
        this.content.setText(JournalController.getEntryContent(entry));
        this.dateModified.setText(JournalController.getEntryDateModified(entry));
        this.NrOfWords.setText(JournalController.getNrOfWords(entry));
    }
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
    private void setDoubleClickEvent() {
        setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDoubleClick();
            }
        });
    }
    @FXML
    public void titleClicked() {
        setDoubleClickEvent();
    }

    @FXML
    private void saveButtonClicked(MouseEvent event){

         controller.updateJournalEntry(this.entry, this.content.getText());
         System.out.println("saving");
         update();
    }
    @FXML
    private void saveContent(KeyEvent event){
        // Get the current caret position
        int caretPosition = content.getCaretPosition();

        // Update the content
        controller.updateJournalEntry(this.entry, this.content.getText());


        update();
        // Set the caret position back to its original position
        content.positionCaret(caretPosition);
    }
    @FXML
    private void deleteButtonClicked(MouseEvent event){
         controller.updateJournalEntry(entry, "");
         update();
    }


    // TODO: protection måste läggas till
    public void update() {
        cardUpdater.updateJournalEntryCard(entry, controller, titleLabel, content, dateModified, NrOfWords);

    }

    public String getID() {
        return ID;
    }

    public void setClickListener(JournalClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public JournalEntry getEntry(){
        return entry;
    }



}
