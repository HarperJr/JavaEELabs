package com.vlsu.com.vlsu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<E, K> {

    private final Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<E> getAll() throws UnsupportedOperationException;
    public abstract boolean update(int id, E entity) throws UnsupportedOperationException;
    public abstract E getById(K id) throws UnsupportedOperationException;
    public abstract boolean delete(K id) throws UnsupportedOperationException ;
    public abstract boolean create(E entity) throws UnsupportedOperationException;

    public final Connection getConnection() {
        return connection;
    }

    protected PreparedStatement getPreparedStatement(String sql) throws SQLException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (NullPointerException ex) {
            throw new SQLException("Connection is null");
        }

        return preparedStatement;
    }

    protected void closePreparedStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
