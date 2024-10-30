package controllers;

import dao.QuizDAO;
import models.Quiz;
import models.Result;
import models.User;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class QuizController {
    private final QuizDAO quizDAO;

    public QuizController(Connection connection) {
        this.quizDAO = new QuizDAO(connection);
    }

    // Start a quiz for the user
    public void startQuiz(Scanner scanner, User user) {
        List<Quiz> quizzes = quizDAO.getAllQuizzes();
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available at this time.");
            return;
        }

        System.out.println("Available Quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }

        System.out.print("Select a quiz by number: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Please enter a number.");
            return;
        }

        if (choice < 1 || choice > quizzes.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Quiz selectedQuiz = quizzes.get(choice - 1);
        int score = quizDAO.takeQuiz(scanner, selectedQuiz, user);
        System.out.println("You scored: " + score + " out of " + selectedQuiz.getQuestions().size());
    }

    // View quiz results for the logged-in user
    public void viewQuizResults(User user) {
        List<Result> results = quizDAO.getUserResults(user);
        if (results.isEmpty()) {
            System.out.println("No results available.");
        } else {
            System.out.println("Your Quiz Results:");
            for (Result result : results) {
                System.out.println("Quiz: " + result.getQuizTitle() + " | Score: " + result.getScore() + "/" + result.getTotalQuestions());
            }
        }
    }

    // View the leaderboard
    public void viewLeaderboard() {
        List<Result> leaderboard = quizDAO.getLeaderboard();
        if (leaderboard.isEmpty()) {
            System.out.println("No leaderboard data available.");
        } else {
            System.out.println("Leaderboard:");
            for (Result entry : leaderboard) {
                System.out.println("User: " + entry.getUsername() + " | Quiz: " + entry.getQuizTitle() + " | Score: " + entry.getScore());
            }
        }
    }
}
