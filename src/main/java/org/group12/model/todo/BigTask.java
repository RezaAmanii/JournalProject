package org.group12.model.todo;

import org.group12.model.ItemsSet;
import org.group12.model.todo.factories.TaskFactory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a big task that contains subtasks.
 */
public class BigTask implements IBigTask, Serializable {
    private String description;
    private LocalDateTime dueDate;
    private boolean isFavourite;
    private final ArrayList<ITask> subTaskList;
    private final Task modelTask;
    private final TaskFactory taskFactory;
    private final ItemsSet items;
    private ArrayList<ITask> compeletedSubTasks = new ArrayList<>();

    /**
     * Constructs a BigTask object with the given title and ID.
     *
     * @param title The title of the big task.
     * @param ID    The ID of the big task.
     */
    public BigTask(String title, String ID, ItemsSet items) {
        this.subTaskList = new ArrayList<>();
        this.taskFactory = TaskFactory.getInstance();
        modelTask = new Task("model", ID);
        modelTask.setTitle(title);
        this.items = items;
        this.dueDate = LocalDateTime.now();
        System.out.println(this.getID());
    }

    /**
     * Sets the title of the big task.
     *
     * @param title The title to be set.
     */
    @Override
    public void setTitle(String title) {
        this.modelTask.setTitle(title);
    }

    /**
     * Gets the title of the big task.
     *
     * @return The title of the big task.
     */
    @Override
    public String getTitle() {
        return modelTask.getTitle();
    }

    /**
     * Gets the due date of the big task.
     *
     * @return The due date of the big task.
     */
    @Override
    public LocalDateTime getDueDate() {
        return dueDate;
    }


    /**
     * Sets the due date of the big task.
     *
     * @param date The due date to be set.
     */
    @Override
    public void setDueDate(LocalDateTime date) {
        this.dueDate = date;
    }

    /**
     * Gets the status of isFavourite of the big task.
     *
     * @return The status of isFavourite of the big task.
     */
    @Override
    public boolean isFavourite() {
        return isFavourite;
    }

    /**
     * Sets the value of isFavourite of the big task.
     *
     * @param status The value to be set for isFavourite.
     */
    @Override
    public void setFavourite(boolean status) {
        this.isFavourite = status;
    }

    /**
     * Gets the ID of the big task.
     *
     * @return The ID of the big task.
     */
    @Override
    public String getID() {
        return modelTask.getID();
    }

    /**
     * Gets the status of the big task.
     *
     * @return The status of the big task.
     */
    @Override
    public boolean getStatus() {
        return modelTask.getStatus();
    }

    /**
     * Sets the completion status of the big task.
     *
     * @param status The completion status to be set.
     */
    @Override
    public void setCompleted(boolean status) {
        modelTask.setCompleted(status);
    }

    /**
     * Gets the date created of the big task.
     *
     * @return The date created of the big task.
     */
    @Override
    public LocalDateTime getDateCreated() {
        return modelTask.getDateCreated();
    }

    /**
     * Gets the description of the big task.
     *
     * @return The description of the big task.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the big task.
     *
     * @param desc The description to be set.
     */
    @Override
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Adds a subtask to the big task.
     *
     * @param title The title of the subtask to be added.
     */
    @Override
    public String addSubTask(String title) {
        ITask newTask = taskFactory.createTask(title);
        subTaskList.add(newTask);
        items.addItem(newTask);
        return newTask.getID();
    }

    /**
     * Removes a subtask from the big task.
     *
     * @param subTask The ID of the subtask to be removed.
     */
    @Override
    public void removeSubTask(ITask subTask) {
        subTaskList.remove(subTask);
        items.removeItem(subTask.getID());
    }

    /**
     * Gets the list of completed subtasks in the big task.
     *
     * @return The list of completed subtasks in the big task.
     */
    @Override
    public ArrayList<ITask> getCompletedSubTasks(){
        for (ITask task: subTaskList){
            if (task.getStatus()){
                compeletedSubTasks.add(task);
            }
        }
        return compeletedSubTasks;
    }

    /**
     * Gets the list of uncompleted subtasks in the big task.
     *
     * @return The list of uncompleted subtasks in the big task.
     */
    @Override
    public ArrayList<ITask> getUncompletedSubTasks() {
        ArrayList<ITask> uncompletedSubTask = new ArrayList<>();
        for (ITask task: subTaskList){
            if (!task.getStatus()){
                uncompletedSubTask.add(task);
            }
        }
        return uncompletedSubTask;
    }



    /**
     * Gets the list of subtasks in the big task.
     *
     * @return The list of subtasks in the big task.
     */
    @Override
    public ArrayList<ITask> getSubTaskList() {
        return subTaskList;
    }
}
