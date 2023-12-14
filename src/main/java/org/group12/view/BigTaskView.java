package org.group12.view;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

/**
 * Represents a view for the bigTaskCard class.
 */
public class BigTaskView {

    /**
     * Retrieves input from the user for creating a new task.
     *
     * @return The user-input task name or "New task" if no input is provided.
     */
    public String getInputFromUser(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Task");
        dialog.setHeaderText("Enter the name of the new task");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("New task");
    }

}
