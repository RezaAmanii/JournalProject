package org.group12.view.cards;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TodoListCard extends AnchorPane {

    @FXML
    private Label todoListTitleLabel;

    @FXML
    private VBox todoListVBox;

    public TodoListCard(String title){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("todolist_card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        todoListTitleLabel.setText(title);
    }

    public VBox getTodoListVBox() {
        return todoListVBox;
    }
}
