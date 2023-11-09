package org.group12.model;

public class Model {
    private final TodoList todoList;

    public Model() {
        todoList = new TodoList();

        todoList.setTitle("Exempel Lista");

        todoList.addTask(new Task("Willys"));
        todoList.addTask(new Task("Plugga"));
        todoList.addTask(new Task("Skriva rapport"));
    }

    public TodoList getTodoList() {
        return todoList;
    }
}
