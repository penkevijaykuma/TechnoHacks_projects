public class advanceTask {
    private int id;
    private String description;

    public advanceTask(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String toFileString() {
        return id + "," + description;
    }
    public static advanceTask fromFileString(String line) {
        String[] parts = line.split(",", 2);
        int id = Integer.parseInt(parts[0]);
        String description = parts[1];
        return new advanceTask(id, description);
    }
} 