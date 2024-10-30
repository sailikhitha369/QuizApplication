package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuizDAO;
import models.Question;
import models.Quiz;
import utils.DBConnection;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the list of quizzes for the user to select from
    	Connection connection = DBConnection.getConnection();

    	QuizDAO quizDAO = new QuizDAO(connection);
        List<Quiz> quizzes = quizDAO.getAllQuizzes();
        request.setAttribute("quizzes", quizzes);
        request.getRequestDispatcher("jsp/quiz.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        
        // Fetch the quiz questions
        Connection connection = DBConnection.getConnection();

        QuizDAO quizDAO = new QuizDAO(connection);
        List<Question> questions = quizDAO.getQuestionsForQuiz(quizId);
        // Pass the questions to the JSP page
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("jsp/takeQuiz.jsp").forward(request, response);
    }
}
