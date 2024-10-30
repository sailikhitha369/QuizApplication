package dao;

import models.Result;
import utils.DBConnection;

import java.sql.*;

public class ResultDAO {
    public static void saveResult(Result result) {
        String sql = "INSERT INTO results (user_id, quiz_id, score) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, result.getUserId());
            stmt.setInt(2, result.getQuizId());
            stmt.setInt(3, result.getScore());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
