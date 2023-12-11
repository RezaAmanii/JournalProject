package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.group12.controller.TaskListController;
import org.group12.model.ItemsSet;
import org.group12.model.todo.ITask;
import org.group12.model.todo.ITaskList;

import java.io.IOException;

public class TaskListCard extends AnchorPane {
    // TODO: hur ska items hanteras? här, I en todoPage?, ska vi casta här, ska det vara INameable?
    private final String ID;
    private final ItemsSet items;
    private final TaskListController taskListController;
    @FXML
    private Label titleLabel;

    public TaskListCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.taskListController = TaskListController.getInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskListCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        update();
    }

    @FXML
    private void cardClicked() {

    }

    // TODO: method name might be changed to better fit the actual way the title is changed
    @FXML
    private void titleClicked() {
        // here should be the code to get the new title
        //String newTitle =
        //taskListController.changeListTitle(newTitle);
    }

    // TODO: protection måste läggas till
    public void update() {
        // get the TaskList to get the information from
        try {
            ITaskList taskList = (ITaskList) items.getItem(ID);
            // Set the Title
            String title = taskList.getTitle();
            titleLabel.setText(title);

        } catch (ClassCastException e) {
            // If the cast fails, print an error message
            System.out.println("Item with ID " + ID + " is not a ITaskList!");
        }
    }

    public String getID() {
        return ID;
    }
}