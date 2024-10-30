package controllers;

import dao.UserDAO;
import models.User;
import java.sql.Connection;
import java.util.Scanner;

public class UserController {
    private final UserDAO userDAO;
    private User currentUser;

    public UserController(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    // Register a new user
    public void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (userDAO.registerUser(new User(username, password))) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Username may already exist.");
        }
    }

    // Login an existing user
    public boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        User user = userDAO.authenticateUser(username, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
