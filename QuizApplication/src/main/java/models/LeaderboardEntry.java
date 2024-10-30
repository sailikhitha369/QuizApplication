package models;

public class LeaderboardEntry {
    private String username;
    private String quizTitle;
    private int score;

    // Constructor
    public LeaderboardEntry(String username, String quizTitle, int score) {
        this.username = username;
        this.quizTitle = quizTitle;
        this.score = score;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
