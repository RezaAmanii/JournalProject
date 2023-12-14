package org.group12.view;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class BigTaskView {

    public String getInputFromUser(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Task");
        dialog.setHeaderText("Enter the name of the new task");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("New task");
    }

}
