package com.karmakind.db;

import com.karmakind.model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Simple DAO skeleton. Uses prepared statements and basic CRUD operations.
 * This is intentionally straightforward for education / starter purposes.
 */
public class TaskDAO {
    public void save(Task t) throws SQLException {
        String sql = "INSERT INTO tasks (name, description, category, karma_value, priority, is_completed, task_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getName());
            ps.setString(2, null);
            ps.setString(3, t.getClass().getSimpleName());
            ps.setInt(4, t.getKarmaValue());
            ps.setInt(5, 0);
            ps.setBoolean(6, t.isCompleted());
            ps.setString(7, t.getClass().getSimpleName());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) t.setId(rs.getInt(1));
            }
        }
    }

    public List<Task> findAll() throws SQLException {
        List<Task> out = new ArrayList<>();
        String sql = "SELECT id, name, karma_value, is_completed, task_type FROM tasks";
        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Task t = new Task(rs.getString("name"));
                t.setId(rs.getInt("id"));
                t.setKarmaValue(rs.getInt("karma_value"));
                t.setCompleted(rs.getBoolean("is_completed"));
                out.add(t);
            }
        }
        return out;
    }
}
