package org.group12.model.todo;

import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.factories.TodoCollectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoCollectionTest {
    private ItemsSet itemsSet;
    private TodoCollection todoCollection;

    @BeforeEach
    void setUp() {
        itemsSet = Items.getInstance();
        TodoCollectionFactory todoCollectionFactory = new TodoCollectionFactory(itemsSet);
        todoCollection = todoCollectionFactory.createTodoCollection("Test Collection");
    }

    @Test
    void addTaskListAddsNewTaskList() {
        todoCollection.addTaskList("New Task List");
        assertFalse(todoCollection.getTaskListMap().isEmpty());
    }

    @Test
    void removeTaskListRemovesExistingTaskList() {
        todoCollection.addTaskList("New Task List");
        String taskListId = todoCollection.getTaskListMap().keySet().iterator().next();
        todoCollection.removeTaskList(taskListId);
        assertTrue(todoCollection.getTaskListMap().isEmpty());
    }

    @Test
    void removeTaskListDoesNothingWhenTaskListDoesNotExist() {
        todoCollection.removeTaskList("Nonexistent ID");
        assertTrue(todoCollection.getTaskListMap().isEmpty());
    }

    @Test
    void setTitleChangesCollectionTitle() {
        todoCollection.setTitle("New Title");
        assertEquals("New Title", todoCollection.getTitle());
    }

    @Test
    void getTitleReturnsCorrectTitle() {
        assertEquals("Test Collection", todoCollection.getTitle());
    }
}