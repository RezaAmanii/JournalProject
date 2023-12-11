package org.group12.view.cards;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.group12.model.ItemsSet;
import org.group12.model.todo.ITask;

import java.io.IOException;

public class TaskCard extends AnchorPane {
    // TODO: hur ska items hanteras? här, I en todoPage?, ska vi casta här, ska det vara INameable?
    private final String ID;
    private final ItemsSet items;
    //private final TaskController taskController;
    @FXML
    private Label titleLabel;
    @FXML
    private CheckBox statusCheckBox;

    // TODO: lägg till TaskController i konstruktorn
    public TaskCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        //this.taskController = taskController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //update();
    }

    // TODO: hur funkar taskController?
    @FXML
    private void checkBoxToggled() {
        boolean isSelected = statusCheckBox.isSelected();
        //taskController.handleSetStatus(ID, isSelected);
    }

    // TODO: method name might be changed to better fit the actual way the title is changed
    @FXML
    private void titleClicked() {
        // here should be the code to get the new title
        //String newTitle =
        //taskController.changeListTitle(newTitle);
    }

    // TODO: protection måste läggas till
    public void update() {
        // get the BigTask to get the information from
        try {
            ITask task = (ITask) items.getItem(ID);
            // Set the Title
            String title = task.getTitle();
            titleLabel.setText(title);

            // Set the status
            boolean isCompleted = task.getStatus();
            statusCheckBox.setSelected(isCompleted);

        } catch (ClassCastException e) {
            // If the cast fails, print an error message
            System.out.println("Item with ID " + ID + " is not a ITask!");
        }
    }

    public String getID() {
        return ID;
    }
}