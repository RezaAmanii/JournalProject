package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.group12.Listeners.JournalClickListener;
import org.group12.Observers.IJournalObserver;
import org.group12.controller.JournalController;
import org.group12.model.ItemsSet;
import org.group12.model.journal.JournalEntry;
import org.group12.util.CastHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class JournalEntryCard extends AnchorPane implements Initializable, IJournalObserver {
    // TODO: hur ska items hanteras? här, I en todoPage?, ska vi casta här, ska det vara INameable?
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

    // TODO: lägg till TaskController i konstruktorn
    public JournalEntryCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.controller = JournalController.getInstance();

        this.entry = CastHelper.castObject(JournalEntry.class, items.getItem(ID));

        FXMLLoaderService fxmlLoaderService = new FXMLLoaderService();
        fxmlLoaderService.loadFXML(this, this, "JournalEntryCard.fxml");

        this.cardUpdater = new CardUpdater(items);

        update();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeFields();
        update();
    }


    private void initializeFields() {
        this.titleLabel.setText(JournalController.getEntryTitle(entry));
        this.dateModified.setText(JournalController.getEntryDateModified(entry));
        this.NrOfWords.setText(JournalController.getNrOfWords(entry));
    }


    @FXML
    private void titleClicked(){

        // controller.edittitle(entry, string)
        update();
    }
    @FXML
    private void saveButtonClicked(MouseEvent event){
         controller.updateJournalEntry(entry, content.getText());
         update();
    }
    @FXML
    private void enterClicked(MouseEvent event){
         controller.updateJournalEntry(entry, content.getText());
//         controller.updateJournalEntryTitel(entry, titleLabel.getText();
        update();
    }
    @FXML
    private void deleteButtonClicked(MouseEvent event){
         controller.updateJournalEntry(entry, "");
         update();
    }


    // TODO: hur funkar taskController?

    // TODO: protection måste läggas till
    public void update() {
        cardUpdater.updateJournalEntryCard(ID, entry, titleLabel, content);
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
