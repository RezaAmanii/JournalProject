package org.group12.model.todo;

import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private ItemsSet itemsSet;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        itemsSet = Items.getInstance();
        taskList = new TaskList("Test TaskList", "1", itemsSet);
    }

    @Test
    void setTitle_changesTitle() {
        taskList.setTitle("New Title");
        assertEquals("New Title", taskList.getTitle());
    }

    @Test
    void getTitle_returnsTitle() {
        assertEquals("Test TaskList", taskList.getTitle());
    }

    @Test
    void getID_returnsID() {
        assertEquals("1", taskList.getID());
    }

    /*@Test
    void addBigTask_addsTaskToMap() {
        taskList.addBigTask("Big Task");
        HashMap<String, IBigTask> bigTaskMap = taskList.getBigTaskMap();
        assertFalse(bigTaskMap.isEmpty());
    }

    @Test
    void removeBigTask_removesTaskFromMap() {
        taskList.addBigTask("Big Task");
        HashMap<String, IBigTask> bigTaskMap = taskList.getBigTaskMap();
        String taskId = bigTaskMap.keySet().iterator().next();
        taskList.removeBigTask(taskId);
        assertTrue(bigTaskMap.isEmpty());
    }

    @Test
    void removeBigTask_doesNotRemoveNonexistentTask() {
        taskList.addBigTask("Big Task");
        taskList.removeBigTask("Nonexistent Task");
        HashMap<String, IBigTask> bigTaskMap = taskList.getBigTaskMap();
        assertEquals(1, bigTaskMap.size());
    }*/
}