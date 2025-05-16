import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String filePath = "tasks.txt";
        int choice;

        do {
            System.out.println("""
                    1.Add a task
                    2.Delete a task
                    3.Check pending tasks
                    4.Add completed tasks
                    5.Exit""");

            System.out.print("Enter Your choice:");
             choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("Enter your task here: ");
                    String task = scanner.nextLine();

                    taskWrite(filePath ,task);

                }

                case 2 -> {
                    taskRead(filePath);
                    System.out.println("Enter the task number to delete: ");
                    int taskNumberToDelete = scanner.nextInt();
                    taskDelete(filePath,taskNumberToDelete);

                }

                case 3 -> {
                    taskRead(filePath);
                }

                case 4->{
                    taskRead(filePath);

                    System.out.println("Enter the completed task number: ");
                    int taskNumber = scanner.nextInt();
                    taskDelete(filePath,taskNumber);
                }

                case 5 -> System.out.println("Exiting... Goodbye!");

                default -> System.out.println("Invalid option!");

            }
        }while (choice != 5);

    }

    static void taskRead(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Read the first line to check if the file is empty
            if (line == null) {
                System.out.println("No tasks have been entered yet\n");
            } else {
                int taskNumber = 1;
                System.out.println(taskNumber +" "+ line);
                taskNumber++;
                while ((line = reader.readLine()) != null) {
                    System.out.println(taskNumber + " " + line);
                    taskNumber++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file\n");
        } catch (IOException e) {
            System.out.println("Something went wrong\n");
        }
    }

    static void taskDelete(String filePath , int taskNumberToDelete){
        try {

            List<String> tasksList = new ArrayList<>();
            Scanner fileScanner = new Scanner(new File(filePath));

            while (fileScanner.hasNextLine()) {
                tasksList.add(fileScanner.nextLine());
            }
            fileScanner.close();

            if (taskNumberToDelete < 1 || taskNumberToDelete > tasksList.size()) {
                System.out.println("Invalid task number\n");
            } else {
                tasksList.remove(taskNumberToDelete - 1);
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    for (String task : tasksList) {
                        fileWriter.write(task+"\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing to the file: " + e.getMessage());
        }
    }

    static void taskWrite(String filePath , String task){
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(task+"\n");
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file location\n");
        } catch (IOException e) {
            System.out.println("Could not write file\n");
        }
    }


}