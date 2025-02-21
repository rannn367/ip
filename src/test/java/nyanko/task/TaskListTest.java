package nyanko.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class TaskListTest {

    @Test
    void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Read a book");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals("[T][ ] Read a book", taskList.getTask(0).toString());
    }

    @Test
    void testRemoveTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Read a book");
        taskList.addTask(task);
        taskList.removeTask(0);
        assertEquals(0, taskList.size());
    }

    @Test
    void testGetTaskInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(0));
    }
}
