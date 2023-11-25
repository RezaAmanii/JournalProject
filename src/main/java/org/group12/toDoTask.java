package org.group12;

import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Represents a ToDo task.
 */
public class toDoTask {
    private int ID;
    private String taskName;
    private boolean isFinished, isImportant;
    private ZonedDateTime taskDeadline;
    private ArrayList<subTask> subTasks;


    public toDoTask(int id,String taskName,boolean isFinished,boolean isImportant,ZonedDateTime taskDeadline,ArrayList<subTask>csb){
        this.ID=id;
        this.taskDeadline=taskDeadline;
        this.isImportant=isImportant;
        this.taskName=taskName;
        this.isFinished=isFinished;
        this.subTasks=csb;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setSubTasks(ArrayList<subTask> subTasks) {
        this.subTasks = subTasks;
    }

    public ArrayList<subTask> getSubTasks() {
        return subTasks;
    }


    public ArrayList<subTask> getCompletedSubTasks() {
        ArrayList<subTask>completed=new ArrayList<>();
        for (subTask task:subTasks){
            if(task.isFinished())
                completed.add(task);
        }
        return completed;
    }

    public ArrayList<subTask> getUnfinishedSubTasks() {
        ArrayList<subTask>notCompleted=new ArrayList<>();
        for (subTask task:subTasks){
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
