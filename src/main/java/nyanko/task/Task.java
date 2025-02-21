package nyanko.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String toSaveFormat() {
        return String.format("%s|%d|%s", this.getClass().getSimpleName(), isDone ? 1 : 0, description);
    }

    public static Task fromSaveFormat(String saveFormat) throws InvalidTaskFormatException {
        String[] parts = saveFormat.split("\\|");
        if (parts.length < 3) {
            throw new InvalidTaskFormatException("Invalid task format: " + saveFormat);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "ToDo":
                task = new ToDo(description);
                break;
        case "Deadline":
                if (parts.length < 4) {
                    throw new InvalidTaskFormatException("Invalid deadline format: " + saveFormat);
                }
                task = new Deadline(description, parts[3]);
                break;
        case "Event":
                if (parts.length < 5) {
                    throw new InvalidTaskFormatException("Invalid event format: " + saveFormat);
                }
                task = new Event(description, parts[3], parts[4]);
                break;
        default:
                throw new InvalidTaskFormatException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
