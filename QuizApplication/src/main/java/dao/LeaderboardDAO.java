package dao;

import models.LeaderboardEntry;
import utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardDAO {
    public static List<LeaderboardEntry> getTopScores() {
        List<LeaderboardEntry> leaderboard = new ArrayList<>();
        String sql = "SELECT u.username, q.title, r.score FROM results r JOIN users u ON r.user_id = u.user_id JOIN quizzes q ON r.quiz_id = q.quiz_id ORDER BY r.score DESC LIMIT 10";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                LeaderboardEntry entry = new LeaderboardEntry(rs.getString("username"), rs.getString("title"), rs.getInt("score"));
                leaderboard.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }
}

