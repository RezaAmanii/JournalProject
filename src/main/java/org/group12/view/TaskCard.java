package org.group12.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TaskCard extends AnchorPane {
    @FXML
    private Label taskTitleLabel;

    public TaskCard(String title){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task_card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        taskTitleLabel.setText(title);
    }

    public void taskCheckBoxClick(ActionEvent actionEvent) {
    }

    public String getID(ActionEvent actionEvent) {
        return this.ID;
    }
}
