package org.group12.view;

import javafx.scene.control.*;
import org.group12.util.UserInputHandler;


/**
 * Represents a view for handling task lists and user input.
 */
public class TaskListView implements UserInputHandler {

    /**
     * Retrieves input from the user for creating a new task list.
     *
     * @return The user-input task list name or "New list" if no input is provided.
     */
    @Override
    public String getInputFromUser() {
        TextInputDialog dialog = createTextInputDialog();
        return processDialogResult(dialog);
    }


    /**
     * Processes the result obtained from the input dialog.
     *
     * @param dialog The input dialog to process.
     * @return The user-input task list name or "New list" if canceled or invalid input.
     */
    private String processDialogResult(TextInputDialog dialog) {
        var result = dialog.showAndWait();

        if (result.isPresent()) {
            String userInputOriginal = result.get();
            String userInput = result.get().toLowerCase();
            if (userInput.contains("today") || userInput.contains("important")) {
                displayWarningDialog();
            } else {
                return userInputOriginal;
            }
        }
        return "New list";
    }


    /**
     * Displays a warning dialog for prohibited input.
     */
    @Override
    public void displayWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Choose another title.");
        alert.setHeaderText("You can not use this title.");
        alert.setContentText("The input provided is not allowed");
        alert.showAndWait();
    }

    /**
     * Creates a text input dialog for getting user input.
     *
     * @return A configured TextInputDialog object.
     */
    private TextInputDialog createTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New List");
        dialog.setHeaderText("Enter the name of the new list");
        dialog.setContentText("Name:");
        return dialog;
    }
}
