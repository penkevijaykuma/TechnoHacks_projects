import java.io.*;
import java.util.*;

public class advanceTaskManager {
    private List<advanceTask> tasks = new ArrayList<>();
    private final String fileName = "advance_tasks.txt";

    public advanceTaskManager() {
        loadTasks();
    }

    public void addTask(String description) {
        int id = tasks.size() > 0 ? tasks.get(tasks.size() - 1).getId() + 1 : 1;
        advanceTask task = new advanceTask(id, description);
        tasks.add(task);
        saveTasks();
    }

    public List<advanceTask> getTasks() {
        return tasks;
    }

    public boolean updateTask(int id, String newDescription) {
        for (advanceTask task : tasks) {
            if (task.getId() == id) {
                task.setDescription(newDescription);
                saveTasks();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        Iterator<advanceTask> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            advanceTask task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                saveTasks();
                return true;
            }
        }
        return false;
    }

    private void loadTasks() {
        tasks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(advanceTask.fromFileString(line));
            }
        } catch (FileNotFoundException e) {
            // File does not exist yet, that's fine
        } catch (IOException e) {
            System.err.println("Error reading tasks: " + e.getMessage());
        }
    }
    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (advanceTask task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
} 