package org.group12.model.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Test Task", "1");
    }

    @Test
    void setTitle_changesTitle() {
        task.setTitle("New Title");
        assertEquals("New Title", task.getTitle());
    }

    @Test
    void getTitle_returnsTitle() {
        assertEquals("Test Task", task.getTitle());
    }

    @Test
    void getID_returnsID() {
        assertEquals("1", task.getID());
    }

    @Test
    void getStatus_returnsStatus() {
        assertFalse(task.getStatus());
    }

    @Test
    void setCompleted_changesStatus() {
        task.setCompleted(true);
        assertTrue(task.getStatus());
    }

    @Test
    void setCompleted_doesNotChangeStatusWhenSameValueProvided() {
        task.setCompleted(false);
        assertFalse(task.getStatus());
    }
}