package org.group12.view;

import javafx.scene.control.*;


public class TaskListView {



    public String getInputFromUser() {
        TextInputDialog dialog = createTextInputDialog();
        return processDialogResult(dialog);
    }

    public TextInputDialog createTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New List");
        dialog.setHeaderText("Enter the name of the new list");
        dialog.setContentText("Name:");
        return dialog;
    }

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

    public void displayWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Choose another title.");
        alert.setHeaderText("You can not use this title.");
        alert.setContentText("The input provided is not allowed");
        alert.showAndWait();
    }

}
