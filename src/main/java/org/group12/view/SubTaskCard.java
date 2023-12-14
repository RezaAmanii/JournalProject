package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.group12.Listeners.SubTaskCardClickListener;
import org.group12.Observers.ITaskListObserver;
import org.group12.controller.SubTaskController;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.ITask;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Represents a SubTaskCard view element in the application.
 */
public class SubTaskCard extends AnchorPane implements Initializable, ITaskListObserver {

    // Class attributes
    private final String ID;
    private final ItemsSet items;

    // Controller
    private final SubTaskController subTaskController;

    // Listener
    private SubTaskCardClickListener clickListener;

    // FXML components
    @FXML public Label titleLabel;
    @FXML public Label dateCreatedLabel;
    @FXML public CheckBox statusCheckBox;
    @FXML public ImageView deleteSubTaskBtn;


    // Constructor
    public SubTaskCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.subTaskController = SubTaskController.getInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subTaskCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        spacingBetweenCards();
        update();
    }

    /**
     * Sets the spacing between cards in the view.
     */
    private void spacingBetweenCards() {
        double paddingValue = 10.0;
        VBox.setMargin(this, new Insets(paddingValue));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeFields();
        setupEventHandlers();
    }

    /**
     * Initializes the fields of the SubTaskCard.
     */
    private void initializeFields(){
        this.titleLabel.setText(subTaskController.getSubTaskTitle(this.ID));
        this.dateCreatedLabel.setText(subTaskController.getSubTaskDateCreated(this.ID));
        this.statusCheckBox.setSelected(subTaskController.getSubTaskStatus(this.ID));
    }


    /**
     * Sets up event handlers for various actions.
     */
    private void setupEventHandlers(){
        titleLabel.setOnMouseClicked(this::titleClicked);
        deleteSubTaskBtn.setOnMouseClicked(this::deleteSubTaskBtnClicked);
        statusCheckBox.setOnMouseClicked(this::checkBoxToggled);
    }

    /**
     * Handles the click event on the title label of the subtask.
     *
     * @param event The MouseEvent triggered by the user.
     */
    public void titleClicked(MouseEvent event) {
        setDoubleClickEvent();
        if(clickListener != null){
            clickListener.onSubTaskCardClicked(this);
        }
    }

    /**
     * Handles the click event on the delete button of the subtask.
     *
     * @param event The MouseEvent triggered by the user.
     */
    private void deleteSubTaskBtnClicked(MouseEvent event){
        ITask subTaskToRemove = subTaskController.getSubTaskByID(this.ID);
        subTaskController.handleRemoveSubTask(subTaskToRemove);
        update();
    }

    /**
     * Handles the click event on the status checkbox of the subtask.
     *
     * @param event The MouseEvent triggered by the user.
     */
    private void checkBoxToggled(MouseEvent event) {
        boolean isSelected = statusCheckBox.isSelected();
        subTaskController.setSubTaskStatus(ID, isSelected);
        update();
    }


    // Getters
    public String getID() {
        return ID;
    }

    /**
     * Sets up a double click event for the subtask card.
     */
    private void setDoubleClickEvent() {
        setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDoubleClick();
            }
        });
    }

    /**
     * Handles the action when the subtask is double-clicked for renaming.
     */
    private void handleDoubleClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rename Task");
        dialog.setHeaderText("Enter new name");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            subTaskController.renameSubTask(this.ID, name);
            update();
        });
    }


    /**
     * Updates the SubTaskCard view based on the latest data.
     */
    @Override
    public void update() {

        try{
            INameable item = items.getItem(this.ID);

            if(item instanceof ITask){
                ITask subTask = (ITask) item;

                this.titleLabel.setText(subTaskController.getSubTaskTitle(subTask.getID()));
                this.dateCreatedLabel.setText(subTaskController.getSubTaskDateCreated(subTask.getID()));
                this.statusCheckBox.setSelected(subTaskController.getSubTaskStatus(subTask.getID()));


            } else{
                System.out.println("Item with ID " + ID + " is not a ITask!");
            }
        } catch (ClassCastException error){
            System.out.println("Item with ID " + ID + " is not a ITask!");
        }
    }

    /**
     * Sets the click listener for the SubTaskCard.
     *
     * @param clickListener The listener to be set for SubTaskCard clicks.
     */
    public void setSubTaskCardListener(SubTaskCardClickListener clickListener) {
        this.clickListener = clickListener;
    }


}
