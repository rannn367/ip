package nyanko.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private String lastResponse = "";
    private List<String> responseBuffer = new ArrayList<>();

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
        addResponse("HEEHAW I'M NYANKO 🐱\nToday's a good day to chill and slack!\nzzzzzz");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        lastResponse = message;
        System.out.println(message);
        addResponse(message);
    }

    public void showLoadingError() {
        lastResponse = "Error loading tasks";
        System.out.println(lastResponse);
        addResponse(lastResponse);
    }

    public void showGoodbye() {
        lastResponse = "Good night... I'm going to nap zzzzz";
        System.out.println(lastResponse);
        addResponse(lastResponse);
    }

    public void addResponse(String message) {
        responseBuffer.add(message);
    }

    /**
     * Retrieves all stored responses for GUI.
     *
     * @return A concatenated string of responses.
     */
    public String getResponse() {
        String response = String.join("\n", responseBuffer);
        responseBuffer.clear(); // Clear after retrieval
        return response;
    }
}
