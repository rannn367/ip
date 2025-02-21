package nyanko.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import nyanko.storage.Storage;
import nyanko.task.Deadline;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents a command to create and add a {@link Deadline} task to the task list.
 * The command prompts the user to enter a due date in the format "yyyy-MM-dd HHmm".
 */
public class DeadlineCommand extends Command {
    private String description;

    /**
     * Constructs a {@code DeadlineCommand} with the given task description.
     *
     * @param argument The description of the deadline task.
     */
    public DeadlineCommand(String argument) {
        this.description = argument;
    }

    /**
     * Executes the command by prompting the user for a due date,
     * creating a {@link Deadline} task, and adding it to the task list.
     * If an invalid date format is entered, the user is asked to re-enter it.
     *
     * @param tasks   The task list to add the deadline task.
     * @param ui      The UI instance for user interaction.
     * @param storage The storage instance to save the updated task list.
     * @throws IOException If an error occurs while saving the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        while (true) {
            try {
                ui.showLine();
                System.out.println("When is it due? (format: yyyy-MM-dd HHmm)");
                String by = ui.readCommand();
                Deadline deadline = new Deadline(description, by);
                tasks.addTask(deadline);
                System.out.println("WOW you're so hardworking\nok fine... your task has been added!\nadded: " + deadline.toString());
                System.out.println("Oh my! You have " + tasks.size() + " tasks!");
                storage.save(tasks.getTasks());
                break;
            } catch (DateTimeParseException e) {
                ui.showError("Your date/time format is incorrect! Don't be dumb! Try again!");
            }
        }
    }
}