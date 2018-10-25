package com.vlsu.com.vlsu;

import java.sql.SQLException;

public class App {
    private static final String HOST = "localhost";
    private static final int PORT = 3306;

    private final JDBCTools tools;

    public static void main(String[] args) {
        //Entry point
        new App().run();
    }

    public App() {
        tools = new JDBCTools();
    }

    private void run() {
        try {
            tools.connect(HOST, PORT, "tools_database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
