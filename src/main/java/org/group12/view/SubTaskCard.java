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
import org.group12.controller.BigTaskController;
import org.group12.controller.TaskController;
import org.group12.controllerView.SubTaskWindowManager;
import org.group12.controllerView.ToDoWindowManager;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.ITask;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SubTaskCard extends AnchorPane implements Initializable, ITaskListObserver {

    // Class attributes
    private final String ID;
    private final ItemsSet items;
    private final SubTaskWindowManager subTaskWindowManager;

    // Controller
    private final TaskController taskController;

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
        this.subTaskWindowManager = new SubTaskWindowManager();
        this.taskController = TaskController.getInstance();

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

    private void spacingBetweenCards() {
        double paddingValue = 10.0;
        VBox.setMargin(this, new Insets(paddingValue));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeFields();
        setupEventHandlers();
    }

    private void initializeFields(){
        this.titleLabel.setText(taskController.getSubTaskTitle(this.ID));
        this.dateCreatedLabel.setText(taskController.getSubTaskDateCreated(this.ID));
        this.statusCheckBox.setSelected(taskController.getSubTaskStatus(this.ID));
    }


    // Event handlers
    private void setupEventHandlers(){
        titleLabel.setOnMouseClicked(this::titleClicked);
        deleteSubTaskBtn.setOnMouseClicked(this::deleteSubTaskBtnClicked);
        statusCheckBox.setOnMouseClicked(this::checkBoxToggled);
    }

    public void titleClicked(MouseEvent event) {
        setDoubleClickEvent();
        if(clickListener != null){
            clickListener.onSubTaskCardClicked(this);
        }
    }
    private void deleteSubTaskBtnClicked(MouseEvent event){
        ITask subTaskToRemove = taskController.getSubTaskByID(this.ID);
        taskController.handleRemoveSubTask(subTaskToRemove);
        update();
    }

    private void checkBoxToggled(MouseEvent event) {
        boolean isSelected = statusCheckBox.isSelected();
        taskController.setSubTaskStatus(ID, isSelected);
        update();
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
            taskController.renameSubTask(this.ID, name);
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

            if(item instanceof ITask){
                ITask subTask = (ITask) item;

                this.titleLabel.setText(taskController.getSubTaskTitle(subTask.getID()));
                this.dateCreatedLabel.setText(taskController.getSubTaskDateCreated(subTask.getID()));
                this.statusCheckBox.setSelected(taskController.getSubTaskStatus(subTask.getID()));


            } else{
                System.out.println("Item with ID " + ID + " is not a ITask!");
            }
        } catch (ClassCastException error){
            System.out.println("Item with ID " + ID + " is not a ITask!");
        }
    }

    // On SubTask clicked
    public void setSubTaskCardListener(SubTaskCardClickListener clickListener) {
        this.clickListener = clickListener;
    }


}
