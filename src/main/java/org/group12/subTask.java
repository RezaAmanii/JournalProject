package org.group12;

public class subTask {
    private String subTaskName;
    private boolean isFinished;

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
