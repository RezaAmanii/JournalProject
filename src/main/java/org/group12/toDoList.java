package org.group12;

import java.util.ArrayList;

public class toDoList {
    int ID;
    private String listName;
    private ArrayList<toDoTask> tasks;


    public toDoList() {
        this.tasks=new ArrayList<>();
    }

    public toDoList(int id,String listName, ArrayList<toDoTask>initialTasks){
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

    public void setTasks(ArrayList<toDoTask> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<toDoTask> getTasks() {
        return tasks;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }
}
