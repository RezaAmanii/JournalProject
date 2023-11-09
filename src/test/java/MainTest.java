import org.group12.model.Task;
import org.group12.model.TodoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    private final TodoList todoList = new TodoList();

    @Test
    void addTask(){
        todoList.setTitle("Exempel Lista");
        todoList.addTask(new Task("Skriva rapport"));
    }
    @Test
    void addEmptyTitle(){
        assertThrows(IllegalArgumentException.class, () -> todoList.setTitle(""));
    }
    @Test
    void addEmptyTask(){
        assertThrows(IllegalArgumentException.class, () -> todoList.addTask(new Task("")));
    }

}