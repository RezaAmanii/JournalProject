package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.group12.model.Calendar.interfaces.IEvent;
import org.group12.util.Globals;

import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import static java.util.Optional.ofNullable;

/**
 * This class represents the Event Details view.
 * It displays the details of an event and allows for event deletion.
 */
public class EventDetailsView {

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @FXML
    private Button deleteBtn;

    @FXML
    private TextArea descriptionTxt;

    @FXML
    private Label fromDate;

    @FXML
    private BorderPane root;

    @FXML
    private Label titleLbl;

    @FXML
    private Label toDate;


    /**
     * Initializes the Event Details view with the event data and delete event action.
     *
     * @param eventData The event data.
     * @param deleteEventAction The action to delete the event.
     */
    public void _initialize(IEvent eventData, Consumer<String> deleteEventAction) {
        this.descriptionTxt.setText(eventData.getDescription());
        this.titleLbl.setText(eventData.getTitle());
        this.fromDate.setText(getFromData(eventData));
        this.toDate.setText(getToDate(eventData));
        deleteBtn.setOnMouseClicked(event -> {
            Globals.showConfirmationAlert("Confirm deleting event?",
                    () -> {
                        deleteEventAction.accept(eventData.getID());
                        ((Stage) root.getScene().getWindow()).close();
                    });
        });
    }

    /**
     * Retrieves the formatted to-date string from the event data.
     *
     * @param eventData The event data.
     * @return The formatted to-date string, or an empty string if it is null.
     */
    private String getToDate(IEvent eventData) {
        return ofNullable(eventData.getTimeFrame())
                .map(Pair::getValue)
                .map(DATE_FORMATTER::format)
                .orElse("");
    }

    /**
     * Retrieves the formatted from-date string from the event data.
     *
     * @param eventData The event data.
     * @return The formatted from-date string, or an empty string if it is null.
     */
    private String getFromData(IEvent eventData) {
        return ofNullable(eventData.getTimeFrame())
                .map(Pair::getKey)
                .map(DATE_FORMATTER::format)
                .orElse("");
    }
}
