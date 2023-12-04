package org.group12.controllerView;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.group12.controller.TaskListController;
import org.group12.model.toDoSubTask.Globals;
import org.group12.model.toDoSubTask.ToDoList;
import org.group12.model.toDoSubTask.ToDoTask;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import static org.group12.view.TaskListView.*;

/**
 * This class is a controller for the ToDo Page.
 * It implements the Initializable interface.
 */
public class ToDoPageController implements Initializable {

    public VBox fixedListsVbox;
    public Label todayToDoLBL;
    public Label importantToDoLBL;
    public VBox appendableListVbox;
    public Label activeListNameLBL;
    public VBox ongoingTasksVbox;
    public VBox completedTasksVbox;
    public GridPane addNewListBtn;
    public BorderPane mainWindowBorder;

    // Controllers
    private TaskListController taskListController = new TaskListController();


    public static ArrayList<ToDoList> allLists = new ArrayList<>();
    public static ToDoList selectedList = new ToDoList();
    public static ToDoTask selectedTask=null;

    /**
     * Initializes the ToDoPageController.
     * It checks if there are any existing lists, and if not, adds the "Today" and "Important" lists.
     * Then, it refreshes the list VBox and side panel information.
     *
     * @param location The URL location.
     * @param resources The ResourceBundle resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (allLists.isEmpty()){
            allLists.add(new ToDoList(1,"Today", new ArrayList<>()));
            allLists.add(new ToDoList(2,"Important", new ArrayList<>()));
        }
        refreshAllListVBox();
        refreshSidePanelInfo();
    }

    /**
     Creates a new list object in the GUI for the given ToDoList.

     * @param newList The ToDoList for which the list object is created.
     * @return The GridPane representing the new list object.
     */

    public  GridPane createNewListObject(ToDoList newList){
        GridPane listToAppend = createListPane();
        TextField taskNameLBL = createTaskNameLabel(newList);
        Label noOfTasks = createNumberOfTaskLabel(newList);
        setTaskNameLabelEventHandler(newList, taskNameLBL, noOfTasks);
        listToAppend.getChildren().addAll(taskNameLBL, noOfTasks);
        VBox.setMargin(listToAppend, new Insets(10.0, 10.0, 0, 10.0));
        return listToAppend;
    }


    /**
     * Adds a new to-do list to the application.
     */
    public void addNewList() {
        ToDoList newList = new ToDoList(Globals.createNewRandomID(Globals.toDoListsIDs),"New List", new ArrayList<>());
        allLists.add(newList);
        GridPane listToAppend=createNewListObject(newList);

        appendableListVbox.getChildren().add(listToAppend);
    }

    /**
     * Deletes the selected to-do list from the application.
     */
    public void deleteSelectedList(){
        if(selectedList.getID()==1||selectedList.getID()==2){
            Globals.showErrorAlert("Can't delete Today or Important Lists, select a different list.");
            return;
        }
        for (ToDoList list:allLists){
            if (list.getID()==selectedList.getID()){
                allLists.remove(list);
                break;
            }
        }
        refreshAllListVBox();
        refreshSidePanelInfo();
    }

    /**
     * Refreshes the VBox that contains all the to-do lists in the UI.
     */

    public void refreshAllListVBox(){
        clearListVBoxContent();
        refreshFixedLists();
        refreshAppendableLists();
        refreshSidePanelInfo();
    }

    /**
         * Renames the given to-do list with a new name.
         *
         * @param list    The to-do list to rename.
         * @param newName The new name for the list.
         */
    public  void renameToDoList(ToDoList list, String newName) {
        if (allLists.get(findTheToDoList(list)).getID()==1||allLists.get(findTheToDoList(list)).getID()==0)return;
        allLists.get(findTheToDoList(list)).setListName(newName);
        refreshSidePanelInfo();
    }

