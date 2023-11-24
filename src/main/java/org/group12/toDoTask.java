package org.group12;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class toDoTask {
    private int ID;
    private String taskName;
    private boolean isFinished;
    private ZonedDateTime taskDeadline;
    private ArrayList<subTask> completedSubTasks;
    private ArrayList<subTask> toDoSubTasks;
    public toDoTask(int id,String taskName,boolean isFinished,ZonedDateTime taskDeadline,ArrayList<subTask>tdsb,ArrayList<subTask>csb){
        this.ID=id;
        this.taskDeadline=taskDeadline;
        this.taskName=taskName;
        this.isFinished=isFinished;
        this.completedSubTasks=csb;
        this.toDoSubTasks=tdsb;
    }

    public void setToDoSubTasks(ArrayList<subTask> toDoSubTasks) {
        this.toDoSubTasks = toDoSubTasks;
    }

    public void setCompletedSubTasks(ArrayList<subTask> completedSubTasks) {
        this.completedSubTasks = completedSubTasks;
    }

    public ArrayList<subTask> getCompletedSubTasks() {
        return completedSubTasks;
    }

    public ArrayList<subTask> getToDoSubTasks() {
        return toDoSubTasks;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setTaskDeadline(ZonedDateTime taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public ZonedDateTime getTaskDeadline() {
        return taskDeadline;
    }

    public boolean isFinished() {
        return isFinished;
    }

}
