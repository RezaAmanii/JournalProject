package org.group12.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.group12.controller.CalendarController;
import org.group12.model.Calendar.Event;
import org.group12.model.ItemsSet;

public class EventCardTiny extends AnchorPane {
    private final String ID;
    private final ItemsSet items;
    private final CalendarController controller;
    private final Event event;
    private final CardUpdater cardUpdater;
    @FXML
    private Label titleLabel;
    @FXML
    private FlowPane tagsFlowPane;

    // TODO: OBS att items måste komma från SaveLoad
    public EventCardTiny(String ID, CalendarController controller, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.controller = new CalendarController(); // TODO: Gör om till getInstacne()
        Event tempevent = null;
        try {
            // Get Event
            tempevent = (Event) items.getItem(ID);
        } catch (ClassCastException e) {
            // If the cast fails, print an error message
            System.out.println("Item with ID " + ID + " is not an Event!");
        }
        this.event = tempevent;

        FXMLLoaderService fxmlLoaderService = new FXMLLoaderService();
        fxmlLoaderService.loadFXML(this, this, "EventCardTiny.fxml");

        this.cardUpdater = new CardUpdater();

        update();


    }
    @FXML
    private void cardClicked() {
        // controller.expandEvent(event);
    }
    public void update() {
        cardUpdater.updateEventCardTiny(event, ID, titleLabel, tagsFlowPane);
    }
}
