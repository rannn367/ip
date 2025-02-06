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
        System.out.println(greeting);

        String[] toDoList = new String[100];
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
                    System.out.println((i + 1) + ". " + toDoList[i]);
                }
            } else {
                System.out.println(listMessage + str);
                toDoList[index] = str;
                index++;
            }
        }


    }
}
