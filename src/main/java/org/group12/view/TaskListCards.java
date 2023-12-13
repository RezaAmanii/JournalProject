package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.group12.Listeners.TaskListCardClickListener;
import org.group12.Observers.ITaskListObserver;
import org.group12.controller.TaskListController;
import org.group12.controllerView.ToDoWindowManager;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITaskList;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TaskListCards extends AnchorPane implements Initializable, ITaskListObserver {

    // Class attributes
    private final String ID;
    private final ItemsSet items;

    // Controller
    private final TaskListController taskListController;

    private final ToDoWindowManager toDoWindowManager;

    // Listener
    private TaskListCardClickListener clickListener;

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
    public TaskListCards(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.taskListController = TaskListController.getInstance();
        this.toDoWindowManager = new ToDoWindowManager();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskListCards.fxml"));
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

    // Event handlers
    private void setupEventHandlers(){
        titleLabel.setOnMouseClicked(this::titleClicked);
        deleteTaskListBtn.setOnMouseClicked(this::deleteTaskListBtnClicked);
    }
    public void titleClicked(MouseEvent event) {
        if (clickListener != null) {
            clickListener.onTaskListCardClicked(this);
        }
        handleDoubleClick(event);
    }
    private void deleteTaskListBtnClicked(MouseEvent event){
        for(IBigTask tasks : taskListController.getTaskListByID(this.ID).getBigTaskList()){
            toDoWindowManager.removeTodayTask(tasks);
            toDoWindowManager.removeImportantTasks(tasks);
        }
        taskListController.handlerRemoveToDoList(taskListController.getTaskListByID(this.ID));
        update();
    }

    // Getters
    public String getID() {return ID;}

    // Rename methods
    private void handleDoubleClick(MouseEvent event) {
        if (event.getSource() instanceof Label && event.getClickCount() == 2) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Rename Task");
            dialog.setHeaderText("Enter new name");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {
                taskListController.changeListTitle(this.ID, name);
            });
        }
    }


    // Update method
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


    // Task List card clicked
    public void setClickListener(TaskListCardClickListener clickListener) {
        this.clickListener = clickListener;
    }


}
