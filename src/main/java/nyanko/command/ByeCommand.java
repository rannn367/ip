package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents the command to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand, displaying the goodbye message.
     *
     * @param tasks   The TaskList containing the current tasks (not used in this command).
     * @param ui      The UI instance to handle user interaction.
     * @param storage The Storage instance for saving tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
