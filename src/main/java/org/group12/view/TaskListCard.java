package org.group12.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.group12.Listeners.TaskListCardClickListener;
import org.group12.Observers.ITodoObserver;
import org.group12.controller.TaskListController;
import org.group12.controllerView.ToDoWindowManager;
import org.group12.model.INameable;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITaskList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Represents a visual representation of task lists as cards in the user interface.
 * Extends AnchorPane and implements Initializable, ITaskListObserver.
 */
public class TaskListCard extends AnchorPane implements Initializable, ITodoObserver {

    // Class attributes
    private final String ID;
    private final ItemsSet items;

    // Controller
    private final TaskListController taskListController;

    // View
    private final TaskListView taskListView;
    private final ToDoWindowManager toDoWindowManager;

    // Listener
    private TaskListCardClickListener clickListener;

    // FXML components
    @FXML public Label titleLabel;
    @FXML public Label dateCreated;
    @FXML public Label NrOfBigTasks;
    @FXML public ImageView deleteTaskListBtn;

    // Constructor
    public TaskListCard(String ID, ItemsSet items){
        this.items = items;
        this.ID = ID;
        this.taskListController = TaskListController.getInstance();
        this.toDoWindowManager = new ToDoWindowManager();
        this.taskListView = new TaskListView();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskListCards.fxml"));
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
     * Sets spacing between the cards.
     */
    private void spacingBetweenCards() {
        double paddingValue = 10.0;
        VBox.setMargin(this, new Insets(paddingValue));
    }


    /**
     * Overrides the initialize method from Initializable.
     *
     * @param location  The URL location.
     * @param resources The ResourceBundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupEventHandlers();
    }

    /**
     * Initializes the controller fields and loads FXML components.
     */
    private void initializeFields() {
        this.titleLabel.setText(taskListController.getTaskListTitle(this.ID));
        this.dateCreated.setText(taskListController.getTaskListDateCreated(this.ID));
        this.NrOfBigTasks.setText(taskListController.getNrOfBigTasks(this.ID));

        if (taskListController.getTaskListByTitle("Today").getID().equals(this.ID) || taskListController.getTaskListByTitle("Important").getID().equals(this.ID)) {
            deleteTaskListBtn.setVisible(false);
        }

    }

    /**
     * Initializes the event handlers for the UI components.
     */
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

    /**
     * Deletes the task list button clicked event.
     *
     * @param event The MouseEvent for the delete button click.
     */
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

    /**
     * Handles double-clicking on the title for renaming.
     *
     * @param event The MouseEvent triggering the double click.
     */
    private void handleDoubleClick(MouseEvent event) {
        if (event.getSource() instanceof Label && event.getClickCount() == 2) {
            String newName = taskListView.getInputFromUser();

            if (newName != null) {
                taskListController.changeListTitle(this.ID, newName);
                this.titleLabel.setText(newName);
            }
        }
    }


    /**
     * Updates the displayed information on the TaskListCards.
     */
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


    /**
     * Sets the click listener for the TaskListCardClickListener.
     *
     * @param clickListener The TaskListCardClickListener.
     */
    public void setClickListener(TaskListCardClickListener clickListener) {
        this.clickListener = clickListener;
    }


}
