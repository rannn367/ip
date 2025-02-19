public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")\n";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s|%d|%s|%s|%s", this.getClass().getSimpleName(), isDone ? 1 : 0, description, from, to);
    }

    public static Task fromSaveFormat(String saveFormat) throws InvalidTaskFormatException {
        String[] parts = saveFormat.split("\\|");
        if (parts.length < 5) {
            throw new InvalidTaskFormatException("Invalid task format: " + saveFormat);
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        String from = parts[3];
        String to = parts[4];

        Event event = new Event(description, from, to);
        if (isDone) {
            event.markAsDone();
        }

        return event;
    }
}
