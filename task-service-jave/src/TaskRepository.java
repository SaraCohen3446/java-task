import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    // stores all current task
    private List<Task> tasks = new ArrayList<>();

    // adds a new task and saves to file
    public void add(Task task, File file) {
        tasks.add(task);
        saveToFile(file);
    }

    // updates an existing task by id and saves to file
    public void update(Task task, File file) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                saveToFile(file);
                break;
            }
        }
    }

    // deletes a task by id and saves to file
    public void delete(int id, File file) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                saveToFile(file);
                break;
            }
        }
    }

    // returns a task by id
    public Task getById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    // returns all tasks
    public List<Task> listAll() {
        return new ArrayList<>(tasks);
    }


    // saves all current tasks to the specified JSON file
    public void saveToFile(File file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("[");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                writer.printf("  {\"id\":%d,\"title\":\"%s\",\"description\":\"%s\",\"status\":\"%s\"}%s%n",
                        t.getId(),
                        t.getTitle(),
                        t.getDescription(),
                        t.getStatus(),
                        (i < tasks.size() - 1) ? "," : "");
            }
            writer.println("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // loads current tasks from json file
    public void loadToFile(File file) {
        tasks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.startsWith("{") || !line.endsWith("}")) continue;

                line = line.substring(1, line.length() - 1);
                int id = 0;
                String title = "", description = "";
                Status status = Status.NEW;

                for (String part : line.split(",")) {
                    String[] kv = part.split(":", 2);
                    String key = kv[0].trim().replace("\"", "");
                    String value = kv[1].trim().replace("\"", "");

                    switch (key) {
                        case "id" -> id = Integer.parseInt(value);
                        case "title" -> title = value;
                        case "description" -> description = value;
                        case "status" -> status = Status.valueOf(value);
                    }
                }

                Task task = new Task(title, description, status);
                task.setId(id);
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
