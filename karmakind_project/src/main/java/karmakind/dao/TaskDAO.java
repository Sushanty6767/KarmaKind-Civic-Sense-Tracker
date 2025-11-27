package com.karmakind.dao;

import com.karmakind.model.CivicTask;
import com.karmakind.model.Habit;
import com.karmakind.model.Task;
import com.karmakind.util.DataAccessException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements GenericDAO<Task> {
    private final DatabaseManager db;

    public TaskDAO() throws SQLException {
        this.db = DatabaseManager.getInstance();
    }

    @Override
    public void save(Task task) throws DataAccessException {
        String sql = "INSERT INTO tasks(title,description,base_points,due_date,type,impact,completed,user_id) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, task.title);
            ps.setString(2, task.description);
            ps.setInt(3, task.basePoints);
            ps.setString(4, task.dueDate == null ? null : task.dueDate.toString());
            String type = (task instanceof CivicTask) ? "CIVIC" : (task instanceof Habit ? "HABIT" : "TASK");
            ps.setString(5, type);
            String impact = (task instanceof CivicTask) ? ((CivicTask) task).impact.name() : null;
            ps.setString(6, impact);
            ps.setInt(7, task.completed ? 1 : 0);
            ps.setInt(8, task.userId);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) task.id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to save task", e);
        }
    }

    @Override
    public Task findById(int id) throws DataAccessException {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find task", e);
        }
    }

    @Override
    public List<Task> findAll() throws DataAccessException {
        String sql = "SELECT * FROM tasks";
        List<Task> list = new ArrayList<>();
        try (Connection conn = db.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new DataAccessException("Failed to list tasks", e);
        }
        return list;
    }

    @Override
    public void update(Task task) throws DataAccessException {
        String sql = "UPDATE tasks SET title=?,description=?,base_points=?,due_date=?,type=?,impact=?,completed=?,user_id=? WHERE id=?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, task.title);
            ps.setString(2, task.description);
            ps.setInt(3, task.basePoints);
            ps.setString(4, task.dueDate == null ? null : task.dueDate.toString());
            String type = (task instanceof CivicTask) ? "CIVIC" : (task instanceof Habit ? "HABIT" : "TASK");
            ps.setString(5, type);
            String impact = (task instanceof CivicTask) ? ((CivicTask) task).impact.name() : null;
            ps.setString(6, impact);
            ps.setInt(7, task.completed ? 1 : 0);
            ps.setInt(8, task.userId);
            ps.setInt(9, task.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to update task", e);
        }
    }

    @Override
    public void delete(int id) throws DataAccessException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete task", e);
        }
    }

    private Task mapRow(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String desc = rs.getString("description");
        int base = rs.getInt("base_points");
        String due = rs.getString("due_date");
        LocalDate dueDate = due == null ? null : LocalDate.parse(due);
        String impact = rs.getString("impact");
        int userId = rs.getInt("user_id");
        Task t;
        if ("CIVIC".equalsIgnoreCase(type)) {
            CivicTask ct = new CivicTask(id, title, desc, base, dueDate, CivicTask.Impact.valueOf(impact == null ? "LOW" : impact), userId);
            ct.completed = rs.getInt("completed") == 1;
            t = ct;
        } else if ("HABIT".equalsIgnoreCase(type)) {
            Habit h = new Habit(id, title, desc, base, dueDate, userId);
            h.completed = rs.getInt("completed") == 1;
            t = h;
        } else {
            Habit h = new Habit(id, title, desc, base, dueDate, userId);
            h.completed = rs.getInt("completed") == 1;
            t = h;
        }
        return t;
    }
}
