package com.vlsu.com.vlsu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTools {

    private Connection connection;

    public final void connect(final String host, final int port, final String database) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        final String connectionString = String.format("jdbc:mysql://%s:%d/%s?user=root&password=root", host, port, database);
        connection = DriverManager.getConnection(connectionString);
    }

    public Connection getConnection() {
        return connection;
    }
}
