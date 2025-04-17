import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String filePath = "/Users/saniabhandari/IdeaProjects/Todo list/tasks.txt";
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

                    try (FileWriter fileWriter = new FileWriter(filePath, true)) {
                        fileWriter.write(task+"\n");
                    } catch (FileNotFoundException e) {
                        System.out.println("Could not locate file location\n");
                    } catch (IOException e) {
                        System.out.println("Could not write file\n");
                    }
                }

                case 2 -> {
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

                    System.out.println("Enter the task number to delete: ");
                    int taskNumberToDelete = scanner.nextInt();

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

                case 4->{
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        String line = reader.readLine(); // Read the first line to check if the file is empty
                        if (line == null) {
                            System.out.println("No tasks have been entered yet\n");
                        } else {
                            int taskNumber = 1;
                            System.out.println(taskNumber + " " + line + "[Pending]");
                            taskNumber++;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(taskNumber + " " + line + "[Pending]");
                                taskNumber++;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Could not locate file\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong\n");
                    }

                    System.out.println("Enter the completed task number: ");
                    int taskNumber = scanner.nextInt();

                    try {

                        List<String> tasksList = new ArrayList<>();
                        Scanner fileScanner = new Scanner(new File(filePath));

                        while (fileScanner.hasNextLine()) {
                            tasksList.add(fileScanner.nextLine());
                        }
                        fileScanner.close();

                        if (taskNumber < 1 || taskNumber > tasksList.size()) {
                            System.out.println("Invalid task number\n");
                        } else {
                            tasksList.remove(taskNumber - 1);
                            try (FileWriter fileWriter = new FileWriter(filePath)) {
                                for (String task : tasksList) {
                                    fileWriter.write(task+"\n");
                                }
                            }
                        }
                        System.out.println("Task has been marked as completed");
                    } catch (IOException e) {
                        System.out.println("Error reading or writing to the file: " + e.getMessage());
                    }

                }

                case 3 -> {
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        String line = reader.readLine(); // Read the first line to check if the file is empty
                        if (line == null) {
                            System.out.println("No tasks have been entered yet\n");
                        } else {
                            int taskNumber = 1;
                            System.out.println(taskNumber +" "+ line + "[Pending]");
                            taskNumber++;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(taskNumber + " " + line + "[Pending]");
                                taskNumber++;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Could not locate file\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong\n");
                    }
                }

                case 5 -> System.out.println("Exiting... Goodbye!");

                default -> System.out.println("Invalid option!");

            }
        }while (choice != 5);

    }
}