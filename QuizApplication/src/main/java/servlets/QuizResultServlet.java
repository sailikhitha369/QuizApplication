package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuizDAO;
import dao.ResultDAO;
import models.Question;
import models.Result;
import models.User;
import utils.DBConnection;

@WebServlet("/submitQuiz")
public class QuizResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve quizId and userId
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        User user = (User) session.getAttribute("user"); // Cast the user object
        int userId = user != null ? user.getUserId() : -1; // Handle case where user is null

        // Retrieve the questions and user's answers
        Connection connection = DBConnection.getConnection();

        QuizDAO quizDAO = new QuizDAO(connection);
        List<Question> questions = quizDAO.getQuestionsForQuiz(quizId);
        int score = 0;

        for (Question question : questions) {
            String selectedAnswer = request.getParameter("answer_" + question.getQuestionId());
            if (selectedAnswer != null && Integer.parseInt(selectedAnswer) == question.getCorrectOption()) {
                score++;
            }
        }

        // Store the result in the database
        Result result = new Result(userId, quizId, score);
        ResultDAO.saveResult(result);

        // Set attributes to display in the results page
        request.setAttribute("score", score);
        request.setAttribute("totalQuestions", questions.size());
        request.getRequestDispatcher("jsp/results.jsp").forward(request, response);
    }
}
