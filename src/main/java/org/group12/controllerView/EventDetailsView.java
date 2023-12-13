package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.group12.model.Calendar.Event;

import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import static java.util.Optional.ofNullable;
import static org.group12.model.toDoSubTask.Globals.showConfirmationAlert;

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

//public void _initialize(Event eventData, Consumer<String> deleteEventAction, Consumer<Event> editEventAction, Consumer<Event> saveEventAction, Consumer<Event> makeRecurringAction)
    public void _initialize(Event eventData, Consumer<String> deleteEventAction){
        this.descriptionTxt.setText(eventData.getDescription());
        this.titleLbl.setText(eventData.getTitle());
        this.fromDate.setText(getFromData(eventData));
        this.toDate.setText(getToDate(eventData));
        deleteBtn.setOnMouseClicked(event -> {
            showConfirmationAlert("Confirm deleting event?",
                    () -> {
                        deleteEventAction.accept(eventData.getID());
                        ((Stage) root.getScene().getWindow()).close();
                    });
        });
    }

    private String getToDate(Event eventData) {
        return ofNullable(eventData.getTimeFrame())
                .map(Pair::getValue)
                .map(DATE_FORMATTER::format)
                .orElse("");
    }

    private String getFromData(Event eventData) {
        return ofNullable(eventData.getTimeFrame())
                .map(Pair::getKey)
                .map(DATE_FORMATTER::format)
                .orElse("");
    }
}
