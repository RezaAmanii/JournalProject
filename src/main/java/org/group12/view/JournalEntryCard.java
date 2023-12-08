package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.group12.controller.JournalController;
import org.group12.model.ItemsSet;
import org.group12.model.journal.JournalEntry;

import java.io.IOException;

public class JournalEntryCard extends AnchorPane{
    // TODO: hur ska items hanteras? här, I en todoPage?, ska vi casta här, ska det vara INameable?
    private final String ID;
    private final ItemsSet items;
    private final JournalController controller;
    @FXML
    private Label titleLabel;
    @FXML
    private Label contentLabel;

    // TODO: lägg till TaskController i konstruktorn
    public JournalEntryCard(String ID, JournalController controller, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.controller = new JournalController(); // TODO: Gör om till getInstacne()

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JournalCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        update();
    }

    @FXML
    private void cardClicked() {
        // controller.edittext
    }
    @FXML
    private void titleClicked(){
        // controller.edittitle
    }


    // these buttons should probably be in the Journal and not JournalEntry
    @FXML
    private void nextDayButtonClicked() {
    }
    @FXML
    private void prevDayButtonClicked(){
    }

    // TODO: hur funkar taskController?

    // TODO: protection måste läggas till
    public void update() {
        Object item = items.getItem(ID);
        try {
        // Hämta JournalEntry
        JournalEntry journalEntry = (JournalEntry) item;
        //        JournalEntry journalEntry = controller.getJournalEntry(ID);
        // Sätt titel
        String title = journalEntry.getTitle();
        titleLabel.setText(title);
        // Sätt content
        String content = journalEntry.getContent();
        contentLabel.setText(content);
        } catch (ClassCastException e) {
    // If the cast fails, print an error message
    System.out.println("Item with ID " + ID + " is not a JournalEntry!");
        }

    }

    public String getID() {
        return ID;
    }
}
