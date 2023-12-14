package org.group12.controller;

import org.group12.Observers.IObservable;
import org.group12.Observers.IPlanITObserver;
import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages and controls operations related to task lists within the application.
 * Implements IObservable for observing changes.
 */
public class TaskListController implements IController, IObservable {

    // Attributes
    private final ItemsSet items;
    private final TodoCollection todoCollection;
    private static TaskListController instance;
    private final List<IPlanITObserver> observers = new ArrayList<>();

    // Constructor
    private TaskListController() {
        this.items = Items.getInstance();
        this.todoCollection = Container.getInstance().getTodoCollection();

    }

    /**
     * Gets the singleton instance of TaskListController.
     * @return The singleton instance of TaskListController.
     */
    public static TaskListController getInstance() {
        if (instance == null) {
            instance = new TaskListController();
        }
        return instance;
    }


    /**
     * Handles the addition of a new todo list.
     * @param title The title of the new todo list.
     * @return The ID of the newly added todo list.
     */
    public String handlerAddToDoList(String title) {
        return todoCollection.addTaskList(title);
    }

    /**
     * Handles the removal of a todo list.
     * @param taskList The todo list to remove.
     */
    public void handlerRemoveToDoList(ITaskList taskList) {
        todoCollection.removeTaskList(taskList);
        notifyObservers();
    }

    /**
     * Changes the title of a task list.
     * @param TaskListID The identifier of the task list.
     * @param newTitle The new title to assign to the task list.
     */
    public void changeListTitle(String TaskListID, String newTitle) {
        ITaskList taskList = (ITaskList) items.getItem(TaskListID);
        if (taskList != null){
            if (SubTaskController.stringValidation(newTitle)) {
                taskList.setTitle(newTitle);
            } else {
                System.out.println("Not a String");
            }

        } else {
            System.out.println("TaskList not found");
        }
        notifyObservers();
    }

    /**
     * Retrieves a task list by its unique identifier.
     * @param taskListID The identifier of the task list.
     * @return The task list with the given ID if found; otherwise, null.
     */
    public ITaskList getTaskListByID(String taskListID){
        return (ITaskList) items.getItem(taskListID);
    }

    /**
     * Retrieves a task list by its title.
     * @param title The title of the task list to retrieve.
     * @return The task list with the given title if found; otherwise, null.
     */
    public ITaskList getTaskListByTitle(String title){
        for(ITaskList taskList : todoCollection.getTaskList()){
            if(taskList.getTitle().equals(title)){
                return taskList;
            }
        }
        return null;
    }

    /**
     * Retrieves the number of big tasks within a task list.
     * @param taskListID The identifier of the task list.
     * @return The number of big tasks in the task list as a String.
     */
    public String getNrOfBigTasks(String taskListID){
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return String.valueOf(taskList.getBigTaskList().size());
    }

    /**
     * Retrieves the creation date of a task list by its identifier.
     * @param taskListID The identifier of the task list.
     * @return The creation date of the task list formatted as a string.
     */
    public String getTaskListDateCreated(String taskListID){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
        ITaskList taskList = (ITaskList) items.getItem(taskListID);
        return taskList.getDateCreated().format(formatter);
    }

    /**
     * Retrieves the title of a task list by its identifier.
     * @param taskListID The identifier of the task list.
     * @return The title of the task list.
     */
    public String getTaskListTitle(String taskListID){
        return items.getItem(taskListID).getTitle();
    }

    /**
     * Retrieves all task lists.
     * @return An ArrayList containing all task lists.
     */
    public ArrayList<ITaskList> getTasksLists(){
        return todoCollection.getTaskList();
    }




    // Observer methods
    @Override
    public void addObserver(IPlanITObserver observer) {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(IPlanITObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IPlanITObserver observer : observers){
            observer.update();
        }

    }
}
