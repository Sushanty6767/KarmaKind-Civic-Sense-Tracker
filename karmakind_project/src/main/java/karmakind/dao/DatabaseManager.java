package com.karmakind.dao;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Scanner;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:karmakind.db";
    private static DatabaseManager instance;

    private DatabaseManager() throws SQLException {
        // ensure driver loads and DB file is created
        try (Connection c = DriverManager.getConnection(URL)) {
            // no-op
        }
    }

    public static synchronized DatabaseManager getInstance() throws SQLException {
        if (instance == null) instance = new DatabaseManager();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void initializeSchema() {
        // read schema.sql from resources
        try (InputStream in = getClass().getResourceAsStream("/schema.sql")) {
            if (in == null) {
                System.err.println("schema.sql not found in resources");
                return;
            }
            String sql = new Scanner(in, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
                st.executeUpdate("PRAGMA foreign_keys = ON;");
                for (String stmt : sql.split(";")) {
                    String s = stmt.trim();
                    if (!s.isEmpty()) st.executeUpdate(s + ";");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
