package org.group12;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class toDoTask {
    private int ID;
    private String taskName;
    private boolean isFinished;
    private ZonedDateTime taskDeadline;


    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }


}
