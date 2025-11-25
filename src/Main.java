import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("---- task manager ----");
            System.out.println("1 - add task");
            System.out.println("2 - update task");
            System.out.println("3 - delete task");
            System.out.println("4 - mark as done");
            System.out.println("5 - search tasks");
            System.out.println("6 - list all tasks");
            System.out.println("7 - list sorted by status");
            System.out.println("0 - exit");
            System.out.print("your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) running = false;
        }

        sc.close();
        System.out.println("exiting task manager");
    }
}
