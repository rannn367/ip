package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.Task;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String argument) {
        this.index = Integer.parseInt(argument) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index >= tasks.size() || index < 0) {
            throw new InvalidTaskNumberException("Task number " + (index + 1) + " is invalid!!");
        }
        Task task = tasks.getTask(index);
        task.markAsNotDone();
        System.out.println("I knew it! You're not done!\n  " + task.toString());
        storage.save(tasks.getTasks());
    }
}