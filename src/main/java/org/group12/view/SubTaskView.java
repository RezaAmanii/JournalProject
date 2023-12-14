package org.group12.view;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

/**
 * Manages the view for adding a new SubTask.
 */
public class SubTaskView {

    /**
     * Retrieves input from the user for creating a new SubTask.
     *
     * @return The name of the new SubTask provided by the user. If no name is provided, returns "New subtask" by default.
     */
    public String getInputFromUser(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New SubTask");
        dialog.setHeaderText("Enter the name of the new SubTask");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("New subtask");
    }

}
