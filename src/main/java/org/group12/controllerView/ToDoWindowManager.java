package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.group12.Listeners.BigTaskCardClickListener;
import org.group12.Listeners.SubTaskCardClickListener;
import org.group12.Listeners.TaskListCardClickListener;
import org.group12.Observers.ITodoObserver;
import org.group12.controller.BigTaskController;
import org.group12.controller.TaskListController;
import org.group12.dataHandler.SaveLoad;
import org.group12.model.Items;
import org.group12.util.Globals;
import org.group12.model.todo.*;
import org.group12.view.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


/**
 * This class is responsible for managing the ToDo Window.
 */
public class ToDoWindowManager implements Initializable, ITodoObserver, TaskListCardClickListener, BigTaskCardClickListener, SubTaskCardClickListener {

    // FXML components
    @FXML public VBox fixedListsVbox;
    @FXML public Label todayToDoLBL;
    @FXML public Label importantToDoLBL;
    @FXML public VBox appendableListVbox;
    @FXML public Label activeListNameLBL;
    @FXML public VBox ongoingTasksVbox;
    @FXML public GridPane addNewListBtn;
    @FXML public BorderPane mainWindowBorder;


    // Corresponding Controller
    private static final TaskListController taskListController = TaskListController.getInstance();
    private static final BigTaskController bigTaskController = BigTaskController.getInstance();

    // Corresponding View
    private final TaskListView taskListView = new TaskListView();
    private final BigTaskView bigTaskView = new BigTaskView();


    // A reference to the selected taskList card and selected bigTask card
    public static TaskListCard lastClickedTaskListCard;
    public static BigTaskCard lastClickedBigTaskCard;



    /**
     * This method is responsible for initializing the ToDo Window.
     * @param location The location used to resolve relative paths for the root object or null if not known.
     * @param resources The resources used to localize the root object or null if not known.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkFixedList();
        taskListController.addObserver(this);
        bigTaskController.addObserver(this);
        refreshAllListVBox();
        refreshSidePanelInfo();
    }
    /**
     * Checks if fixed task lists ("Today" and "Important") exist, creates them if not present.
     */
    private static void checkFixedList() {
        boolean todayListExists = taskListController.getTaskListByTitle("Today") != null;
        boolean importantListExists = taskListController.getTaskListByTitle("Important") != null;

        if(!todayListExists){
            taskListController.handlerAddToDoList("Today");
        }
        if(!importantListExists){
            taskListController.handlerAddToDoList("Important");
        }
    }


    /**
     * Creates a new task list card object based on the provided list.
     *
     * @param list The task list to create the card for.
     * @return The created TaskListCards object.
     */

    // Adding Task List
    public TaskListCards createNewListObject(ITaskList list){
        TaskListCards newTaskListCard = new TaskListCards(list.getID(), SaveLoad.getInstance().getItemsInstance());
        newTaskListCard.setClickListener(this);

        return newTaskListCard;
    }

    /**
     * Adds a new task list based on user input.
     */
    public void addNewList() {
        String title = taskListView.getInputFromUser();
        String newListID = taskListController.handlerAddToDoList(title);
        ITaskList newList = taskListController.getTaskListByID(newListID);

        TaskListCard listToAppend = createNewListObject(newList);
        appendableListVbox.getChildren().add(listToAppend);

    }


    /**
     * Creates a new big task card object based on the provided task.
     *
     * @param task The big task to create the card for.
     * @return The created BigTaskCard object.
     */
    public BigTaskCard createNewTaskObject(IBigTask task) {
        BigTaskCard newBigTaskCard = new BigTaskCard(task.getID(), Items.getInstance());
        newBigTaskCard.setBigTaskClickListener(this);

        return newBigTaskCard;
    }

    /**
     * Adds a new big task to the selected task list if available.
     */
    public void addNewTask() {
        if (lastClickedTaskListCard != null && (taskListController.getTaskListByID(lastClickedTaskListCard.getID()).getTitle().equals("Today") || taskListController.getTaskListByID(lastClickedTaskListCard.getID()).getTitle().equals("Important"))) {
            System.out.println("Choose another list to add task");
            return;
        }

        String title = bigTaskView.getInputFromUser();
        if (lastClickedTaskListCard != null) {
            bigTaskController.handleAddTask(title);
            update();
        }
    }

    /**
     * Removes a task from the "Today" task list.
     *
     * @param taskToRemove The task to remove from the "Today" task list.
     */
    public void removeTodayTask(IBigTask taskToRemove){
        for(IBigTask task : taskListController.getTaskListByTitle("Today").getBigTaskList()){
            if(task.getID().equals(taskToRemove.getID())){
                taskListController.getTaskListByTitle("Today").getBigTaskList().remove(task);
                break;
            }
        }
    }

    /**
     * Removes a task from the "Important" task list.
     *
     * @param taskToRemove The task to remove from the "Important" task list.
     */
    public void removeImportantTasks(IBigTask taskToRemove){
        for(IBigTask task : taskListController.getTaskListByTitle("Important").getBigTaskList()){
            if(task.getID().equals(taskToRemove.getID())){
                taskListController.getTaskListByTitle("Important").getBigTaskList().remove(task);
                break;
            }
        }
    }

    /**
     * Populates the ongoing tasks VBox with the tasks from the provided task list.
     *
     * @param taskList The task list to populate the ongoing tasks VBox with.
     */
    public void populateOngoingTasks(ITaskList taskList){
        ongoingTasksVbox.getChildren().clear();

        for(IBigTask task : taskList.getBigTaskList()){

            BigTaskCard bigTaskCard = createNewTaskObject(task);
            ongoingTasksVbox.getChildren().add((bigTaskCard));
        }
    }


