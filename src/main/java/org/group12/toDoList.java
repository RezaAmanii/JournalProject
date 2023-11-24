package org.group12;

import java.util.ArrayList;

public class toDoList {
    int ID;
    private String listName;
    private ArrayList<toDoTask> tasks;


    toDoList(){this.tasks=new ArrayList<>();}
    toDoList(int id,String listName, ArrayList<toDoTask>initialTasks){
        this.ID=id;
        this.listName=listName;
        this.tasks=initialTasks;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<toDoTask> getTasks() {
        return tasks;
    }

}
