package org.group12.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label label;
    @FXML
    private HBox startPageHBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TodoListCard todoListCard = new TodoListCard(model.getTodoList().getTitle());
    }
}
