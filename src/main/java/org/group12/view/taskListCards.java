package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.group12.Observers.ITaskListObserver;
import org.group12.controller.BigTaskController;
import org.group12.controller.TaskListController;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITaskList;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class taskListCards extends AnchorPane implements Initializable, ITaskListObserver {

    // Class attributes
    private final String ID;
    private final ItemsSet items;

    // Controller
    private final TaskListController taskListController;

    // FXML components
    @FXML
    private Label titleLabel;
    @FXML
    private Label dateCreated;
    @FXML
    private Label NrOfBigTasks;
    @FXML
    private ImageView deleteTaskListBtn;



    // Constructor
    public taskListCards(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.taskListController = TaskListController.getInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskListCards.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Make space between each cards
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
        update();
    }

    private void initializeFields() {
        this.titleLabel.setText(taskListController.getTaskListTitle(this.ID));
        this.dateCreated.setText(taskListController.getTaskListDateCreated(this.ID));
        this.NrOfBigTasks.setText(taskListController.getNrOfBigTasks(this.ID));

        if (taskListController.getTaskListByTitle("Today").getID().equals(this.ID) || taskListController.getTaskListByTitle("Important").getID().equals(this.ID)) {
            deleteTaskListBtn.setVisible(false);

        }

    }

    private void setupEventHandlers(){
        titleLabel.setOnMouseClicked(event -> {
            titleClicked();
        });

    }


    private void setDoubleClickEvent() {
        setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDoubleClick();
            }
        });
    }


    @FXML
    public void titleClicked() {
        setDoubleClickEvent();

    }


    public void update() {

        try{
            INameable item = items.getItem(this.ID);

            if(item instanceof ITaskList){
                ITaskList taskList = (ITaskList) item;

                this.titleLabel.setText(taskListController.getTaskListTitle(taskList.getID()));
                this.dateCreated.setText(taskListController.getTaskListDateCreated(taskList.getID()));
                this.NrOfBigTasks.setText(taskListController.getNrOfBigTasks(taskList.getID()));

            } else{
                System.out.println("Item with ID " + ID + " is not a ITaskList!");
            }
        } catch (ClassCastException error){
            System.out.println("Item with ID " + ID + " is not a ITaskList!");
        }
    }

    public String getID() {
        return ID;
    }


    private void handleDoubleClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rename Task");
        dialog.setHeaderText("Enter new name");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            taskListController.renameTaskList(this.ID, name);
        });
    }

    // Delete taskList
    @FXML
    private void deleteTaskListBtnClicked(){
        taskListController.handlerRemoveToDoList(taskListController.getTaskListByID(this.ID));
        update();
    }


}
