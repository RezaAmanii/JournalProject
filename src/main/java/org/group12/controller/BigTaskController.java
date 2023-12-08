package org.group12.controller;


import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITaskList;
import java.util.List;

public class BigTaskController implements IController {

    private ItemsSet itemsSet;

    public BigTaskController(ItemsSet itemsSet) {
        this.itemsSet = itemsSet;
    }

}
