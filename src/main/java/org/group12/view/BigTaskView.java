package org.group12.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import org.group12.model.todo.IBigTask;

import java.util.Optional;

public class BigTaskView {

    public ProgressIndicator createProgressIndicator(IBigTask task){
        double finishedTaskPercentage = calculateTaskPercentage(task);
        ProgressIndicator progressIndicator = new ProgressIndicator(finishedTaskPercentage);
        GridPane.setHalignment(progressIndicator, HPos.CENTER);
        GridPane.setMargin(progressIndicator, new Insets(3.0, 3.0, 3.0, 3.0));
        return progressIndicator;
    }

    public double calculateTaskPercentage(IBigTask task) {
        double percentageCompleted = 0;
        if(!task.getSubTaskList().isEmpty()){
            percentageCompleted = (double) task.getCompletedSubTasks().size() / task.getSubTaskList().size();
        } else{
            return 0;
        }
        return percentageCompleted;
    }

    public String getInputFromUser(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Task");
        dialog.setHeaderText("Enter the name of the new task");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("New task");
    }

}