    /**
     * Refreshes all the lists and tasks in the ToDo Window.
     */
    public void refreshAllListVBox() {
        clearListVBoxContent();
        refreshFixedLists();
        refreshAppendableLists();
        refreshSidePanelInfo();

        if(appendableListVbox.getChildren().isEmpty()){
            lastClickedTaskListCard = createNewListObject(taskListController.getTaskListByTitle("Today"));
        }
    }

    /**
     * Clears the contents of the fixed and appendable VBox containers.
     * Checks and updates the last clicked task list card if necessary.
     */
    private void clearListVBoxContent() {
        if (lastClickedTaskListCard == null || taskListController.getTaskListByID(lastClickedTaskListCard.getID()) == null
                || taskListController.getTaskListByID(lastClickedTaskListCard.getID()).getTitle().equals("Today")
                || taskListController.getTaskListByID(lastClickedTaskListCard.getID()).getTitle().equals("Important")) {
            lastClickedTaskListCard = createNewListObject(taskListController.getTaskListByTitle("Today"));
        }

        fixedListsVbox.getChildren().clear();
        appendableListVbox.getChildren().clear();
    }


    /**
     * Refreshes the fixed lists VBox by populating it with "Today" and "Important" task lists.
     */
    private void refreshFixedLists() {
        fixedListsVbox.getChildren().clear();
        for (ITaskList list : taskListController.getTasksLists()) {
            if (list.getTitle().equals("Today") || list.getTitle().equals("Important")) {
                fixedListsVbox.getChildren().add(createNewListObject(list));
            }
        }
    }

    /**
     * Refreshes the appendable lists VBox by populating it with task lists other than "Today" and "Important".
     * Updates tasks within these lists.
     */
    private void refreshAppendableLists() {
        appendableListVbox.getChildren().clear();

        for (ITaskList list : taskListController.getTasksLists()) {
            if (!list.getTitle().equals("Today") && !list.getTitle().equals("Important")) {
                updateListTasks(list);
                appendableListVbox.getChildren().add(createNewListObject(list));
            }
        }
    }

    /**
     * Updates tasks within the provided task list based on their due dates and favorites status.
     *
     * @param list The task list containing tasks to be updated.
     */
    private void updateListTasks(ITaskList list) {
        list.getBigTaskList().forEach(task -> {
            LocalDateTime dueDate = task.getDueDate();
            if (dueDate != null) {
                LocalDate date = dueDate.toLocalDate();
                if (date.isEqual(LocalDateTime.now().toLocalDate())) {
                    updateTodayTask(task);
                }
            }
            if (task.isFavourite()) {
                updateImportantTask(task);
            }
        });
    }

    /**
     * Updates the provided task within the "Today" task list.
     *
     * @param task The task to be updated within the "Today" task list.
     */
    private void updateTodayTask(IBigTask task) {
        taskListController.getTaskListByTitle("Today").getBigTaskList().removeIf(task1 -> task1.getID().equals(task.getID()));

        taskListController.getTaskListByTitle("Today").getBigTaskList().add(task);

    }

    /**
     * Updates the provided task within the "Important" task list.
     *
     * @param task The task to be updated within the "Important" task list.
     */
    private void updateImportantTask(IBigTask task) {
        taskListController.getTaskListByTitle("Important").getBigTaskList().removeIf(task1 -> task1.getID().equals(task.getID()));

        taskListController.getTaskListByTitle("Important").getBigTaskList().add(task);
    }

    /**
     * Refreshes the side panel info by populating it with the tasks from the selected task list.
     */
    public void refreshSidePanelInfo() {
        if (lastClickedTaskListCard != null) {
            ITaskList taskList = taskListController.getTaskListByID(lastClickedTaskListCard.getID());

            if (taskList != null) {
                activeListNameLBL.setText(taskList.getTitle());
                ongoingTasksVbox.getChildren().clear();

                for (IBigTask task : taskList.getBigTaskList()) {
                    ongoingTasksVbox.getChildren().add(createNewTaskObject(task));
                }

            } else {
                System.out.println("Task List not found!");
            }
        } else {
            System.out.println("No Task List Card selected!");
        }
    }


    /**
     * Overrides the update method to synchronize and refresh various parts of the application UI/state.
     */
    @Override
    public void update() {
        clearListVBoxContent();
        refreshAllListVBox();
        refreshSidePanelInfo();
    }


    /**
     * Handles the event triggered when a task list card is clicked.
     *
     * @param clickedCard The task list card that was clicked.
     */
    @Override
    public void onTaskListCardClicked(TaskListCard clickedCard) {
        lastClickedTaskListCard = clickedCard;

        ITaskList taskList = taskListController.getTaskListByID(clickedCard.getID());

        activeListNameLBL.setText(taskList.getTitle());
        populateOngoingTasks(taskList);

    }


    /**
     * Handles the event triggered when a big task card is clicked.
     *
     * @param clickedCard The big task card that was clicked.
     */
    @Override
    public void onBigTaskCardClicked(BigTaskCard clickedCard) {
        lastClickedBigTaskCard = clickedCard;

        IBigTask bigTask = bigTaskController.getBigTaskByID(lastClickedBigTaskCard.getID());

        try {
            Globals.openNewForm("/org/group12/view/subTasks.fxml", bigTask.getTitle(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Handles the event triggered when a subtask card is clicked.
     *
     * @param subTaskCard The subtask card that was clicked.
     */
    @Override
    public void onSubTaskCardClicked(SubTaskCard subTaskCard) {

    }
}


