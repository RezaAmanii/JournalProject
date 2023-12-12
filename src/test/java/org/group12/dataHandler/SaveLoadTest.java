package org.group12.dataHandler;

import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.todo.ITaskList;
import org.group12.model.todo.TaskList;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SaveLoadTest {
    private Container container;
    private Items items;
    SaveLoad saveLoad = SaveLoad.getInstance();

    @Test
    void findTest1() {
        boolean saveExists = new File("containerData.ser").isFile();
        assertTrue(saveExists);
    }

    @Test
    void findTest2() {
        boolean saveExists = new File("itemsData.ser").isFile();
        assertTrue(saveExists);
    }

    @Test
    void save() {
        Container container = saveLoad.getContainerInstance();
        Items items = saveLoad.getItemsInstance();

        String ID = container.getTodoCollection().addTaskList("TestList");
        ITaskList taskList = (ITaskList) items.getItem(ID);
        taskList.addBigTask("TestTask");
        saveLoad.save();
    }

    @Test
    void load() {
        Container container = null;
        container = saveLoad.getContainerInstance();
        Items items = null;
        items = saveLoad.getItemsInstance();

        System.out.println(items.toString());

        boolean containerIsNotNull = container != null;
        boolean itemsIsNotNull = items != null;

        System.out.println(containerIsNotNull);
        System.out.println(itemsIsNotNull);

        assertTrue(containerIsNotNull && itemsIsNotNull);
    }


}