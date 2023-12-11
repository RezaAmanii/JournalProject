
import org.group12.controller.BigTaskController;
import org.group12.controller.TaskListController;
import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.todo.BigTask;
import org.group12.model.todo.ITaskList;
import org.group12.model.todo.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;



import static org.junit.jupiter.api.Assertions.*;

public class AllControllerTest {
    private TaskListController taskListController;
    private BigTaskController bigTaskController;
    private ItemsSet itemSet;
    private TaskList taskList;
    private String taskListID;



    // TaskListController Test

    @BeforeEach
    public void setUp(){
        taskListController = TaskListController.getInstance();
        bigTaskController = BigTaskController.getInstance();
        itemSet = Items.getInstance();
        taskList = new TaskList("Title test", "TL-001", itemSet);
        taskListID = taskList.getID();
    }


    @Test
    public void testAddToDoList(){
        String title = "Title test";
        String id = taskListController.handlerAddToDoList(title);
        assertNotNull(id);
    }

    @Test
    public void testRemoveToDoList(){

        String title = "Title test";
        String id = taskListController.handlerAddToDoList(title);

        ITaskList taskList = taskListController.getTaskListByID(id);
        assertNotNull(taskList);

        taskListController.handlerRemoveToDoList(taskList);
        assertNull(taskListController.getTaskListByID(id));

    }


    @Test
    public void testChangeListTitle(){
        String title = "Title test";
        String newTitle = "New Title";
        String id = taskListController.handlerAddToDoList(title);

        taskListController.changeListTitle(id, newTitle);
        assertEquals(newTitle, taskListController.getTaskListByID(id).getTitle());

    }

    @Test
    public void testGetTaskListByTitle(){
        String title = "Title test";
        String id = taskListController.handlerAddToDoList(title);

        ITaskList taskList = taskListController.getTaskListByTitle(title);
        assertNotNull(taskList);
        assertEquals(id, taskList.getID());
    }

    @Test
    public void testGetTaskListByID(){
        String title = "Title test";
        String id = taskListController.handlerAddToDoList(title);

        ITaskList taskList = taskListController.getTaskListByID(id);
        assertNotNull(taskList);
        assertEquals(title, taskList.getTitle());
    }

    @Test
    public void testFetchAllTaskLists(){
        int size = taskListController.fetchAllTaskLists().size();
        String title1 = "List 1";
        String title2 = "List 2";

        taskListController.handlerAddToDoList(title1);
        taskListController.handlerAddToDoList(title2);

        ArrayList<ITaskList> taskList = taskListController.fetchAllTaskLists();
        assertNotNull(taskList);
        assertEquals(size + 2, taskList.size());
    }

    @Test
    public void testRenameTaskList(){
        String title = "Title test";
        String newTitle = "New Title";
        String id = taskListController.handlerAddToDoList(title);

        taskListController.renameTaskList(id, newTitle);
        assertEquals(newTitle, taskListController.getTaskListByID(id).getTitle());
    }

    @Test
    public void testGetTaskListDateCreated(){
        String title = "Title test";
        String id = taskListController.handlerAddToDoList(title);

        String creationDate = taskListController.getTaskListDateCreated(id);
        assertNotNull(creationDate);
    }


    // BigTaskController Test
































}
