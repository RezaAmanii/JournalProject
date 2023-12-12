package org.group12.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.group12.model.toDoSubTask.Globals;
import org.group12.model.todo.ITask;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class TaskView  {

    public static void openNewForm(String formName,String title,boolean resizable) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(Globals.class.getResource(formName)));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setResizable(resizable);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static Pane createCheckBoxPane(ITask task){
        Pane pane = new Pane();
        pane.setMaxHeight(35.0);
        pane.setMaxWidth(31.0);
        pane.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        CheckBox checkBox = getCheckBox(task);
        pane.getChildren().add(checkBox);
        GridPane.setMargin(pane, new Insets(10.0, 10.0, 10.0, 10.0));
        pane.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));

        GridPane.setHalignment(pane, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(pane, javafx.geometry.VPos.CENTER);
        return pane;
    }

    public static CheckBox getCheckBox(ITask task) {
        CheckBox checkBox = new CheckBox();
        checkBox.setLayoutX(1.0);
        checkBox.setMnemonicParsing(false);
        checkBox.setPrefHeight(35.0);
        checkBox.setPrefWidth(32.0);
        checkBox.setSelected(task.getStatus());

        checkBox.setOnMouseClicked(event -> {
            //allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(selectedTask)).getSubTasks().get(findTheSubTask(task)).setFinished(checkBox.isSelected());
        });

        checkBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        return checkBox;
    }

    public static GridPane createNewTaskPane() {
        GridPane newTaskPane=new GridPane();
        newTaskPane.setMinHeight(57.0);
        newTaskPane.setMinWidth(306.0);
        newTaskPane.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: white;");
        addColumnConstraints(newTaskPane);
        addRowConstraints(newTaskPane);
        VBox.setMargin(newTaskPane, new Insets(10.0, 10.0, 10.0, 10.0));
        return newTaskPane;
    }

    public static void addColumnConstraints(GridPane pane){
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMaxWidth(148.111083984375);
        col1.setMinWidth(10.0);
        col1.setPercentWidth(16.0);
        col1.setPrefWidth(48.888875325520836);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMaxWidth(266.3333053588867);
        col2.setMinWidth(10.0);
        col2.setPercentWidth(84.0);
        col2.setPrefWidth(215.11112467447913);


        ColumnConstraints col3 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMaxWidth(266.3333053588867);
        col2.setMinWidth(10.0);
        col2.setPercentWidth(84.0);
        col2.setPrefWidth(215.11112467447913);
        pane.getColumnConstraints().addAll(col1, col2, col3);
    }

    public static void addRowConstraints(GridPane pane){
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        pane.getRowConstraints().add(row1);
    }

    public static String getInputFromUser(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New SubTask");
        dialog.setHeaderText("Enter the name of the new SubTask");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return "New subtask";
    }




}
