package org.group12.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.group12.model.Model;
import org.group12.model.Task;
import org.group12.TaskCard;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label label;
    Model model = new Model();
    @FXML
    private VBox taskBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Task t: model.getTasks()) {
            label.setText(t.getTitle());
            System.out.println(t.getTitle());
            //taskBox.getChildren().add((new TaskCard(t.getTitle())));
        }
    }
}
