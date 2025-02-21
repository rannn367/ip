package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.ui.Ui;
import nyanko.task.ToDo;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MarkCommandTest {

    @Test
    void testMarkValidTask() throws IOException, InvalidTaskNumberException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("dummy.txt");
        taskList.addTask(new ToDo("Finish assignment"));

        Command command = new MarkCommand("1");
        command.execute(taskList, ui, storage);

        assertTrue(taskList.getTask(0).isDone()); // ✅ Use isDone() method
    }

    @Test
    void testMarkInvalidTaskNumber() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("dummy.txt");

        Command command = new MarkCommand("1"); // No tasks exist yet
        assertThrows(InvalidTaskNumberException.class, () -> {
            command.execute(taskList, ui, storage);
        });
    }
}
