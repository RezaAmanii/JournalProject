
import org.group12.model.todo.Task;
import org.group12.model.todo.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final TaskList todoList = new TaskList();
    private final Task task1 = new Task("Skriva rapport");
    private final Task task2 = new Task("Willys");
    private boolean error;

    @Test
        // Add and set the title of a list and add a task
    void addTask() {
        todoList.setTitle("Exempel Lista");
        todoList.addTask(task1);
        if (!todoList.getTasks().contains(task1)) {
            fail("Missing task");
        }
    }

    @Test
        // Remove a task and checks that its removed
    void removeTask() {
        todoList.removeTask(task1);
        if (todoList.getTasks().contains(task1)) {
            fail("Task not removed");
        }
    }

    @Test
        // Try to set the title of a list to null, should throw exception
    void addEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> todoList.setTitle(""));
    }

    @Test
        // Try to set the title of a task to null, should throw exception
    void addEmptyTask() {
        assertThrows(IllegalArgumentException.class, () -> todoList.addTask(new Task("")));
    }

    @Test
        // Try to remove a task that doesn't exist, should throw exception
    void removeEmptyTask() {
        assertThrows(IllegalArgumentException.class, () -> todoList.removeTask(task2));

    }

    @Test
        // Marks a task as completed and removes it from the list
    void markTaskAsComplete() {
        task2.setStatus(true);
        if (task2.getStatus() == false) {
            fail("Wrong complete status!");
        }
        fail("Not implemented! Should also check if its on the list?");

    }

    @Test
        // Marks a task as not complete and adds it back to the list
    void markTaskAsNotComplete() {
        fail("Not yet implemented");

    }

    @Test
        // create Subtasks for a task and add it to a list of subtasks connected to main task
    void createSubtask() {
        fail("Not yet implemented");

    }

    @Test
        // marks a Subtask as complete, should still be on subtask list
    void markSubtaskAsComplete() {
        fail("Not yet implemented");

    }

    @Test
        // Sets the deadline of a task
    void setTaskDueDate() {
        LocalDateTime date1 = LocalDateTime.of(2023, Month.DECEMBER, 30, 12, 00);
        task1.setDueDate(date1);
        assertEquals(task1.getDueDate(), date1);
    }

    @Test
        // Removes the deadline of a task
    void removeTaskDate() {
        LocalDateTime date1 = null;
        task1.setDueDate(date1);
        assertEquals(task1.getDueDate(), null);

    }
}