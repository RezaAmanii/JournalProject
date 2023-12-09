package org.group12.controller;


import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.IBigTask;
import org.group12.model.todo.ITaskList;
import java.util.List;

public class BigTaskController implements IController {

    private ItemsSet itemsSet;
    private static BigTaskController instance;

    private BigTaskController() {
        this.itemsSet = Items.getInstance();
    }

    public static BigTaskController getInstance() {
        if (instance == null) {
            instance = new BigTaskController();
        }
        return instance;
    }



    private String getBigTaskTitle(IBigTask bigTask){
        return itemsSet.getItem(bigTask.getID()).getTitle();
    }

    private String getBigTaskDueDate(IBigTask bigTask){
        //return itemsSet.getItem(bigTask.getID()).getDueDate().toString();
        return null;
    }

}
