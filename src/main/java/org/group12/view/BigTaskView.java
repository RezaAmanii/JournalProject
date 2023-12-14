package org.group12.view;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.group12.util.UserInputHandler;

import java.util.Optional;

/**
 * Represents a view for the bigTaskCard class.
 */
public class BigTaskView implements UserInputHandler {

    /**
     * Retrieves input from the user for creating a new task.
     *
     * @return The name of the new task provided by the user. If no name is provided, returns "New task" by default.
     */
    @Override
    public String getInputFromUser() {
        String userInput = "";
        boolean validInput = false;

        while (!validInput) {
            Optional<String> result = getTaskNameFromUser();
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
     * Retrieves the name of the task from the user via a text input dialog.
     *
     * @return An Optional containing the user-input task name, or empty if canceled.
     */
    private Optional<String> getTaskNameFromUser() {
        TextInputDialog dialog = createTextInputDialog();
        return dialog.showAndWait();
    }

    /**
     * Creates a text input dialog for getting the task name from the user.
     *
     * @return A configured TextInputDialog object.
     */
    private TextInputDialog createTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Task");
        dialog.setHeaderText("Enter the name of the new task");
        dialog.setContentText("Name:");
        return dialog;
    }

    /**
     * Displays a warning dialog when the task name provided by the user is empty.
     */
    @Override
    public void displayWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Task name cannot be empty.");
        alert.setContentText("Please enter a valid task name.");
        alert.showAndWait();
    }


}
