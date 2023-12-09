package org.group12.view.cards;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.group12.controller.CalendarController;
import org.group12.controller.JournalController;
import org.group12.model.Calendar.Calendar;
import org.group12.model.Calendar.Event;
import org.group12.model.ItemsSet;
import org.group12.model.journal.JournalEntry;
import org.group12.util.CastHelper;

import java.io.IOException;

public class EventCard extends AnchorPane {
    // TODO: hur ska items hanteras? här, I en todoPage?, ska vi casta här, ska det vara INameable?
    private final String ID;
    private final ItemsSet items;
    private final CalendarController controller;
    private final CardUpdater cardUpdater;
    private final Event event;
    @FXML
    private Label titleLabel;
    @FXML
    private Label contentLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private Button makeRecurringButton;
    @FXML
    private Button editButton;

    // Proposal: Opens a window where you can tag the event from list of all tags and has the option to create a new tag
    // or just have a scrollable list of all tags with a remove button next to it? And add at the bottom then
    @FXML
    private Button tagAddButton;
    @FXML
    private Button tagRemoveButton;

    // TODO: lägg till CalendarController i konstruktorn
    public EventCard(String ID, CalendarController controller,ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.controller = new CalendarController(); // TODO: Gör om till getInstacne()
//        Event tempevent = null;
//        try {
//            // Get Event
//            tempevent = (Event) items.getItem(ID);
//        } catch (ClassCastException e) {
//            // If the cast fails, print an error message
//            System.out.println("Item with ID " + ID + " is not an Event!");
//        }
//        this.event = tempevent;
        this.event = CastHelper.castObject(Event.class, items.getItem(ID));

        FXMLLoaderService fxmlLoaderService = new FXMLLoaderService();
        fxmlLoaderService.loadFXML(this, this, "EventCard.fxml");

        this.cardUpdater = new CardUpdater(items);
        update();

    }
    @FXML
    private void descriptionClicked() {

        // controller.editdescription(event)
    }
    @FXML
    private void titleClicked(){
        // controller.edittitle
    }
    @FXML
    private void deleteEvent(){
        // controller.deleteEvent(event)
    }
    @FXML
    private void makeRecurring(){
        // controller.makeRecurring(event)
    }
    @FXML
    private void editEvent(){
        // controller.editEvent(event)
    }
    @FXML
    private void tagEvent(){
        // controller.tagEvent(event, tag)
    }
    @FXML
    private void removeTag(){
        // controller.removeTag(event, tag)
    }


    // TODO: protection måste läggas till
    public void update() {
        cardUpdater.updateEventCard(ID, event, titleLabel, contentLabel);

//        // Check if event is not null
//        if (this.event != null) {
//            // Set title
//            String title = this.event.getTitle();
//            titleLabel.setText(title);
//            // Set content
//            String content = this.event.getDescription();
//            contentLabel.setText(content);
//        } else {
//            // If event is null, print an error message
//            System.out.println("Event with ID " + ID + " is null!");
//        }
    }


    public String getID() {
        return ID;
    }
}
