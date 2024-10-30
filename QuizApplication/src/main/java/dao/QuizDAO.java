package dao;

import models.Quiz;
import models.Result;
import models.User;
import models.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizDAO {
    private final Connection connection;

    public QuizDAO(Connection connection) {
        this.connection = connection;
    }

    // Retrieve all quizzes from the database
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT * FROM quizzes";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Quiz quiz = new Quiz(rs.getInt("id"), rs.getString("title"));
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching quizzes: " + e.getMessage());
        }
        return quizzes;
    }

    // Conduct a quiz for the user
    public int takeQuiz(Scanner scanner, Quiz quiz, User user) {
        int score = 0;
        String query = "SELECT * FROM questions WHERE quiz_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quiz.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("question"));
                    System.out.println("1: " + rs.getString("option1"));
                    System.out.println("2: " + rs.getString("option2"));
                    System.out.println("3: " + rs.getString("option3"));
                    System.out.println("4: " + rs.getString("option4"));
                    
                    int correctOption = rs.getInt("correct_option");
                    System.out.print("Your answer: ");
                    int userAnswer = Integer.parseInt(scanner.nextLine().trim());
                    
                    if (userAnswer == correctOption) {
                        score++;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during quiz: " + e.getMessage());
        }
        saveResult(user, quiz, score);
        return score;
    }

    private void saveResult(User user, Quiz quiz, int score) {
        String query = "INSERT INTO results (user_id, quiz_id, score) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, user.getUserId());
            stmt.setInt(2, quiz.getId());
            stmt.setInt(3, score);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving quiz result: " + e.getMessage());
        }
    }
    
 // Retrieve all questions for a specific quiz
    public List<Question> getQuestionsForQuiz(int quizId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions WHERE quiz_id = ?";
        

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quizId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                	List<String> options = new ArrayList<>();
                	options.add(rs.getString("option1"));
                	options.add(rs.getString("option2"));
                	options.add(rs.getString("option3"));
                	options.add(rs.getString("option4"));

                	Question question = new Question(
                	    rs.getInt("id"),
                	    rs.getString("question"),
                	    options,
                	    rs.getInt("correct_option")
                	);

                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching questions for quiz: " + e.getMessage());
        }
        return questions;
    }


    // Retrieve quiz results for a user
    public List<Result> getUserResults(User user) {
        List<Result> results = new ArrayList<>();
        String query = "SELECT quizzes.title, results.score, COUNT(questions.id) AS total_questions " +
                       "FROM results " +
                       "JOIN quizzes ON results.quiz_id = quizzes.id " +
                       "JOIN questions ON questions.quiz_id = quizzes.id " +
                       "WHERE results.user_id = ? " +
                       "GROUP BY quizzes.title, results.score";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, user.getUserId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Result result = new Result(rs.getString("title"), rs.getInt("score"), rs.getInt("total_questions"));
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching results: " + e.getMessage());
        }
        return results;
    }

    // Retrieve leaderboard data
    public List<Result> getLeaderboard() {
        List<Result> leaderboard = new ArrayList<>();
        String query = "SELECT users.username, quizzes.title, results.score " +
                       "FROM results " +
                       "JOIN users ON results.user_id = users.id " +
                       "JOIN quizzes ON results.quiz_id = quizzes.id " +
                       "ORDER BY results.score DESC LIMIT 10";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Result entry = new Result(rs.getString("username"), rs.getString("title"), rs.getInt("score"));
                leaderboard.add(entry);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching leaderboard: " + e.getMessage());
        }
        return leaderboard;
    }
}
