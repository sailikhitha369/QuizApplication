package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String email;

    // Constructor for full initialization
    public User(int userId, String username, String passwordHash, String email) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    // Constructor for new user registration (without ID and email)
    public User(String username, String password) {
        this.username = username;
        this.passwordHash = hashPassword(password);
    }

    // Method to hash the password
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password hashing failed", e);
        }
    }

    // Getter and Setter methods
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String password) { this.passwordHash = hashPassword(password); }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
