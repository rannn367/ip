import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String description;

    public EventCommand(String argument) {
        this.description = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        while (true) {
            try {
                ui.showLine();
                System.out.println("When does it start? (format: yyyy-MM-dd HHmm)");
                String from = ui.readCommand();
                System.out.println("When does it end? (format: yyyy-MM-dd HHmm)");
                String to = ui.readCommand();
                Event event = new Event(description, from, to);
                tasks.addTask(event);
                System.out.println("WOW you're so hardworking\nok fine... your task has been added!\nadded: " + event.toString());
                System.out.println("Oh my! You have " + tasks.size() + " tasks!");
                storage.save(tasks.getTasks());
                break;
            } catch (DateTimeParseException e) {
                ui.showError("Invalid date and time format. Please try again.");
            }
        }
    }
}