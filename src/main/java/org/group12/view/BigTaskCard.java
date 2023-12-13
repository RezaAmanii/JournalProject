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
import org.group12.controller.TaskListController;
import org.group12.controllerView.ToDoWindowManager;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import javafx.geometry.Insets;



import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.group12.view.TaskView.openNewForm;

public class BigTaskCard extends AnchorPane implements Initializable, ITaskListObserver {

    // Class attributes
    private final String ID;
    private final ItemsSet items;

    // Controller
    private final BigTaskController bigTaskController = BigTaskController.getInstance();
    private final ToDoWindowManager toDoWindowManager = new ToDoWindowManager();
    // Listener
    private BigTaskCardClickListener clickListener;

    // FXML components
    @FXML
    public Label titleLabel;
    @FXML
    public Label dueDateLabel;
    @FXML
    public CheckBox statusCheckBox;
    @FXML
    public ImageView favouriteImageView;
    @FXML
    public ImageView deleteTaskBtn;


    // Constructor
    public BigTaskCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bigTaskCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        double paddingValue = 10.0;
        VBox.setMargin(this, new Insets(paddingValue));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeFields();
        setupEventHandlers();
    }

    private void initializeFields(){
        this.titleLabel.setText(bigTaskController.getBigTaskTitle(this.ID));
        this.dueDateLabel.setText(bigTaskController.getBigTaskDateCreated(this.ID));
        this.statusCheckBox.setSelected(bigTaskController.getBigTaskCheckBoxStatus(this.ID));
    }


    // Event handlers
    private void setupEventHandlers(){
        titleLabel.setOnMouseClicked(this::titleClicked);
        deleteTaskBtn.setOnMouseClicked(this::deleteTaskBtnClicked);
        statusCheckBox.setOnMouseClicked(this::checkBoxToggled);
        favouriteImageView.setOnMouseClicked(this::imageViewClicked);
    }

    public void titleClicked(MouseEvent event) {
        setDoubleClickEvent();
        if(clickListener != null){
            clickListener.onBigTaskCardClicked(this);
        }
    }
    private void deleteTaskBtnClicked(MouseEvent event){
        IBigTask bigTaskToRemove = (IBigTask) items.getItem(this.ID);
        toDoWindowManager.removeTodayTask(bigTaskToRemove);
        toDoWindowManager.removeImportantTasks(bigTaskToRemove);
        bigTaskController.handleRemoveTask(bigTaskToRemove);
        update();
    }

    private void checkBoxToggled(MouseEvent event) {
        boolean isSelected = statusCheckBox.isSelected();
        bigTaskController.setBigTaskCheckBoxStatus(ID, isSelected);
        toDoWindowManager.moveToCompletedVBox(this.ID);
        update();
    }

    private void imageViewClicked(MouseEvent event) {
        boolean currentStatus = bigTaskController.getBigTaskFavouriteStatus(this.ID);
        bigTaskController.setBigTaskFavoriteStatus(this.ID, !currentStatus);
        updateFavoriteImageView(!currentStatus);
    }

    public void updateFavoriteImageView(boolean status) {
        String imagePath = status ? "star.png" : "starUnselected.png";
        Image image = new Image(imagePath);
        favouriteImageView.setImage(image);
        bigTaskController.setBigTaskFavoriteStatus(this.ID, status);
    }

    // Getters
    public String getID() {
        return ID;
    }

    // Rename methods
    private void setDoubleClickEvent() {
        setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDoubleClick();
            }
        });
    }

    private void handleDoubleClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rename Task");
        dialog.setHeaderText("Enter new name");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            bigTaskController.renameTheTask(this.ID, name);
            update();
        });
    }

    @FXML
    public String cardClicked() {
        return this.ID;
    }

    // Update method
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

    // On BigTask clicked
    public void setBigTaskClickListener(BigTaskCardClickListener clickListener) {
        this.clickListener = clickListener;
    }


}
