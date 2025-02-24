package nyanko.ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private String lastResponse = "";

    /**
     * Reads the next command from the user (CLI mode).
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        lastResponse = "HEEHAW I'M NYANKO \uD83D\uDC31\nToday's a good day to chill and slack!\nzzzzzz";
        System.out.println(lastResponse);
    }

    /**
     * Displays an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        lastResponse = message;
        System.out.println(message);
    }

    public void showLoadingError() {
        lastResponse = "Error loading tasks";
        System.out.println(lastResponse);
    }

    public void showGoodbye() {
        lastResponse = "Good night... I'm going to nap zzzzz";
        System.out.println(lastResponse);
    }

    /**
     * Retrieves the last response stored, for use in the GUI.
     *
     * @return The last response as a string.
     */
    public String getLastResponse() {
        return lastResponse;
    }
}
