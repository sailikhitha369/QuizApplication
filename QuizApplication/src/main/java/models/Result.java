// Result.java
package models;

public class Result {
    private int userId;
    private int quizId;
    private String quizTitle;
    private int score;
    private int totalQuestions;
    private String username; // For leaderboard display

    // Constructor for saving a result
    public Result(int userId, int quizId, int score) {
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
    }

    // Constructor for displaying results with title and total questions
    public Result(String quizTitle, int score, int totalQuestions) {
        this.quizTitle = quizTitle;
        this.score = score;
        this.totalQuestions = totalQuestions;
    }

    // Constructor for leaderboard
    public Result(String username, String quizTitle, int score) {
        this.username = username;
        this.quizTitle = quizTitle;
        this.score = score;
    }

    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getQuizId() { return quizId; }
    public void setQuizId(int quizId) { this.quizId = quizId; }

    public String getQuizTitle() { return quizTitle; }
    public void setQuizTitle(String quizTitle) { this.quizTitle = quizTitle; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
