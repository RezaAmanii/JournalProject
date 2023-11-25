package org.group12;

public class subTask {
    int ID;
    private String subTaskName;
    private boolean isFinished;
    subTask(int id,String subTaskName,boolean isFinished){
        this.ID=id;
        this.subTaskName=subTaskName;
        this.isFinished=isFinished;
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
