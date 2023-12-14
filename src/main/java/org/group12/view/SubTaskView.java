package org.group12.view;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.group12.util.UserInputHandler;

import java.util.Optional;

/**
 * Manages the view for adding a new SubTask.
 */
public class SubTaskView implements UserInputHandler {

    /**
     * Retrieves input from the user for creating a new subtask.
     *
     * @return The name of the new subtask provided by the user. If no name is provided, returns "New subTask" by default.
     */
    @Override
    public String getInputFromUser() {
        String userInput = "";
        boolean validInput = false;

        while (!validInput) {
            Optional<String> result = getSubTaskNameFromUser();
            if (result.isPresent() && !result.get().isEmpty()) {
                userInput = result.get();
                validInput = true;
            } else {
                displayWarningDialog();
            }
        }
        return userInput;
    }


    /**
     * Retrieves the name of the subtask from the user via a text input dialog.
     *
     * @return An Optional containing the user-input subtask name, or empty if canceled.
     */
    private Optional<String> getSubTaskNameFromUser() {
        TextInputDialog dialog = createTextInputDialog();
        return dialog.showAndWait();
    }

    /**
     * Creates a text input dialog for getting the subtask name from the user.
     *
     * @return A configured TextInputDialog object.
     */
    private TextInputDialog createTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Subtask");
        dialog.setHeaderText("Enter the name of the new Subtask");
        dialog.setContentText("Name:");
        return dialog;
    }

    /**
     * Displays a warning dialog when the subtask name provided by the user is empty.
     */
    @Override
    public void displayWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Subtask name cannot be empty.");
        alert.setContentText("Please enter a valid subtask name.");
        alert.showAndWait();
    }

}
