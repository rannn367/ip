import java.io.IOException;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String description;

    public DeadlineCommand(String argument) {
        this.description = argument;
    }

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
                ui.showError("Invalid date and time format. Please try again.");
            }
        }
    }
}