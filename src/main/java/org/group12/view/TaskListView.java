package org.group12.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import org.group12.Observers.ITaskListObserver;
import org.group12.model.toDoSubTask.ToDoList;
import org.group12.model.toDoSubTask.ToDoTask;

import java.time.format.DateTimeFormatter;

public class TaskListView implements ITaskListObserver {

    @Override
    public void update() {

    }

    public static GridPane createListPane() {
        GridPane listToAppend = new GridPane();
        listToAppend.setMinHeight(33.0);
        listToAppend.setMinWidth(300.0);
        listToAppend.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-color: white; -fx-border-radius: 10;");

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(10.0);
        col2.setPercentWidth(75);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setMinWidth(10.0);
        col3.setPercentWidth(25);

        listToAppend.getColumnConstraints().addAll(col2, col3);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        listToAppend.getRowConstraints().add(row1);

        return listToAppend;
    }

    public static TextField createTaskNameLabel(ToDoList newList){
        TextField taskNameLBL = new TextField(newList.getListName());
        taskNameLBL.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent;");
        taskNameLBL.setEditable(false);
        taskNameLBL.setAlignment(Pos.CENTER);
        GridPane.setHalignment(taskNameLBL, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(taskNameLBL, javafx.geometry.VPos.CENTER);
        taskNameLBL.setFont(new Font("Berlin Sans FB Demi Bold", 22.0));
        return taskNameLBL;
    }

    public static Label createNumberOfTaskLabel(ToDoList newList){
        Label noOfTasks = new Label(String.valueOf(newList.getTasks().size()));
        noOfTasks.setAlignment(javafx.geometry.Pos.CENTER);
        noOfTasks.setTextFill(javafx.scene.paint.Color.WHITE);
        GridPane.setColumnIndex(noOfTasks, 1);
        GridPane.setHalignment(noOfTasks, javafx.geometry.HPos.CENTER);
        Font font2 = new Font("Berlin Sans FB Demi Bold", 22.0);
        noOfTasks.setFont(font2);
        return noOfTasks;
    }

    public static GridPane createTaskPane(){
        GridPane newTaskPane = new GridPane();
        newTaskPane.setMinHeight(70.0);
        newTaskPane.setMinWidth(250.0);
        newTaskPane.setStyle("-fx-background-color: #2f3f4e; -fx-background-radius: 10; -fx-border-color: white; -fx-border-radius: 10;");
        newTaskPane.getColumnConstraints().addAll(
                createColumnConstraints(12.5, 216),
                createColumnConstraints(50.0,216),
                createColumnConstraints(12.5, 104),
                createColumnConstraints(12.5, 104),
                createColumnConstraints(12.5, 104)
        );
        newTaskPane.getRowConstraints().addAll(
                createRowConstraints(60),
                createRowConstraints(40)
        );
        return newTaskPane;
    }

    public static ColumnConstraints createColumnConstraints(double width, double prefWidth){
        double minWidth = 10.0;
        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.SOMETIMES);
        col.setMinWidth(minWidth);
        col.setPercentWidth(width);
        col.setPrefWidth(prefWidth);
        return col;
    }

    public static RowConstraints createRowConstraints(double height){
        double minHeight = 10.0;
        RowConstraints row = new RowConstraints();
        row.setMinHeight(minHeight);
        row.setPercentHeight(height);
        row.setPrefHeight(height);
        return row;
    }

    public static Label createDeadlineLabel(IBigTask task){
        Label deadlineLabel = new Label(task.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyy - HH:mm")));
        deadlineLabel.setFont(new Font("Berlin Sans FB", 16.0));
        deadlineLabel.setStyle("-fx-text-fill: white");
        GridPane.setMargin(deadlineLabel,new Insets(3));
        GridPane.setValignment(deadlineLabel, javafx.geometry.VPos.CENTER);
        GridPane.setHalignment(deadlineLabel, javafx.geometry.HPos.CENTER);
        GridPane.setColumnIndex(deadlineLabel,1);
        GridPane.setRowIndex(deadlineLabel,1);
        return deadlineLabel;
    }

    public static ImageView createImportantImageView(IBigTask task){
        ImageView imageViewImportant = new ImageView(task.isFavourite() ? "/star.png" : "/starUnselected.png");
        imageViewImportant.setFitHeight(31.0);
        imageViewImportant.setFitWidth(31.0);
        imageViewImportant.setPickOnBounds(true);
        imageViewImportant.setPreserveRatio(true);
        GridPane.setColumnIndex(imageViewImportant, 4);
        GridPane.setRowSpan(imageViewImportant,2);
        return imageViewImportant;
    }

    public static ImageView createDeleteImageView(IBigTask task){
        ImageView imageViewDelete = new ImageView("/deleteWhite.png");
        imageViewDelete.setFitHeight(31.0);
        imageViewDelete.setFitWidth(31.0);
        imageViewDelete.setPickOnBounds(true);
        imageViewDelete.setPreserveRatio(true);
        GridPane.setColumnIndex(imageViewDelete, 3);
        GridPane.setRowSpan(imageViewDelete,2);
        return imageViewDelete;
    }

    public static TextField createTaskNameTextField(IBigTask task){
        TextField taskNameLBL = new TextField(task.getTitle());
        taskNameLBL.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent;");
        taskNameLBL.setEditable(false);
        taskNameLBL.setAlignment(Pos.CENTER);
        taskNameLBL.setFont(new Font("Berlin Sans FB Demi Bold", 22.0));
        GridPane.setHalignment(taskNameLBL, HPos.CENTER);
        GridPane.setValignment(taskNameLBL, VPos.CENTER);
        return taskNameLBL;
    }

    public static ProgressIndicator createProgressIndicator(IBigTask task){
        double tasksFinishPrecentage = calculateTaskPrecentage(task);
        ProgressIndicator progressIndicator = new ProgressIndicator(tasksFinishPrecentage);
        GridPane.setHalignment(progressIndicator, HPos.CENTER);
        GridPane.setMargin(progressIndicator, new Insets(3.0, 3.0, 3.0, 3.0));
        return progressIndicator;
    }

    public static double calculateTaskPrecentage(IBigTask task) {
        double precentageCompleted = 0;
        if(!task.getSubTaskList().isEmpty()){
            precentageCompleted = (double) task.getCompletedSubTasks().size() / task.getSubTaskList().size();
        } else{
            return 0;
        }
        return precentageCompleted;
    }

    public static ImageView createViewImageView(IBigTask task){
        ImageView imageView = new ImageView("/viewWhite.png");
        imageView.setFitHeight(31.0);
        imageView.setFitWidth(31.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        GridPane.setColumnIndex(imageView, 2);
        GridPane.setRowSpan(imageView,2);
        return imageView;
    }

    public static void configureGridPanePositions(TextField taskNameLabel, ProgressIndicator progressIndicator, ImageView imageView) {
        GridPane.setColumnIndex(taskNameLabel, 1);
        GridPane.setColumnIndex(progressIndicator, 0);
        GridPane.setRowSpan(progressIndicator,2);
        GridPane.setHalignment(imageView, HPos.CENTER);
        GridPane.setMargin(imageView, new Insets(2.0, 2.0, 2.0, 2.0));
    }











}
