package org.group12;

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/task_card.fxml"));
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
}
