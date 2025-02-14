import java.util.ArrayList;
import java.util.Scanner;

public class Nyanko {
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, DEADLINE, TODO, EVENT, INVALID
    }

    static String name = "NYANKO\n";
    static String greeting = "HEEHAW I'M " + name
            + "Today's a good day to chill and slack!\n"
            + "zzzzzz";
    static String listMessage = "WOW you're so hardworking\n"
            + "ok fine... your task has been added! \n"
            + "added: ";
    static String toDoListMessage = "ok you funny soul here's your to do list:\n";
    static String othersMessage = "Whot on earth are you saying!!!\n";

    static ArrayList<Task> toDoList = new ArrayList<>();
    static int index = 0;

    public static void main(String[] args) {
        System.out.println(greeting);

        //Solution for scanner input inspired by https://www.geeksforgeeks.org/how-to-take-input-from-user-in-java/
        Scanner scn = new Scanner(System.in);

        while (true) {
            //Solution for splitting the command and argument inspired by Java Chatbot in ChatGPT
            String userInput = scn.nextLine();
            String[] parts = userInput.split(" ", 2);
            Command command = getCommand(parts[0]);
            String argument = parts.length > 1 ? parts[1] : "";

            switch (command) {
            case BYE:
                System.out.println("Good night... I'm going to nap zzzzz");
                return;

            case LIST:
                System.out.println(toDoListMessage);
                for (int i = 0; i < index; i++) {
                    String nextToDoListMessage = (i + 1)
                            + ". "
                            + toDoList.get(i).toString();
                    System.out.println(nextToDoListMessage);
                }
                break;

                case MARK:
                int taskIndex;
                taskIndex = Integer.parseInt(argument) - 1;
                try {
                    validateTaskNumber(taskIndex);
                    markAsDone(toDoList.get(taskIndex));
                    break;
                } catch (InvalidTaskNumberException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }

            case UNMARK:
                taskIndex = Integer.parseInt(argument) - 1;
                try {
                    validateTaskNumber(taskIndex);
                    markAsNotDone(toDoList.get(taskIndex));
                    break;
                } catch (InvalidTaskNumberException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }

            case DELETE:
                taskIndex = Integer.parseInt(argument) - 1;
                try {
                    validateTaskNumber(taskIndex);
                    toDoList.remove(taskIndex);
                    index--;
                    System.out.println("You're goofy but I deleted your task");
                    break;
                } catch (InvalidTaskNumberException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }

            case DEADLINE:
                System.out.println("When is it due?");
                String by = scn.nextLine();
                toDoList.add(new Deadline(argument, by));
                System.out.println(listMessage + toDoList.get(index).toString());
                System.out.println("Oh my! You have " + (index + 1) + " tasks!");
                index++;
                break;

            case TODO:
                toDoList.add(new ToDo(argument));
                System.out.println(listMessage + toDoList.get(index).toString());
                System.out.println("Oh my! You have " + (index + 1) + " tasks!");
                index++;
                break;

            case EVENT:
                System.out.println("When does it start?");
                String from = scn.nextLine();
                System.out.println("When does it end?");
                String to = scn.nextLine();
                toDoList.add(new Event(argument, from, to));
                System.out.println(listMessage + toDoList.get(index).toString());
                System.out.println("Oh my! You have " + (index + 1) + " tasks!");
                index++;
                break;

            case INVALID:
                System.out.println(othersMessage);
                break;
            }
        }
    }

    private static Command getCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.INVALID;
        }
    }

    public static void markAsDone(Task task) {
        String markAsDoneMessage = "zzzz... oh WHAT you're done already?\n";

        task.markAsDone();
        System.out.println(markAsDoneMessage);
        System.out.println("  " + task.toString());
    }

    public static void markAsNotDone(Task task) {
        String markAsNotDoneMessage = "I knew it! You're not done!\n";

        task.markAsNotDone();
        System.out.println(markAsNotDoneMessage);
        System.out.println("  " + task.toString() + "\n");
    }

    public static void validateTaskNumber(int taskIndex) throws InvalidTaskNumberException {
        if (taskIndex >= index || taskIndex < 0) {
            throw new InvalidTaskNumberException("You are cuckoo! There's only "
                    + index + " tasks! Task number " + (taskIndex + 1) + " is invalid!!");
        }
    }
}
