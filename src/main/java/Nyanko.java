import java.util.Scanner;

public class Nyanko {
    public static void main(String[] args) {
        String name = "NYANKO\n";
        String greeting = "HEEHAW I'M " + name
                + "Today's a good day to chill and slack!\n"
                + "zzzzzz";
        String listMessage = "WOW you're so hardworking\n"
                + "ok fine... your task has been added! \n"
                + "added: ";
        String toDoListMessage = "ok you funny soul here's your to do list:\n";
        String markAsDoneMessage = "zzzz... oh WHAT you're done already?\n";
        String markAsNotDoneMessage = "I knew it! You're not done!\n";
        String othersMessage = "Whot on earth are you saying!!!\n";
        System.out.println(greeting);

        Task[] toDoList = new Task[100];
        int index = 0;

        //Solution for scanner input inspired by https://www.geeksforgeeks.org/how-to-take-input-from-user-in-java/
        Scanner scn = new Scanner(System.in);
        while (true) {
            String str = scn.nextLine();
            if (str.equals("bye")) {
                System.out.println("Good night... I'm going to nap zzzzz");
                break;
            } else if (str.equals("list")) {
                System.out.println(toDoListMessage);
                for (int i = 0; i < index; i++) {
                    String nextToDoListMessage = (i + 1)
                            + ". "
                            + toDoList[i].toString();
                    System.out.println(nextToDoListMessage);
                }
            } else if (str.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(str.substring(5)) - 1;
                toDoList[taskIndex].markAsDone();
                System.out.println(markAsDoneMessage);
                System.out.println("  " + toDoList[taskIndex].toString());
            } else if (str.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(str.substring(7)) - 1;
                toDoList[taskIndex].markAsNotDone();
                System.out.println(markAsNotDoneMessage);
                System.out.println("  " + toDoList[taskIndex].toString());
            } else if (str.startsWith("deadline ")) {
                System.out.println("When is it due?");
                String by = scn.nextLine();
                toDoList[index] = new Deadline(str.substring(9), by);
                System.out.println(listMessage + toDoList[index].toString());
                System.out.println("Oh my! You have " + (index + 1) + " tasks!");
                index++;
            } else if (str.startsWith("todo ")) {
                toDoList[index] = new ToDo(str.substring(5));
                System.out.println(listMessage + toDoList[index].toString());
                System.out.println("Oh my! You have " + (index + 1) + " tasks!");
                index++;
            } else if (str.startsWith("event ")) {
                System.out.println("When does it start?");
                String from = scn.nextLine();
                System.out.println("When does it end?");
                String to = scn.nextLine();
                toDoList[index] = new Event(str.substring(6), from, to);
                System.out.println(listMessage + toDoList[index].toString());
                System.out.println("Oh my! You have " + (index + 1) + " tasks!");
                index++;
            } else {
                System.out.println(othersMessage);
            }
        }


    }
}
