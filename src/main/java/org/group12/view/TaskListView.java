package org.group12.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.group12.model.toDoSubTask.Globals;
import org.group12.model.todo.IBigTask;
import java.io.IOException;
import java.util.Objects;

public class TaskListView {


    public static ProgressIndicator createProgressIndicator(IBigTask task){
        double finishedTaskPercentage = calculateTaskPercentage(task);
        ProgressIndicator progressIndicator = new ProgressIndicator(finishedTaskPercentage);
        GridPane.setHalignment(progressIndicator, HPos.CENTER);
        GridPane.setMargin(progressIndicator, new Insets(3.0, 3.0, 3.0, 3.0));
        return progressIndicator;
    }

    public static double calculateTaskPercentage(IBigTask task) {
        double percentageCompleted = 0;
        if(!task.getSubTaskList().isEmpty()){
            percentageCompleted = (double) task.getCompletedSubTasks().size() / task.getSubTaskList().size();
        } else{
            return 0;
        }
        return percentageCompleted;
    }


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
