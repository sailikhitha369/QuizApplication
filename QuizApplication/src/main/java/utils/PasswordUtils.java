package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    // Method to hash a password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to verify a password against a stored hash
    public static boolean verifyPassword(String password, String storedHash) {
        String hashedPassword = hashPassword(password);
        return hashedPassword != null && hashedPassword.equals(storedHash);
    }
}
