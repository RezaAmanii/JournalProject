package org.group12.view;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class SubTaskView {


    public String getInputFromUser(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New SubTask");
        dialog.setHeaderText("Enter the name of the new SubTask");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("New subtask");
    }



}
