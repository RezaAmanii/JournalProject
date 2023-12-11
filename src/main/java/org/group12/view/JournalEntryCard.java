package org.group12.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.group12.controller.JournalController;
import org.group12.model.ItemsSet;
import org.group12.model.journal.JournalEntry;
import org.group12.util.CastHelper;

public class JournalEntryCard extends AnchorPane{
    // TODO: hur ska items hanteras? här, I en todoPage?, ska vi casta här, ska det vara INameable?
    private final String ID;
    private final ItemsSet items;
    private final JournalController controller;
    private final JournalEntry entry;
    private final CardUpdater cardUpdater;
    @FXML
    private Label titleLabel;
    @FXML
    private TextArea contentText;

    // TODO: lägg till TaskController i konstruktorn
    public JournalEntryCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.controller = JournalController.getInstance(); // TODO: Gör om till getInstacne()

        this.entry = CastHelper.castObject(JournalEntry.class, items.getItem(ID));

        FXMLLoaderService fxmlLoaderService = new FXMLLoaderService();
        fxmlLoaderService.loadFXML(this, this, "JournalEntryCard.fxml");

        this.cardUpdater = new CardUpdater(items);

        update();
    }

    @FXML
    private void titleClicked(){
        // controller.edittitle(entry, string)
    }
    @FXML
    private void saveButtonClicked(MouseEvent event){
         controller.updateJournalEntry(entry, contentText.getText());
    }
    @FXML
    private void enterClicked(MouseEvent event){
         controller.updateJournalEntry(entry, contentText.getText());
//         controller.updateJournalEntryTitel(entry, titleLabel.getText();
    }
    @FXML
    private void deleteButtonClicked(MouseEvent event){
         //controller.removeJournalEntry(entry);
    }

    // TODO: hur funkar taskController?

    // TODO: protection måste läggas till
    public void update() {
        cardUpdater.updateJournalEntryCard(ID, entry, titleLabel, contentText);

    }

    public String getID() {
        return ID;
    }
}
