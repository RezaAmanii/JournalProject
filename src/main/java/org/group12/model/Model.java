package org.group12.model;

import org.group12.model.todo.Task;
import org.group12.model.todo.TaskList;

public class Model {
    private final TaskList taskList;

    public Model() {
        taskList = new TaskList();

        taskList.setTitle("Exempel Lista");

        taskList.addTask(new Task("Willys"));
        taskList.addTask(new Task("Plugga"));
        taskList.addTask(new Task("Skriva rapport"));
    }

    public TaskList getTodoList() {
        return taskList;
    }
}
