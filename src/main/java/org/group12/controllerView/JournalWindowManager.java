package org.group12.controllerView;


import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.group12.model.journal.JournalEntry;

import java.net.URL;
import java.util.ResourceBundle;

import static java.time.format.DateTimeFormatter.ISO_DATE;

public class JournalWindowManager implements Initializable {



    private TextArea content;


    private DatePicker entryDate;


    private Label entryDateLabel;


    private Label prevDayBtn;

    public DatePicker getEntryDate() {
        return entryDate;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void loadView(TextArea content,DatePicker entryDate,Label entryDateLabel, Label prevDayBtn)
    {
        this.content = content;
        this.entryDate = entryDate;
        this.entryDateLabel = entryDateLabel;
        this.prevDayBtn = prevDayBtn;
    }


    public JournalEntry prepareJournal()
    {
        return new JournalEntry("Posts",entryDate.getValue(),content.getText());
    }
    public void createBindings() {
        entryDateLabel.textProperty()
                .bind(entryDate.valueProperty()
                        .map(date -> date.format(ISO_DATE)));
    }

    public boolean deleteJournal()
    {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Confirm");
        alert.setContentText("Confirm deleting journal?");

        var option = alert.showAndWait().orElse(ButtonType.CANCEL);
        if(ButtonType.OK.equals(option)) {
            return true;
        }else
            return false;
    }

    public void getPevDayClick(JournalEntry journalEntry)
    {
        var currentVal = entryDate.valueProperty().get();
        entryDate.valueProperty().set(currentVal.minusDays(1));
        if(journalEntry != null)
            content.setText(journalEntry.getContent());
        else
            content.setText("");
    }

    public void getNexDayClick(JournalEntry journalEntry)
    {
        var currentVal = entryDate.valueProperty().get();
        entryDate.valueProperty().set(currentVal.plusDays(1));
        if(journalEntry != null)
            content.setText(journalEntry.getContent());
        else
            content.setText("");
    }

}