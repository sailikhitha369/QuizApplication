package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LeaderboardDAO;
import models.LeaderboardEntry;

@WebServlet("/leaderboard")
public class LeaderboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch the top scores from the database
        List<LeaderboardEntry> leaderboard = LeaderboardDAO.getTopScores();

        // Set leaderboard as an attribute and forward to JSP
        request.setAttribute("leaderboard", leaderboard);
        request.getRequestDispatcher("jsp/leaderboard.jsp").forward(request, response);
    }
}
