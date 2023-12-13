package org.group12.controllerView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.group12.Listeners.BigTaskCardClickListener;
import org.group12.Listeners.TaskListCardClickListener;
import org.group12.Observers.ITaskListObserver;
import org.group12.controller.BigTaskController;
import org.group12.controller.TaskListController;
import org.group12.model.Items;
import org.group12.model.todo.*;
import org.group12.view.BigTaskCard;
import org.group12.view.TaskListCards;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import static org.group12.view.TaskListView.*;
import static org.group12.view.TaskView.openNewForm;


public class ToDoWindowManager implements Initializable, ITaskListObserver, TaskListCardClickListener, BigTaskCardClickListener {

    // FXML components
    @FXML public VBox fixedListsVbox;
    @FXML public Label todayToDoLBL;
    @FXML public Label importantToDoLBL;
    @FXML public VBox appendableListVbox;
    @FXML public Label activeListNameLBL;
    @FXML public VBox ongoingTasksVbox;
    @FXML public GridPane addNewListBtn;
    @FXML public BorderPane mainWindowBorder;


    // Corresponding controller
    private static final TaskListController taskListController = TaskListController.getInstance();
    private static final BigTaskController bigTaskController = BigTaskController.getInstance();


    // A reference to the selected taskList card and selected bigTask card
    public static TaskListCards lastClickedTaskListCard;
    public static BigTaskCard lastClickedBigTaskCard;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkFixedList();
        taskListController.addObserver(this);
        bigTaskController.addObserver(this);
        refreshAllListVBox();
        refreshSidePanelInfo();

    }

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

    // Adding Task List
    public TaskListCards createNewListObject(ITaskList list){
        TaskListCards newTaskListCard = new TaskListCards(list.getID(), Items.getInstance());
        newTaskListCard.setClickListener(this);

        return newTaskListCard;
    }

    public void addNewList() {
        String title = getInputFromUser();
        String newListID = taskListController.handlerAddToDoList(title);
        ITaskList newList = taskListController.getTaskListByID(newListID);

        TaskListCards listToAppend = createNewListObject(newList);
        appendableListVbox.getChildren().add(listToAppend);

    }


    // Adding Big Task
    public BigTaskCard createNewTaskObject(IBigTask task) {
        BigTaskCard newBigTaskCard = new BigTaskCard(task.getID(), Items.getInstance());
        newBigTaskCard.setBigTaskClickListener(this);

        return newBigTaskCard;
    }


    public void addNewTask() {
        if (lastClickedTaskListCard != null && (taskListController.getTaskListByID(lastClickedTaskListCard.getID()).getTitle().equals("Today") || taskListController.getTaskListByID(lastClickedTaskListCard.getID()).getTitle().equals("Important"))) {
            System.out.println("Choose another list to add task");
            return;
        }

        String title = getInputFromUser();
        if (lastClickedTaskListCard != null) {
            bigTaskController.handleAddTask(title);
            update();
        }
    }

    // Removing Big Task
    public void removeTodayTask(IBigTask taskToRemove){
        for(IBigTask task : taskListController.getTaskListByTitle("Today").getBigTaskList()){
            if(task.getID().equals(taskToRemove.getID())){
                taskListController.getTaskListByTitle("Today").getBigTaskList().remove(task);
                break;
            }
        }
    }

    public void removeImportantTasks(IBigTask taskToRemove){
        for(IBigTask task : taskListController.getTaskListByTitle("Important").getBigTaskList()){
            if(task.getID().equals(taskToRemove.getID())){
                taskListController.getTaskListByTitle("Important").getBigTaskList().remove(task);
                break;
            }
        }
    }


    // Populate OngoingTasks VBox
    public void populateOngoingTasks(ITaskList taskList){
        ongoingTasksVbox.getChildren().clear();

        for(IBigTask task : taskList.getBigTaskList()){

            BigTaskCard bigTaskCard = createNewTaskObject(task);
            ongoingTasksVbox.getChildren().add((bigTaskCard));
        }
    }


    // Refreshing the lists and tasks methods
    public void refreshAllListVBox() {
        clearListVBoxContent();
        refreshFixedLists();
        refreshAppendableLists();
        refreshSidePanelInfo();

        if(appendableListVbox.getChildren().isEmpty()){
            lastClickedTaskListCard = createNewListObject(taskListController.getTaskListByTitle("Today"));
        }
    }

    private void clearListVBoxContent() {
        if (lastClickedTaskListCard == null || taskListController.getTaskListByID(lastClickedTaskListCard.getID()) == null
                || taskListController.getTaskListByID(lastClickedTaskListCard.getID()).getTitle().equals("Today")
                || taskListController.getTaskListByID(lastClickedTaskListCard.getID()).getTitle().equals("Important")) {
            lastClickedTaskListCard = createNewListObject(taskListController.getTaskListByTitle("Today"));
        }

        fixedListsVbox.getChildren().clear();
        appendableListVbox.getChildren().clear();
    }



    private void refreshFixedLists() {
        fixedListsVbox.getChildren().clear();
        for (ITaskList list : taskListController.getTasksLists()) {
            if (list.getTitle().equals("Today") || list.getTitle().equals("Important")) {
                fixedListsVbox.getChildren().add(createNewListObject(list));
            }
        }
    }

    private void refreshAppendableLists() {
        appendableListVbox.getChildren().clear();

        for (ITaskList list : taskListController.getTasksLists()) {
            if (!list.getTitle().equals("Today") && !list.getTitle().equals("Important")) {
                updateListTasks(list);
                appendableListVbox.getChildren().add(createNewListObject(list));
            }
        }
    }

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


    private void updateTodayTask(IBigTask task) {
        taskListController.getTaskListByTitle("Today").getBigTaskList().removeIf(task1 -> task1.getID().equals(task.getID()));

        taskListController.getTaskListByTitle("Today").getBigTaskList().add(task);

    }



    private void updateImportantTask(IBigTask task) {
        taskListController.getTaskListByTitle("Important").getBigTaskList().removeIf(task1 -> task1.getID().equals(task.getID()));

        taskListController.getTaskListByTitle("Important").getBigTaskList().add(task);
    }

    public void refreshSidePanelInfo() {
        if (lastClickedTaskListCard != null) {
            ITaskList taskList = taskListController.getTaskListByID(lastClickedTaskListCard.getID());
            if (taskList != null) {
                activeListNameLBL.setText(taskList.getTitle());

                ongoingTasksVbox.getChildren().clear();

                for (IBigTask task : taskList.getBigTaskList()) {
                    if (task.getSubTaskList().size() == task.getCompletedSubTasks().size() && !task.getSubTaskList().isEmpty()) {
                    } else {
                        ongoingTasksVbox.getChildren().add(createNewTaskObject(task));
                    }
                }
            } else {

                System.out.println("Task List not found!");
            }
        } else {
            System.out.println("No Task List Card selected!");
        }
    }



    @Override
    public void update() {
        clearListVBoxContent();
        refreshAllListVBox();
        refreshSidePanelInfo();
    }



    // Event Handlers for clicking on TaskList
    @Override
    public void onTaskListCardClicked(TaskListCards clickedCard) {
        lastClickedTaskListCard = clickedCard;

        ITaskList taskList = taskListController.getTaskListByID(clickedCard.getID());

        activeListNameLBL.setText(taskList.getTitle());
        populateOngoingTasks(taskList);

    }


    // Event Handlers for clicking on BigTask
    @Override
    public void onBigTaskCardClicked(BigTaskCard clickedCard) {
        lastClickedBigTaskCard = clickedCard;

        IBigTask bigtask = bigTaskController.getBigTaskByID(lastClickedBigTaskCard.getID());

        try {
            openNewForm("/org/group12/view/subTasks.fxml", bigtask.getTitle(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


