package org.group12.controllerView;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.group12.Listeners.TaskListCardClickListener;
import org.group12.Observers.ITaskListObserver;
import org.group12.controller.TaskListController;
import org.group12.model.Items;
import org.group12.model.toDoSubTask.Globals;
import org.group12.model.todo.*;
import org.group12.view.BigTaskCard;
import org.group12.view.TaskListCard;
import org.group12.view.taskListCards;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.ResourceBundle;

import static org.group12.view.TaskListView.*;


public class ToDoWindowManager implements Initializable, ITaskListObserver, TaskListCardClickListener {

    // FXML components
    public VBox fixedListsVbox;
    public Label todayToDoLBL;
    public Label importantToDoLBL;
    public VBox appendableListVbox;
    public Label activeListNameLBL;
    public VBox ongoingTasksVbox;
    public VBox completedTasksVbox;
    public GridPane addNewListBtn;
    public BorderPane mainWindowBorder;


    // Corresponding controller
    public static TaskListController taskListController = TaskListController.getInstance();
    public taskListCards selectedTaskListCard = null;

    // A reference to the selectedList and selectedBigTask
    public static ITaskList selectedList = null;
    public static IBigTask selectedTask = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (taskListController.fetchAllTaskLists().isEmpty()) {
            taskListController.handlerAddToDoList("Today");
            taskListController.handlerAddToDoList("Important");
        }
        taskListController.addObserver(this);
        refreshAllListVBox();
        refreshSidePanelInfo();
    }

    public taskListCards createNewListObject(ITaskList list){
        taskListCards newTaskListCard = new taskListCards(list.getID(), Items.getInstance());
        newTaskListCard.setClickListener(this);


        return newTaskListCard;
    }

