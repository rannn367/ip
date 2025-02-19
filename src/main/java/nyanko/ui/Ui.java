package nyanko.ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("HEEHAW I'M NYANKO\nToday's a good day to chill and slack!\nzzzzzz");
    }

    public void showLine() {
        System.out.println("_________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks");
    }

    public void showGoodbye() {
        System.out.println("Good night... I'm going to nap zzzzz");
    }
}