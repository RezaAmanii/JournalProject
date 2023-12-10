package org.group12.controllerView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE;

public class JournalWindowManager extends AnchorPane {
    @FXML
    private DatePicker entryDate;

    @FXML
    private Label entryDateLabel;

    @FXML
    private Label prevDayBtn;

    @FXML
    void onNextDayClk(MouseEvent event) {
        var currentVal = entryDate.valueProperty().get();
        entryDate.valueProperty().set(currentVal.plusDays(1));
    }
    @FXML
    void onPrevDayClk(MouseEvent event) {
        var currentVal = entryDate.valueProperty().get();
        entryDate.valueProperty().set(currentVal.minusDays(1));
    }
    @FXML
    public void initialize() {
        createBindings();

        entryDate.valueProperty().setValue(LocalDate.now());
    }
    private void createBindings() {
        journalEntry = new SimpleObjectProperty<>(journalModel.getEntryForDate(LocalDateTime.now()));
        journalEntry.bind(entryDate.valueProperty().map(journalModel::getEntryByDate));
        journalEntry.addListener((observable, oldValue, newValue) -> content.setText(newValue.getContent()));

        entryDateLabel.textProperty()
                .bind(entryDate.valueProperty()
                        .map(date -> date.format(ISO_DATE)));
    }
}
