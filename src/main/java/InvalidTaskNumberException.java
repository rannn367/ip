public class InvalidTaskNumberException extends Exception {
    int argument;
    int numberOfTasks;

    public InvalidTaskNumberException(String message) {
        super(message);
        
    }
}