/*
    public GridPane createNewListObject(ITaskList newList) {
        GridPane listToAppend = createListPane();
        TextField taskNameLBL = createTaskNameLabel(newList);
        Label noOfTasks = createNumberOfTaskLabel(newList);
        setTaskNameLabelEventHandler(newList, taskNameLBL, noOfTasks);
        listToAppend.getChildren().addAll(taskNameLBL, noOfTasks);
        VBox.setMargin(listToAppend, new Insets(10.0, 10.0, 0, 10.0));
        return listToAppend;
    }

 */


    public static String retriveTaskListID(ITaskList taskList) {
        for (ITaskList list : taskListController.fetchAllTaskLists()) {
            if (list.equals(taskList)) {
                return list.getID();
            }
        }
        return null;
    }


    public static String retriveBigTaskID(IBigTask bigTask) {
        for (IBigTask task : taskListController.fetchAllBigTasks(retriveTaskListID(selectedList))) {
            if (task.equals(bigTask)) {
                return task.getID();
            }

        }
        return null;
    }


    public void addNewList() {
        String title = getInputFromUser();
        String newListID = taskListController.handlerAddToDoList(title);
        ITaskList newList = taskListController.getTaskListByID(newListID);

        taskListCards listToAppend = createNewListObject(newList);
        appendableListVbox.getChildren().add(listToAppend);
    }




    public void refreshAllListVBox() {
        clearListVBoxContent();
        refreshFixedLists();
        refreshAppendableLists();
        refreshSidePanelInfo();
    }


    public void renameToDoList(ITaskList list, String newName) {
        if (taskListController.getTaskListByTitle("Today").equals(list) || taskListController.getTaskListByTitle("Important").equals(list)) {
            System.out.println("Choose another list to rename");
        } else {
            taskListController.changeListTitle(list.getID(), newName);
            refreshSidePanelInfo();

            if (selectedList.equals(list)) {
                activeListNameLBL.setText(newName);
            }
        }
    }


    void renameTask(IBigTask task, String newName) {
        taskListController.getTaskByID(retriveBigTaskID(task)).setTitle(newName);
    }

    public GridPane createNewTaskObject(IBigTask task) {



        GridPane newTaskPane = createTaskPane();
        Label deadLineLabel = createDeadlineLabel(task);
        ImageView imageViewImportant = createImportantImageView(task);
        ImageView imageViewDelete = createDeleteImageView(task);
        ImageView imageView = createViewImageView(task);
        TextField taskNameLabel = createTaskNameTextField(task);
        ProgressIndicator progressIndicator = createProgressIndicator(task);

        setTaskNameEditEvent(taskNameLabel, task);
        setViewEvent(imageView, task, progressIndicator);

        handleImportantImageClick(task, imageViewImportant);
        handleDeleteImageViewClick(task, imageViewDelete);

        configureGridPanePositions(taskNameLabel, progressIndicator, imageView);
        setVisualComponentsInPane(task, imageView, progressIndicator, taskNameLabel, newTaskPane, imageViewDelete, deadLineLabel, imageViewImportant);

        VBox.setMargin(newTaskPane, new Insets(10.0, 10.0, 0, 10.0));
        return newTaskPane;
    }

    public void addNewTask() {
        if (selectedList.getTitle().equals(taskListController.getTaskListByTitle("Today")) || selectedList.getTitle().equals(taskListController.getTaskListByTitle("Important"))) {
            Globals.showErrorAlert("You can't add tasks to today or important directly, \ncreate a list to add tasks to \nand today and important lists will be updated accordingly.");
            return;
        }
        String title = getInputFromUser();
        String taskID = taskListController.getTaskListByID(selectedList.getID()).addBigTask(title);
        IBigTask task = taskListController.getBigTaskByID(taskID);

        BigTaskCard bigTaskCard = new BigTaskCard(task.getID(), Items.getInstance());

        //GridPane newTask = createNewTaskObject(task);
        ongoingTasksVbox.getChildren().add(bigTaskCard);

    }

    public void refreshSidePanelInfo() {
        selectedList = taskListController.getTaskListByID(retriveTaskListID(selectedList));
        activeListNameLBL.setText(selectedList.getTitle());

        ongoingTasksVbox.getChildren().clear();
        completedTasksVbox.getChildren().clear();

        Comparator<IBigTask> comparator = Comparator.comparing(IBigTask::getDueDate);
        selectedList.getBigTaskList().sort(comparator);

        for (IBigTask task : selectedList.getBigTaskList()) {
            if (task.getSubTaskList().size() == task.getCompletedSubTasks().size() && !task.getSubTaskList().isEmpty())
                completedTasksVbox.getChildren().add(createNewTaskObject(task));
            else
                ongoingTasksVbox.getChildren().add(createNewTaskObject(task));
        }

    }


    private void setTaskNameLabelEventHandler(ITaskList newList, TextField taskNameLBL, Label noOfTask) {
        taskNameLBL.setOnMouseClicked(event -> {
            handleTaskNameLabelClick(event, newList, taskNameLBL, noOfTask);
        });

        taskNameLBL.setOnKeyPressed(event -> {
            handleTaskNameLabelKeyPress(newList, taskNameLBL, noOfTask, event);
        });
    }

    private void handleTaskNameLabelClick(MouseEvent event, ITaskList newList, TextField taskNameLBL, Label noOfTask) {
        selectedList = taskListController.getTaskListByID(retriveTaskListID(newList));
        //selectedList = allLists.get(findTheToDoList(newList));
        noOfTask.setText(String.valueOf(selectedList.getBigTaskList().size()));
        refreshSidePanelInfo();
        handleTaskNameLabelEvents(taskNameLBL, event);
    }

    private void handleTaskNameLabelKeyPress(ITaskList newList, TextField taskNameLBL, Label noOfTask, KeyEvent event) {
        selectedList = newList;
        noOfTask.setText(String.valueOf(selectedList.getBigTaskList().size()));

        if (event.getCode() == KeyCode.ENTER) {
            taskNameLBL.setEditable(false);
            renameToDoList(selectedList, taskNameLBL.getText());
        }
    }

    private void setVisualComponentsInPane(IBigTask task, ImageView imageView, ProgressIndicator progressIndicator, TextField taskNameLabel, GridPane newTaskPane, ImageView imageViewDelete, Label deadLineLabel, ImageView imageViewImportant) {
        imageView.setOnMouseClicked(event -> {
            handleImageViewClick(task, !task.getSubTaskList().isEmpty(), progressIndicator);

        });

        taskNameLabel.setOnMouseClicked(event -> {
            selectedTask = task;
            handleTaskNameLabelEvents(taskNameLabel, event);
        });

        taskNameLabel.setOnKeyPressed(event -> {
            handleTaskNameLabelKeyPress(event, taskNameLabel, taskListController.getBigTaskByID(retriveBigTaskID(task)));
        });

        newTaskPane.getChildren().addAll(taskNameLabel, progressIndicator, imageView, imageViewDelete, deadLineLabel, imageViewImportant);
    }

    private void handleImageViewClick(IBigTask task, boolean task1, ProgressIndicator progressIndicator) {
        selectedTask = task;
        try {
            Globals.openNewForm("/org/group12/view/subTasks.fxml", selectedTask.getTitle(), false);
            if (task1) {
                progressIndicator.setProgress((((double) task.getCompletedSubTasks().size() / (double) task.getSubTaskList().size())));
                System.out.println((((double) task.getCompletedSubTasks().size() / (double) task.getSubTaskList().size())));
            }
            refreshSidePanelInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDeleteImageViewClick(IBigTask task, ImageView imageViewDelete) {
        imageViewDelete.setOnMouseClicked(event -> {
            for (IBigTask task1 : selectedList.getBigTaskList()) {
                if (task.getID().equals(task1.getID())) {
                    selectedList.getBigTaskList().remove(task1);
                    break;
                }
            }
            refreshSidePanelInfo();
            refreshAllListVBox();
        });
    }

    private static void handleImportantImageClick(IBigTask task, ImageView imageViewImportant) {
        imageViewImportant.setOnMouseClicked(event -> {
            if (task.isFavourite()) {
                task.setFavourite(false);
                taskListController.getTaskListByTitle("Important").getBigTaskList().remove(task);
                imageViewImportant.setImage(new Image("/starUnselected.png"));
            } else {
                task.setFavourite(true);
                taskListController.getTaskListByTitle("Important").getBigTaskList().add(task);
                imageViewImportant.setImage(new Image("/star.png"));

            }

        });
    }


    private void setTaskNameEditEvent(TextField taskNameLabel, IBigTask task) {
        taskNameLabel.setOnMouseClicked(event -> {
            handleTaskNameLabelEvents(taskNameLabel, event);
        });

        taskNameLabel.setOnKeyPressed(event -> {
            handleTaskNameLabelKeyPress(event, taskNameLabel, (taskListController.getBigTaskByID(retriveBigTaskID(task))));
        });
    }

    private void handleTaskNameLabelKeyPress(KeyEvent event, TextField taskNameLabel, IBigTask task) {
        if (event.getCode() == KeyCode.ENTER) {
            taskNameLabel.setEditable(false);
            renameTask(task, taskNameLabel.getText());
        }
    }

    private static void handleTaskNameLabelEvents(TextField taskNameLabel, MouseEvent event) {
        if (event.getClickCount() == 2 && !selectedList.getTitle().equals(taskListController.getTaskListByTitle("Today")) && !selectedList.getTitle().equals(taskListController.getTaskListByTitle("Important"))) {
            taskNameLabel.setEditable(true);
            taskNameLabel.requestFocus();
        }
    }

    private void setViewEvent(ImageView imageView, IBigTask task, ProgressIndicator progressIndicator) {
        imageView.setOnMouseClicked(event -> {
            handleImageViewClick(task, !task.getSubTaskList().isEmpty(), progressIndicator);

        });
    }

    private void refreshFixedLists() {
        for (ITaskList list : taskListController.fetchAllTaskLists()) {
            if (list.getTitle().equals("Today") || list.getTitle().equals("Important")) {
                fixedListsVbox.getChildren().add(createNewListObject(list));
            }
        }
    }


    private void refreshAppendableLists() {
        for (ITaskList list : taskListController.fetchAllTaskLists()) {
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
        taskListController.getTaskListByTitle("Today").addBigTask(task.getTitle());
    }

    private void updateImportantTask(IBigTask task) {
        taskListController.getTaskListByTitle("Important").getBigTaskList().removeIf(task1 -> task1.getID().equals(task.getID()));
        taskListController.getTaskListByTitle("Important").addBigTask(task.getTitle());
    }


    private void clearListVBoxContent() {
        selectedList = taskListController.getTaskListByTitle("Today");
        fixedListsVbox.getChildren().clear();
        appendableListVbox.getChildren().clear();

    }

    @Override
    public void update() {
        refreshAllListVBox();
        refreshSidePanelInfo();

    }

    @Override
    public void onTaskListCardClicked(taskListCards clickedCard) {
        activeListNameLBL.setText(taskListController.getTaskListByID(clickedCard.getID()).getTitle());
    }
}