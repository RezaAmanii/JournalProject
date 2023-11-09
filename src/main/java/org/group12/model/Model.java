package org.group12.model;

import java.util.ArrayList;

public class Model {
    private final TodoList tasks;

    public Model() {
        tasks = new TodoList();
        tasks.addTask(new Task("Willys"));
        tasks.addTask(new Task("Plugga"));
        tasks.addTask(new Task("Skriva rapport"));
    }

    public ArrayList<Task> getTasks() {
        return tasks.getTasks();
    }
}
