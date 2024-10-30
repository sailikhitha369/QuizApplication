package dao;

import models.User;
import utils.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Register a new user
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)"; // Added email field
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getEmail()); // Ensure User class has this method
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    // Authenticate user for login
    public User authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    // Use PasswordUtils to verify the password
                    if (PasswordUtils.verifyPassword(password, storedHash)) {
                        return new User(rs.getInt("id"), username, storedHash, rs.getString("email")); // Assuming User has this constructor
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during authentication: " + e.getMessage());
        }
        return null; // Return null if user is not found or authentication fails
    }
}
