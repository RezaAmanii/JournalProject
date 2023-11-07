package org.group12.model;

import java.util.ArrayList;

public class TodoList implements ITodoList {
    private final ArrayList<Task> tasks;

    public TodoList() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
