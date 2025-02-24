package nyanko;

import nyanko.command.Command;
import nyanko.command.InvalidTaskNumberException;
import nyanko.parser.Parser;
import nyanko.storage.Storage;
import nyanko.task.InvalidTaskFormatException;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

import java.io.IOException;

/**
 * The main entry point for the Nyanko application.
 * Nyanko is a task manager that supports ToDo, Deadline, and Event tasks.
 */
public class Nyanko {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Nyanko instance and initializes storage and task list.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Nyanko(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | InvalidTaskFormatException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the CLI-based interaction loop.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (IOException | InvalidTaskNumberException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Nyanko("data/nyanko.txt").run();
    }

    /**
     * Generates a response for the user's chat input in the GUI.
     *
     * @param input The user's input.
     * @return Nyanko's response as a string.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return ui.getLastResponse();
        } catch (Exception e) {
            return "Oops! Something went wrong: " + e.getMessage();
        }
    }
}
