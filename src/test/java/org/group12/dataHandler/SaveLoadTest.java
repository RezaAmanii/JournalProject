package org.group12.dataHandler;

import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.todo.ITaskList;
import org.group12.model.todo.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveLoadTest {
    private Container container;
    private Items items;
    SaveLoad saveLoad = new SaveLoad();

    @Test
    void save() {
        Container container = Container.getInstance();
        Items items = Items.getInstance();

        String ID = container.getTodoCollection().addTaskList("TestList");
        ITaskList taskList = (ITaskList) items.getItem(ID);
        taskList.addBigTask("TestTask");
        saveLoad.save(container, items);
    }

    @Test
    void load() {
        Container container = null;
        container = saveLoad.loadContainer();
        Items items = null;
        items = saveLoad.loadItems();
        System.out.println(items.toString());

        boolean containerIsNotNull = container != null;
        boolean itemsIsNotNull = items != null;
        assertNotNull(containerIsNotNull && itemsIsNotNull);
    }


}