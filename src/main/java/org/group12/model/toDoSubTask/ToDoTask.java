package org.group12.model.toDoSubTask;

import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Represents a ToDo task.
 */
public class ToDoTask {
    private int ID;
    private String taskName;
    private boolean isFinished, isImportant;
    private ZonedDateTime taskDeadline;
    private ArrayList<SubTask> SubTasks;


    public ToDoTask(int id, String taskName, boolean isFinished, boolean isImportant, ZonedDateTime taskDeadline, ArrayList<SubTask>csb){
        this.ID=id;
        this.taskDeadline=taskDeadline;
        this.isImportant=isImportant;
        this.taskName=taskName;
        this.isFinished=isFinished;
        this.SubTasks =csb;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setSubTasks(ArrayList<SubTask> SubTasks) {
        this.SubTasks = SubTasks;
    }

    public ArrayList<SubTask> getSubTasks() {
        return SubTasks;
    }


    public ArrayList<SubTask> getCompletedSubTasks() {
        ArrayList<SubTask>completed=new ArrayList<>();
        for (SubTask task: SubTasks){
            if(task.isFinished())
                completed.add(task);
        }
        return completed;
    }

    public ArrayList<SubTask> getUnfinishedSubTasks() {
        ArrayList<SubTask>notCompleted=new ArrayList<>();
        for (SubTask task: SubTasks){
            if(!task.isFinished())
                notCompleted.add(task);
        }
        return notCompleted;
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
