import org.group12.model.Task;
import org.group12.model.TodoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    private final TodoList todoList = new TodoList();
    private final Task task1 = new Task("Skriva rapport");
    private final Task task2 = new Task("Willys");

    @Test
    // Add and set the title of a list and add a task
    void addTask(){
        todoList.setTitle("Exempel Lista");
        todoList.addTask(task1);
    }
    @Test
        // Try to set the title of a list to null, should throw exception
    void addEmptyTitle(){
        assertThrows(IllegalArgumentException.class, () -> todoList.setTitle(""));
    }
    @Test
        // Try to set the title of a task to null, should throw exception
    void addEmptyTask(){
        assertThrows(IllegalArgumentException.class, () -> todoList.addTask(new Task("")));
    }
    @Test
        // Try to remove a task
    void removeTask(){
        todoList.removeTask(task1);
    }
    @Test
        // Try to remove a task that doesn't exist, should throw exception
    void removeEmptyTask(){
        assertThrows(IllegalArgumentException.class, () -> todoList.removeTask(task2));

    }
    @Test
        // Marks a task as completed and removes it from the list
    void markTaskAsComplete(){

    }
    @Test
        // Marks a task as not complete and adds it back to the list
    void markTaskAsNotComplete(){

    }
    @Test
        // create Subtasks for a task and add it to a list of subtasks connected to main task
    void createSubtask(){

    }
    @Test
        // marks a Subtask as complete, should still be on subtask list
    void markSubtaskAsComplete(){

    }
    @Test
        // Remove subtask from a task, should dissapear
    void removeSubtask(){

    }

}