package org.group12.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TodoList implements ITodoList {
    private final ArrayList<Task> tasks;

    public TodoList() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String desc) {

    }

    @Override
    public LocalDateTime getDateCreated() {
        return null;
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
