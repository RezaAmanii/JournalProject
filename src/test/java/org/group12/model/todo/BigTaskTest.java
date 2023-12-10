package org.group12.model.todo;

import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BigTaskTest {
    private ItemsSet itemsSet;
    private BigTask bigTask;

    @BeforeEach
    void setUp() {
        itemsSet = Items.getInstance();
        bigTask = new BigTask("Test BigTask", "1", itemsSet);
    }

    @Test
    void setTitleChangesTitle() {
        String newTitle = "New Big Task Title";
        bigTask.setTitle(newTitle);
        assertEquals(newTitle, bigTask.getTitle());
    }

    @Test
    void setDueDateChangesDueDate() {
        LocalDateTime newDueDate = LocalDateTime.now().plusDays(1);
        bigTask.setDueDate(newDueDate);
        assertEquals(newDueDate, bigTask.getDueDate());
    }

    @Test
    void setFavouriteChangesFavouriteStatus() {
        bigTask.setFavourite(true);
        assertTrue(bigTask.isFavourite());
    }

    @Test
    void setCompletedChangesCompletionStatus() {
        bigTask.setCompleted(true);
        assertTrue(bigTask.getStatus());
    }

    @Test
    void setDescriptionChangesDescription() {
        String newDescription = "New Big Task Description";
        bigTask.setDescription(newDescription);
        assertEquals(newDescription, bigTask.getDescription());
    }

    /*@Test
    void addSubTaskIncreasesSubTaskCount() {
        bigTask.addSubTask("Sub Task Title");
        assertEquals(1, bigTask.getSubTaskMap().size());
    }

    @Test
    void removeSubTaskDecreasesSubTaskCount() {
        bigTask.addSubTask("Sub Task Title");
        String subTaskID = bigTask.getSubTaskMap().keySet().iterator().next();
        bigTask.removeSubTask(subTaskID);
        assertTrue(bigTask.getSubTaskMap().isEmpty());
    }

    @Test
    void removeNonExistentSubTaskDoesNotChangeSubTaskCount() {
        bigTask.addSubTask("Sub Task Title");
        bigTask.removeSubTask("NonExistentID");
        assertEquals(1, bigTask.getSubTaskMap().size());
    }*/
}