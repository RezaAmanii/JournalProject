package org.group12;

import java.util.ArrayList;

/**
 * Represents a ToDo list.
 */

public class toDoList {
    int ID;
    private String listName;
    private ArrayList<toDoTask> tasks;

    /**
     * Default constructor for ToDoList.
     * Initializes an empty task list.
     */
    public toDoList() {
        this.tasks=new ArrayList<>();
    }

    /**
     * Constructor for ToDoList with initial values.
     *
     * @param id            The ID of the list.
     * @param listName      The name of the list.
     * @param initialTasks  The initial tasks in the list.
     */
    public toDoList(int id,String listName, ArrayList<toDoTask>initialTasks){
        this.ID=id;
        this.listName=listName;
        this.tasks=initialTasks;
    }

    /**
     * Sets the ID of the list.
     *
     * @param ID The ID to be set.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Retrieves the ID of the list.
     *
     * @return The ID of the list.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the tasks in the list.
     *
     * @param tasks The tasks to be set.
     */
    public void setTasks(ArrayList<toDoTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the tasks in the list.
     *
     * @return The tasks in the list.
     */
    public ArrayList<toDoTask> getTasks() {
        return tasks;
    }

    /**
     * Sets the name of the list.
     *
     * @param listName The name of the list to be set.
     */
    public void setListName(String listName) {
        this.listName = listName;
    }

    /**
     * Retrieves the name of the list.
     *
     * @return The name of the list.
     */
    public String getListName() {
        return listName;
    }
}
