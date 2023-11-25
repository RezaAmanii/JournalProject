package org.group12.controller;

import org.group12.model.Items;
import org.group12.model.todo.ITodoCollection;

public class TodoController {
    private final Items items;

    public TodoController(Items items) {
        this.items = items;
    }

    public void addTaskList(String collectionID, String title) {
        ITodoCollection collection = (ITodoCollection) items.getItem(collectionID);
        collection.addTaskList(title);
        notifyView();
    }

    public void removeTaskList(String collectionID, String taskListID) {
        ITodoCollection collection = (ITodoCollection) items.getItem(collectionID);
        collection.removeTaskList(taskListID);
        notifyView();
    }

    public void notifyView() {
        // todoView.update();
    }
}
