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
        if (parts.length < 3) {
            throw new InvalidTaskFormatException("Invalid task format: " + saveFormat);
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markAsDone();
        }

        return todo;
    }
}