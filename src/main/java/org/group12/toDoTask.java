package org.group12;

import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Represents a ToDo task.
 */
public class toDoTask {
    private int ID;
    private String taskName;
    private boolean isFinished;
    private ZonedDateTime taskDeadline;
    private ArrayList<subTask> completedSubTasks;
    private ArrayList<subTask> toDoSubTasks;

    /**
     * Constructor for ToDoTask with initial values.
     *
     * @param id               The ID of the task.
     * @param taskName         The name of the task.
     * @param isFinished       The status of the task (finished or not).
     * @param taskDeadline     The deadline of the task.
     * @param csb     The list of to-do subtasks.
     * @param tdsb The list of completed subtasks.
     */
    public toDoTask(int id,String taskName,boolean isFinished,ZonedDateTime taskDeadline,ArrayList<subTask>tdsb,ArrayList<subTask>csb){
        this.ID = id;
        this.taskDeadline = taskDeadline;
        this.taskName = taskName;
        this.isFinished = isFinished;
        this.completedSubTasks = csb;
        this.toDoSubTasks = tdsb;
    }

    /**
     * Sets the list of to-do subtasks.
     *
     * @param toDoSubTasks The list of to-do subtasks to be set.
     */
    public void setToDoSubTasks(ArrayList<subTask> toDoSubTasks) {
        this.toDoSubTasks = toDoSubTasks;
    }

    /**
     * Sets the list of completed subtasks.
     *
     * @param completedSubTasks The list of completed subtasks to be set.
     */
    public void setCompletedSubTasks(ArrayList<subTask> completedSubTasks) {
        this.completedSubTasks = completedSubTasks;
    }

    /**
     * Retrieves the list of completed subtasks.
     *
     * @return The list of completed subtasks.
     */
    public ArrayList<subTask> getCompletedSubTasks() {
        return completedSubTasks;
    }

    /**
     * Retrieves the list of to-do subtasks.
     *
     * @return The list of to-do subtasks.
     */
    public ArrayList<subTask> getToDoSubTasks() {
        return toDoSubTasks;
    }

    /**
     * Sets the ID of the task.
     *
     * @param ID The ID to be set.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Retrieves the ID of the task.
     *
     * @return The ID of the task.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the status of the task.
     *
     * @param finished The status of the task to be set.
     */
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    /**
     * Sets the deadline of the task.
     *
     * @param taskDeadline The deadline of the task to be set.
     */
    public void setTaskDeadline(ZonedDateTime taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    /**
     * Sets the name of the task.
     *
     * @param taskName The name of the task to be set.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public ZonedDateTime getTaskDeadline() {
        return taskDeadline;
    }

    /**
     * Checks if the task is finished.
     *
     * @return True if the task is finished, false otherwise.
     */
    public boolean isFinished() {
        return isFinished;
    }

}
