package entry;

import controllers.UserController;
import controllers.QuizController;
import utils.DBConnection;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Online Quiz Application");

        // Establish the database connection
        Connection connection = DBConnection.getConnection();
        if (connection == null) {
            System.out.println("Failed to connect to the database. Exiting...");
            return;
        }

        // Initialize controllers
        UserController userController = new UserController(connection);
        QuizController quizController = new QuizController(connection);

        // Start a simple console-based application flow
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\nPlease choose an option:");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Take a Quiz");
                System.out.println("4. View Quiz Results");
                System.out.println("5. View Leaderboard");
                System.out.println("6. Exit");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        userController.register(scanner);
                        break;
                    case 2:
                        if (userController.login(scanner)) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Login failed. Try again.");
                        }
                        break;
                    case 3:
                        if (userController.isUserLoggedIn()) {
                            quizController.startQuiz(scanner, userController.getCurrentUser());
                        } else {
                            System.out.println("Please log in to take a quiz.");
                        }
                        break;
                    case 4:
                        if (userController.isUserLoggedIn()) {
                            quizController.viewQuizResults(userController.getCurrentUser());
                        } else {
                            System.out.println("Please log in to view quiz results.");
                        }
                        break;
                    case 5:
                        quizController.viewLeaderboard();
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }
        System.out.println("Thank you for using the Online Quiz Application!");
    }
}

