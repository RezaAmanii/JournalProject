package org.group12.model.todo;

import org.group12.model.Items;
import org.group12.model.todo.factories.TodoCollectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoCollectionTest {
    private TodoCollection todoCollection;

    @BeforeEach
    void setUp() {
        TodoCollectionFactory todoCollectionFactory = TodoCollectionFactory.getInstance();
        todoCollection = todoCollectionFactory.createTodoCollection("Test Collection");
    }

    @Test
    void addsTaskList() {
        todoCollection.addTaskList("New Task List");
        assertFalse(todoCollection.getTaskList().isEmpty());
    }

    @Test
    void removesExistingTaskList() {
        todoCollection.addTaskList("New Task List");
        ITaskList taskListId = todoCollection.getTaskList().get(0);
        todoCollection.removeTaskList(taskListId);
        assertTrue(todoCollection.getTaskList().isEmpty());
    }

    @Test
    void removeTaskListDoesNothingWhenTaskListDoesNotExist() {
        todoCollection.removeTaskList(new TaskList("New Task List", "123", Items.getInstance()));
        assertTrue(todoCollection.getTaskList().isEmpty());
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