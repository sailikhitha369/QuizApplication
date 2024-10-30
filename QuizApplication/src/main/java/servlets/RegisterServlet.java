package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import models.User;
import utils.DBConnection;
import utils.PasswordUtils;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Hash the password
        String hashedPassword = PasswordUtils.hashPassword(password);
        
        // Create a new User object
        User user = new User(0, username, hashedPassword, email);
        
        // Use UserDAO to register the user
        Connection connection = DBConnection.getConnection();

        UserDAO userDAO = new UserDAO(connection);
        boolean isRegistered = userDAO.registerUser(user);

        // Redirect based on registration success
        if (isRegistered) {
            response.sendRedirect("login.jsp?success=Account created");
        } else {
            response.sendRedirect("register.jsp?error=Could not register");
        }
    }
}
