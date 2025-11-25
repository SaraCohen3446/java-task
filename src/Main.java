import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {


    // main class for Task Manager
    // allows the user to add update delete mark as done search and list tasks
    // tasks are stored in a json file and loaded on start
    public static void main(String[] args) {
        File file = new File("data/tasks.json");

        // initialize task repository and load existing tasks from file
        TaskRepository repo = new TaskRepository();
        repo.loadToFile(file);

        // service to handle operations like marking done or searching
        TaskService service = new TaskService(repo, file);

        Scanner sc = new Scanner(System.in);

        // main menu loop
        boolean running = true;
        while (running) {
            // display menu options for the user
            System.out.println("---- Task Manager ----");
            System.out.println("1 - Add Task");
            System.out.println("2 - Update Task");
            System.out.println("3 - Delete Task");
            System.out.println("4 - Mark Task as Done");
            System.out.println("5 - Search Tasks");
            System.out.println("6 - List All Tasks");
            System.out.println("7 - List Tasks Sorted by Status");
            System.out.println("0 - Exit");
            System.out.print("Your choice: ");

            int choice = -1;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // clear input buffer
            } else {
                System.out.println("Invalid input, please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    // add a new task
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    Task t = new Task(title, desc, Status.NEW);
                    repo.add(t, file);
                    System.out.println("Task added successfully.");
                }
                case 2 -> {
                    // update an existing task by id
                    System.out.print("Task ID: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid ID, must be a number.");
                        sc.nextLine();
                        break;
                    }
                    int id = sc.nextInt();
                    sc.nextLine();
                    Task t = repo.getById(id);
                    if (t != null) {
                        System.out.print("New Title: ");
                        t.setTitle(sc.nextLine());
                        System.out.print("New Description: ");
                        t.setDescription(sc.nextLine());
                        repo.update(t, file);
                        System.out.println("Task updated.");
                    } else {
                        System.out.println("Task not found.");
                    }
                }
                case 3 -> {
                    // delete a task by id
                    System.out.print("Task ID: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid ID, must be a number.");
                        sc.nextLine();
                        break;
                    }
                    int id = sc.nextInt();
                    sc.nextLine();
                    repo.delete(id, file);
                    System.out.println("Task deleted if it existed.");
                }
                case 4 -> {
                    // mark a task as done by id
                    System.out.print("Task ID: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid ID, must be a number.");
                        sc.nextLine();
                        break;
                    }
                    int id = sc.nextInt();
                    sc.nextLine();
                    service.done(id);
                    System.out.println("Task marked as done if it existed.");
                }
                case 5 -> {
                    // search tasks containing a text
                    System.out.print("Search text: ");
                    String text = sc.next();
                    sc.nextLine();
                    List<Task> result = service.searchByText(text);
                    for (Task task : result) {
                        System.out.println("[" + task.getId() + "] " + task.getTitle() + " : "
                                + task.getDescription() + " [" + task.getStatus() + "]");
                    }
                }
                case 6 -> {
                    // display all tasks
                    List<Task> all = repo.listAll();
                    for (Task task : all) {
                        System.out.println("[" + task.getId() + "] " + task.getTitle() + " : "
                                + task.getDescription() + " [" + task.getStatus() + "]");
                    }
                }
                case 7 -> {
                    // display tasks sorted by status
                    List<Task> sorted = service.sortByStatus();
                    for (Task task : sorted) {
                        System.out.println("[" + task.getId() + "] " + task.getTitle() + " : "
                                + task.getDescription() + " [" + task.getStatus() + "]");
                    }
                }
                case 0 -> running = false; // exit program
                default -> System.out.println("Invalid choice, try again.");
            }
        }

        sc.close(); // close scanner to release resources
        System.out.println("Exiting Task Manager.");
    }
}
