package nyanko.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s|%d|%s", this.getClass().getSimpleName(), isDone ? 1 : 0, description);
    }

    public static Task fromSaveFormat(String saveFormat) throws InvalidTaskFormatException {
        String[] parts = saveFormat.split("\\|");
        if (parts.length != 3) {
            throw new InvalidTaskFormatException("Invalid task format: " + saveFormat);
        }

        if (!parts[1].equals("0") && !parts[1].equals("1")) {
            throw new InvalidTaskFormatException("Invalid completion status in ToDo task: " + parts[1] + ". Expected '0' or '1'.");
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        if (description.isEmpty()) {
            throw new InvalidTaskFormatException("Invalid ToDo task: description is empty. Data: " + saveFormat);
        }

        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markAsDone();
        }

        return todo;
    }
}