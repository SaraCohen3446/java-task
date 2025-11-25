import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskService {

    // reference to the repository
    private TaskRepository repository;

    // file used for saving updates
    private File file;

    // constructor that initializes repository and file
    public TaskService(TaskRepository repository, File file) {
        this.repository = repository;
        this.file = file;
    }

    // marks a task as done and updates the repository
    public void done(int taskId) {
        Task task = repository.getById(taskId);
        if (task != null) {
            task.setStatus(Status.DONE);
            repository.update(task, file);
        }
    }

    // searches tasks by text in title or description
    public List<Task> searchByText(String text) {
        List<Task> allTasks = repository.listAll();
        List<Task> result = new ArrayList<>();
        for (Task t : allTasks) {
            if (t.getTitle().contains(text) || t.getDescription().contains(text)) {
                result.add(t);
            }
        }
        return result;
    }

    // returns all tasks sorted by status (NEW → IN_PROGRESS → DONE)
    public List<Task> sortByStatus() {
        List<Task> allTasks = repository.listAll();
        allTasks.sort(Comparator.comparing(Task::getStatus));
        return allTasks;
    }
}
