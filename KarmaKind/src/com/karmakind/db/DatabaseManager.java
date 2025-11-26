package com.karmakind.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Update the URL, USER, PASS constants to match your MySQL credentials.
 * If you don't have a DB ready, the app will run in memory-only mode.
 */
public class DatabaseManager {
    public static final String URL = "jdbc:mysql://localhost:3306/karmakind?useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASS = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
