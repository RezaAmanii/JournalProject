package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.group12.controller.TaskController;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;

import java.io.IOException;
import java.util.HashMap;

public class BigTaskCard extends AnchorPane {
    // TODO: hur ska items hanteras? här, I en todoPage?, ska vi casta här, ska det vara INameable?
    private final String ID;
    private final ItemsSet items;
    //private final TaskController taskController;
    @FXML
    private Label titleLabel;
    @FXML
    private Label dueDateLabel;
    @FXML
    private CheckBox statusCheckBox;
    @FXML
    private ImageView favouriteImageView;

    // TODO: lägg till TaskController i konstruktorn
    public BigTaskCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        //this.taskController = taskController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bigTaskCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //update();
    }

    @FXML
    private void cardClicked() {

    }

    @FXML
    private void imageViewClicked() {

    }

    // TODO: hur funkar taskController?
    @FXML
    private void checkBoxToggled() {
        boolean isSelected = statusCheckBox.isSelected();
        //taskController.handleSetStatus(ID, isSelected);
    }

    // TODO: protection måste läggas till
    public void update() {
        // get the BigTask to get the information from
        IBigTask bigTask = (IBigTask) items.getItem(ID);

        // Set the Title
        String title = bigTask.getTitle();
        titleLabel.setText(title);

        // TODO: hur ska datumet formateras?
        // Set the due date
        String dueDate = bigTask.getDueDate().toString();
        dueDateLabel.setText(dueDate);

        // Set the status
        boolean isCompleted = bigTask.getStatus();
        statusCheckBox.setSelected(isCompleted);

        // Set the if favourite or not
        boolean isFavourite = bigTask.isFavourite();
        // TODO: what happens where depends on Jamals implementation
        if (isFavourite) {
            //favouriteImageView.
        } else {
            //favouriteImageView.
        }
    }

    public String getID() {
        return ID;
    }
}
