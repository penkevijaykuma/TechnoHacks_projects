import java.util.Scanner;

public class advanceMain {
    public static void main(String[] args) {
        advanceTaskManager manager = new advanceTaskManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Task\n2. View Tasks\n3. Update Task\n4. Delete Task\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String desc = scanner.nextLine();
                    manager.addTask(desc);
                    System.out.println("Task added.");
                    break;
                case 2:
                    System.out.println("Tasks:");
                    for (advanceTask task : manager.getTasks()) {
                        System.out.println(task.getId() + ": " + task.getDescription());
                    }
                    break;
                case 3:
                    System.out.print("Enter task ID to update: ");
                    int upId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDesc = scanner.nextLine();
                    if (manager.updateTask(upId, newDesc)) {
                        System.out.println("Task updated.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter task ID to delete: ");
                    int delId = scanner.nextInt();
                    if (manager.deleteTask(delId)) {
                        System.out.println("Task deleted.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
} 