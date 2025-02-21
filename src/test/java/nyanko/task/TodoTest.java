package nyanko.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    void testToSaveFormat() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("ToDo|0|Buy groceries", todo.toSaveFormat());
    }

    @Test
    void testFromSaveFormatValid() throws InvalidTaskFormatException {
        Task task = ToDo.fromSaveFormat("ToDo|1|Walk the dog");
        assertTrue(task instanceof ToDo);
        assertEquals("[T][X] Walk the dog", task.toString());
    }

    @Test
    void testFromSaveFormatInvalid() {
        assertThrows(InvalidTaskFormatException.class, () -> {
            ToDo.fromSaveFormat("ToDo|X|MissingDescription");
        });
    }
}
