package org.group12.model.todo;

import java.util.ArrayList;

public class TodoCollection implements ITodoCollection{
    private ArrayList<ITaskList> taskLists;
    private TaskListFactory taskListFactory;
    //private ArrayList<TodoObserver> observers;

    public TodoCollection (){
        taskLists = new ArrayList<>();
        taskListFactory = new TaskListFactory();
    }

    @Override
    public ArrayList<ITaskList> getTaskLists() {
        return null;
    }

    @Override
    public void addTaskList() {
        taskLists.add(taskListFactory.makeTaskList());
    }

    @Override
    public void remove() {

    }

    @Override
    public void edit() {

    }
}
