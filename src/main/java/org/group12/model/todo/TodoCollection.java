package org.group12.model.todo;

import java.util.ArrayList;

public class TodoCollection implements ITodoCollection{
    private ArrayList<ITaskList> taskLists;
    //private ArrayList<TodoObserver> observers;
    //private TaskListFactory taskListFactory;

    public TodoCollection (){
        taskLists = new ArrayList<>();
    }

    @Override
    public ArrayList<ITaskList> getTaskLists() {
        return null;
    }

    @Override
    public void addTaskList() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void edit() {

    }
}
