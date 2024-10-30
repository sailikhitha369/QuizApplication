package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import models.User;
import utils.DBConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Retrieve user from the database using the username
        Connection connection = DBConnection.getConnection();

        UserDAO userDAO = new UserDAO(connection);
        User user = userDAO.authenticateUser(username, password); // Change method to authenticateUser

        // Verify user credentials
        if (user != null) {
            HttpSession session = request.getSession(); // Create a session if one doesn't exist
            session.setAttribute("user", user); // Store user in session
            response.sendRedirect("dashboard.jsp"); // Redirect to dashboard
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials"); // Redirect back to login with error message
        }
    }
}
