import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(String.valueOf(formatter)) + ")\n";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("%s|%d|%s|%s", this.getClass().getSimpleName(),
                isDone ? 1 : 0, description, by.format(String.valueOf(formatter)));
    }

    public static Task fromSaveFormat(String saveFormat) throws InvalidTaskFormatException {
        String[] parts = saveFormat.split("\\|");
        if (parts.length < 4) {
            throw new InvalidTaskFormatException("Invalid task format: " + saveFormat);
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        String by = parts[3];

        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            deadline.markAsDone();
        }

        return deadline;
    }
}
