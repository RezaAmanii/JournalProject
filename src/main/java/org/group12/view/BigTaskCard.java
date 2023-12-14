package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.group12.Listeners.BigTaskCardClickListener;
import org.group12.Observers.ITaskListObserver;
import org.group12.controller.BigTaskController;
import org.group12.controllerView.ToDoWindowManager;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import javafx.geometry.Insets;
import org.group12.model.todo.ITask;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Represents a graphical card displaying detailed information about a significant task (IBigTask).
 * Allows user interactions such as renaming tasks, toggling checkboxes, and marking tasks as favorites or deleting them.
 */
public class BigTaskCard extends AnchorPane implements Initializable, ITaskListObserver {

    // Class attributes
    private final String ID;
    private final ItemsSet items;

    // Controller
    private final BigTaskController bigTaskController;
    private final ToDoWindowManager toDoWindowManager;

    // View
    private final BigTaskView bigTaskView;

    // Listener
    private BigTaskCardClickListener clickListener;

    // FXML components
    @FXML public Label titleLabel;
    @FXML public Label dueDateLabel;
    @FXML public CheckBox statusCheckBox;
    @FXML public ImageView favouriteImageView;
    @FXML public ImageView deleteTaskBtn;


    /**
     * Constructor for initializing a BigTaskCard.
     *
     * @param ID    The unique identifier for the task card.
     * @param items An ItemsSet object containing items for the task card.
     */
    public BigTaskCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.bigTaskController = BigTaskController.getInstance();
        this.toDoWindowManager = new ToDoWindowManager();
        this.bigTaskView = new BigTaskView();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bigTaskCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initializeFields();
        spacingBetweenCards();
    }

    /**
     * Defines spacing around the BigTaskCard within a VBox.
     */
    private void spacingBetweenCards() {
        double paddingValue = 10.0;
        VBox.setMargin(this, new Insets(paddingValue));
    }


    /**
     * Initializes the BigTaskCard fields with data.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupEventHandlers();
    }

    /**
     * Sets up initial data and event handlers for the BigTaskCard fields.
     */
    private void initializeFields(){
        this.titleLabel.setText(bigTaskController.getBigTaskTitle(this.ID));
        this.dueDateLabel.setText(bigTaskController.getBigTaskDateCreated(this.ID));
        this.statusCheckBox.setSelected(bigTaskController.getBigTaskCheckBoxStatus(this.ID));
    }


    /**
     * Sets up event handlers for user interactions on the BigTaskCard components.
     */
    private void setupEventHandlers(){
        titleLabel.setOnMouseClicked(this::titleClicked);
        deleteTaskBtn.setOnMouseClicked(this::deleteTaskBtnClicked);
        statusCheckBox.setOnMouseClicked(this::checkBoxToggled);
        favouriteImageView.setOnMouseClicked(this::imageViewClicked);
    }

    /**
     * Handles the event when the title label is clicked.
     *
     * @param event The mouse click event.
     */
    public void titleClicked(MouseEvent event) {
        setDoubleClickEvent();
        if(clickListener != null){
            clickListener.onBigTaskCardClicked(this);
        }
    }

    /**
     * Handles the event when the delete button is clicked.
     *
     * @param event The mouse click event.
     */
    private void deleteTaskBtnClicked(MouseEvent event){
        IBigTask bigTaskToRemove = (IBigTask) items.getItem(this.ID);
        toDoWindowManager.removeTodayTask(bigTaskToRemove);
        toDoWindowManager.removeImportantTasks(bigTaskToRemove);
        bigTaskController.handleRemoveTask(bigTaskToRemove);
        update();
    }

    /**
     * Handles the event when the status checkbox is toggled.
     *
     * @param event The mouse click event.
     */
    private void checkBoxToggled(MouseEvent event) {
        boolean isSelected = statusCheckBox.isSelected();
        bigTaskController.setBigTaskCheckBoxStatus(ID, isSelected);

        toggleCheckBoxForSubTasks(isSelected);
        update();
    }

    /**
     * Toggles the checkbox status for subtasks of a BigTask.
     *
     * @param isSelected The status indicating whether the main task checkbox is selected or not.
     */
    private void toggleCheckBoxForSubTasks(boolean isSelected) {
        IBigTask bigTask = bigTaskController.getBigTaskByID(ID);
        if (bigTask != null) {
            boolean allSubtasksCompleted = true;

            for (ITask subTask : bigTask.getSubTaskList()) {
                subTask.setCompleted(isSelected);

                if (!subTask.getStatus()) {
                    allSubtasksCompleted = false;
                }
            }
            bigTaskController.setBigTaskCheckBoxStatus(ID, allSubtasksCompleted);
        }
    }


    /**
     * Handles the event when the favorite image view is clicked.
     *
     * @param event The mouse click event.
     */
    private void imageViewClicked(MouseEvent event) {
        boolean currentStatus = bigTaskController.getBigTaskFavouriteStatus(this.ID);
        bigTaskController.setBigTaskFavoriteStatus(this.ID, !currentStatus);
        updateFavoriteImageView(!currentStatus);
    }

    /**
     * Updates the displayed favorite status image and sets the favorite status of the task.
     *
     * @param status The status indicating whether the task is a favorite or not.
     */
    public void updateFavoriteImageView(boolean status) {
        String imagePath = status ? "star.png" : "starUnselected.png";
        Image image = new Image(imagePath);
        favouriteImageView.setImage(image);
        bigTaskController.setBigTaskFavoriteStatus(this.ID, status);
    }

    /**
     * Retrieves the unique identifier (ID) of the BigTaskCard.
     *
     * @return The ID of the BigTaskCard.
     */
    public String getID() {
        return ID;
    }


    /**
     * Sets up a double click event for the BigTaskCard.
     * Triggers the handleDoubleClick method on a double click event.
     */
    private void setDoubleClickEvent() {
        setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDoubleClick();
            }
        });
    }

    /**
     * Handles the double click event on the BigTaskCard.
     * Prompts the user to enter a new name for the task.
     * Renames the task using the entered name and updates the card.
     */
    private void handleDoubleClick() {
        String newName = bigTaskView.getInputFromUser();
        if(!newName.isEmpty()){
            bigTaskController.renameTheTask(this.ID, newName);
            this.titleLabel.setText(newName);
        }
    }


    /**
     * Updates the displayed information on the BigTaskCard.
     */
    @Override
    public void update() {

        try{
            INameable item = items.getItem(this.ID);

            if(item instanceof IBigTask){
                IBigTask bigTask = (IBigTask) item;

                this.titleLabel.setText(bigTaskController.getBigTaskTitle(bigTask.getID()));
                this.dueDateLabel.setText(bigTaskController.getBigTaskDateCreated(bigTask.getID()));
                this.statusCheckBox.setSelected(bigTaskController.getBigTaskCheckBoxStatus(bigTask.getID()));
                boolean isFavourite = bigTaskController.getBigTaskFavouriteStatus(bigTask.getID());
                updateFavoriteImageView(isFavourite);

            } else{
                System.out.println("Item with ID " + ID + " is not a IBigTask!");
            }
        } catch (ClassCastException error){
            System.out.println("Item with ID " + ID + " is not a IBigTask!");
        }
    }


    /**
     * Sets a listener for the click event on the BigTaskCard.
     *
     * @param clickListener The listener for BigTaskCard click events.
     */
    public void setBigTaskClickListener(BigTaskCardClickListener clickListener) {
        this.clickListener = clickListener;
    }


}
