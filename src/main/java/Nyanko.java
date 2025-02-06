import java.util.Scanner;

public class Nyanko {
    public static void main(String[] args) {
        String name = "NYANKO\n";
        String greeting = "HEEHAW I'M " + name
                + "Today's a good day to chill and slack!\n"
                + "zzzzzz";
        System.out.println(greeting);

        //Solution below inspired by https://www.geeksforgeeks.org/how-to-take-input-from-user-in-java/
        Scanner scn = new Scanner(System.in);

        while (true) {
            String str = scn.nextLine();
            if (str.equals("bye")) {
                System.out.println("Good night... I'm going to nap zzzzz");
                break;
            }
            System.out.println(str);
        }
    }
}
