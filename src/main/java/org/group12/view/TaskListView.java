package org.group12.view;

import javafx.scene.control.*;


public class TaskListView {

    /**
     * Gets user input from a text input dialog for creating a new list, ensuring certain restrictions on the title.
     *
     * @return The validated user input for the new list title or "New list" if restrictions are violated.
     */
    public String getInputFromUser() {
        TextInputDialog dialog = createTextInputDialog();
        return processDialogResult(dialog);
    }


    /**
     * Creates a TextInputDialog for entering the new list title.
     *
     * @return The created TextInputDialog.
     */
    public TextInputDialog createTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New List");
        dialog.setHeaderText("Enter the name of the new list");
        dialog.setContentText("Name:");
        return dialog;
    }


    /**
     * Processes the result from the TextInputDialog, validates the user input, and handles restricted words.
     *
     * @param dialog The TextInputDialog to process.
     * @return The validated user input or "New list" if restrictions are violated.
     */
    public String processDialogResult(TextInputDialog dialog) {
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
     * Displays a warning alert when restricted words are used in the input.
     */
    public void displayWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Choose another title.");
        alert.setHeaderText("You can not use this title.");
        alert.setContentText("The input provided is not allowed");
        alert.showAndWait();
    }

}
