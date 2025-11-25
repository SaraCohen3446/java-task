public class Task {


    // counter that creates a unique ID for every task
    private static int counter = 1;

    // class fields definition
    private int Id;
    private String Title;
    private String Description;
    private Status status;


    // constructor
    public Task(String title, String description, Status status) {
        this.Id = counter++;
        Title = title;
        Description = description;
        this.status = status;
    }


    // returns  task ID
    public int getId() {
        return Id;
    }

    // updates  task ID
    public void setId(int Id) {
        this.Id = Id;
    }

    // returns task title
    public String getTitle() {
        return Title;
    }

    // updates task title
    public void setTitle(String title) {
        Title = title;
    }

    // returns task description
    public String getDescription() {
        return Description;
    }

    // updates task description
    public void setDescription(String description) {
        Description = description;
    }

     // returns task status
    public Status getStatus() {
        return status;
    }

    // updates task status
    public void setStatus(Status status) {
        this.status = status;
    }
}