    /**
     * Renames the given to-do task with a new name.
     *
     * @param task    The to-do task to rename.
     * @param newName The new name for the task.
     */
    void renameTask(ToDoTask task, String newName){
        allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(task)).setTaskName(newName);
    }

    /**
     * Finds the index of the given to-do list in the allLists collection.
     *
     * @param list The to-do list to find.
     * @return The index of the list in the allLists collection, or -1 if not found.
     */
    public static int findTheToDoList(ToDoList list) {
        for (ToDoList list1 : allLists) {
            if (list1.getID() == list.getID()) return allLists.indexOf(list);
        }
        return -1;
    }

    /**
     * Finds the index of the given to-do task in the selectedList's tasks.
     *
     * @param task The to-do task to find.
     * @return The index of the task in the selectedList's tasks, or -1 if not found.
     */
    public static int findTheTask(ToDoTask task) {
        selectedList=allLists.get(findTheToDoList(selectedList));
        for (ToDoTask task1 : selectedList.getTasks()) {
            if (task1.getID() == task.getID()) return selectedList.getTasks().indexOf(task1);
        }
        return -1;
    }

    public  GridPane createNewTaskObject(ToDoTask task) {

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


    /**
     * Adds a new task to the selected list.
     * Displays an error message if the selected list is "today" or "important".
     */
    public void addNewTask() {
        if (selectedList.getID()==1||selectedList.getID()==2){
            Globals.showErrorAlert("You can't add tasks to today or important directly, \ncreate a list to add tasks to \nand today and important lists will be updated accordingly.");
            return;
        }
        ToDoTask newToDoTask=new ToDoTask(Globals.createNewRandomID(Globals.toDoTasksIDs),"newTask", false,false, ZonedDateTime.now(),new ArrayList<>());
        selectedList.getTasks().add(newToDoTask);
        GridPane newTask = createNewTaskObject(newToDoTask);

        ongoingTasksVbox.getChildren().add(newTask);
        refreshAllListVBox();

    }


    /**
     * Refreshes the side panel information based on the selected list.
     * Updates the active list name label, ongoing tasks VBox, and completed tasks VBox.
     */
    public  void refreshSidePanelInfo() {
        selectedList=allLists.get(findTheToDoList(selectedList));
        activeListNameLBL.setText(selectedList.getListName());

        ongoingTasksVbox.getChildren().clear();
        completedTasksVbox.getChildren().clear();

        Comparator<ToDoTask> comparator = Comparator.comparing(ToDoTask::getTaskDeadline);
        selectedList.getTasks().sort(comparator);

        for (ToDoTask task:selectedList.getTasks()){
            if (task.getSubTasks().size()==task.getCompletedSubTasks().size() && task.getSubTasks().size()!=0)
                completedTasksVbox.getChildren().add(createNewTaskObject(task));
            else
                ongoingTasksVbox.getChildren().add(createNewTaskObject(task));
        }

    }

    // After refactoring

    private void setTaskNameLabelEventHandler(ToDoList newList, TextField taskNameLBL, Label noOfTask){
        taskNameLBL.setOnMouseClicked(event -> {
            handleTaskNameLabelClick(event, newList, taskNameLBL, noOfTask);
        });

        taskNameLBL.setOnKeyPressed(event -> {
            handleTaskNameLabelKeyPress(newList, taskNameLBL, noOfTask, event);
        });
    }

    private void handleTaskNameLabelClick(MouseEvent event, ToDoList newList, TextField taskNameLBL, Label noOfTask){
        selectedList = allLists.get(findTheToDoList(newList));
        noOfTask.setText(String.valueOf(allLists.get(findTheToDoList(selectedList)).getTasks().size()));
        refreshSidePanelInfo();
        handleTaskNameLabelEvents(taskNameLBL, event);
    }

    private void handleTaskNameLabelKeyPress(ToDoList newList, TextField taskNameLBL, Label noOfTask, KeyEvent event) {
        selectedList = newList;
        noOfTask.setText(String.valueOf(allLists.get(findTheToDoList(selectedList)).getTasks().size()));

        if (event.getCode() == KeyCode.ENTER) {
            taskNameLBL.setEditable(false);
            renameToDoList(selectedList, taskNameLBL.getText());
        }
    }

    private void setVisualComponentsInPane(ToDoTask task, ImageView imageView, ProgressIndicator progressIndicator, TextField taskNameLabel, GridPane newTaskPane, ImageView imageViewDelete, Label deadLineLabel, ImageView imageViewImportant) {
        imageView.setOnMouseClicked(event -> {
            handleImageViewClick(task, !task.getSubTasks().isEmpty(), progressIndicator);

        });

        taskNameLabel.setOnMouseClicked(event -> {
            selectedTask= task;
            handleTaskNameLabelEvents(taskNameLabel, event);
        });

        taskNameLabel.setOnKeyPressed(event -> {
            handleTaskNameLabelKeyPress(event, taskNameLabel, allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(task)));
        });

        newTaskPane.getChildren().addAll(taskNameLabel, progressIndicator, imageView, imageViewDelete, deadLineLabel, imageViewImportant);
    }

    private void handleImageViewClick(ToDoTask task, boolean task1, ProgressIndicator progressIndicator) {
        selectedTask = task;
        try {
            Globals.openNewForm("/org/group12/view/subTasks.fxml", selectedTask.getTaskName(), false);
            if (task1) {
                progressIndicator.setProgress((((double) task.getCompletedSubTasks().size() / (double) task.getSubTasks().size())));
                System.out.println((((double) task.getCompletedSubTasks().size() / (double) task.getSubTasks().size())));
            }
            refreshSidePanelInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDeleteImageViewClick(ToDoTask task, ImageView imageViewDelete) {
        imageViewDelete.setOnMouseClicked(event -> {
            for (ToDoTask task1: selectedList.getTasks()){
                if (task.getID()==task1.getID()){
                    selectedList.getTasks().remove(task1);
                    break;
                }
            }
            refreshSidePanelInfo();
            refreshAllListVBox();
        });
    }

    private static void handleImportantImageClick(ToDoTask task, ImageView imageViewImportant) {
        imageViewImportant.setOnMouseClicked(event -> {
            if (task.isImportant()){
                task.setImportant(false);
                allLists.get(1).getTasks().remove(task);
                imageViewImportant.setImage(new Image("/starUnselected.png"));
            }
            else{
                task.setImportant(true);
                allLists.get(1).getTasks().add(task);
                imageViewImportant.setImage(new Image("/star.png"));

            }
        });
    }


    private void setTaskNameEditEvent(TextField taskNameLabel, ToDoTask task){
        taskNameLabel.setOnMouseClicked(event -> {
            handleTaskNameLabelEvents(taskNameLabel, event);
        });

        taskNameLabel.setOnKeyPressed(event -> {
            handleTaskNameLabelKeyPress(event, taskNameLabel, (allLists.get(findTheToDoList(selectedList)).getTasks().get(findTheTask(task))));
        });
    }

    private void handleTaskNameLabelKeyPress(KeyEvent event, TextField taskNameLabel, ToDoTask allLists) {
        if (event.getCode() == KeyCode.ENTER) {
            taskNameLabel.setEditable(false);
            renameTask(allLists, taskNameLabel.getText());
        }
    }

    private static void handleTaskNameLabelEvents(TextField taskNameLabel, MouseEvent event) {
        if(event.getClickCount() == 2 && selectedList.getID() != 1 && selectedList.getID() != 2){
            taskNameLabel.setEditable(true);
            taskNameLabel.requestFocus();
        }
    }

    private void setViewEvent(ImageView imageView, ToDoTask task, ProgressIndicator progressIndicator){
        imageView.setOnMouseClicked(event -> {
            handleImageViewClick(task, !task.getSubTasks().isEmpty(), progressIndicator);

        });
    }

    private void refreshFixedLists(){
        for(ToDoList list:allLists){
            if (list.getID()==1||list.getID()==2){
                fixedListsVbox.getChildren().add(createNewListObject(list));
            }
        }
    }

    private void refreshAppendableLists(){
        for(ToDoList list : allLists){
            if (list.getID() != 1 &&list.getID()!= 2){
                updateListTasks(list);
                appendableListVbox.getChildren().add(createNewListObject(list));
            }
        }
    }

    private void updateListTasks(ToDoList list){
        list.getTasks().forEach(task -> {
            if(task.getTaskDeadline().toLocalDate().isEqual(ZonedDateTime.now().toLocalDate())){
                updateTodayTask(task);
            }
            if(task.isImportant()){
                updateImportantTask(task);
            }
        });
    }

    private void updateTodayTask(ToDoTask task) {
        allLists.get(0).getTasks().removeIf(task1 -> task1.getID() == task.getID());
        allLists.get(0).getTasks().add(task);
    }

    private void updateImportantTask(ToDoTask task) {
        allLists.get(1).getTasks().removeIf(task1 -> task1.getID() == task.getID());
        allLists.get(1).getTasks().add(task);
    }

    private void clearListVBoxContent() {
        selectedList=allLists.get(0);
        fixedListsVbox.getChildren().clear();
        appendableListVbox.getChildren().clear();
    }
}
