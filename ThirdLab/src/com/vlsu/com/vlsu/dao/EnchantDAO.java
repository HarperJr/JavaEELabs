package com.vlsu.com.vlsu.dao;

import com.vlsu.com.vlsu.models.Enchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EnchantDAO extends AbstractDAO<Enchant, Integer> {

    public EnchantDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Enchant> getAll() throws UnsupportedOperationException {
        final List<Enchant> enchants = new LinkedList<>();

        try {
            final PreparedStatement preparedStatement = getPreparedStatement("select * from enchant");

            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    final Enchant enchant = new Enchant(result.getString("name"));
                    enchant.setDescription(result.getString("description"))
                            .setDuration(result.getTime("duration"));
                    enchants.add(enchant);
                }
            } finally {
                closePreparedStatement(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }

        return enchants;
    }

    @Override
    public boolean update(int id, Enchant entity) throws UnsupportedOperationException {
        final boolean actionPerformed;
        try {
            final PreparedStatement preparedStatement = getPreparedStatement("update enchant set name = ?, description = ?, duration = ? where id = ?");

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setTime(3, entity.getDuration());
            preparedStatement.setInt(4, id);

            actionPerformed = preparedStatement.executeUpdate() == 1;

            closePreparedStatement(preparedStatement);
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }

        return actionPerformed;
    }

    @Override
    public Enchant getById(Integer id) throws UnsupportedOperationException {
        final Enchant enchant;

        try {
            final PreparedStatement preparedStatement = getPreparedStatement("select * from enchant where id = ?");
            preparedStatement.setInt(1, id);

            try (ResultSet result = preparedStatement.executeQuery()){
                if (result.next()) {
                    enchant = new Enchant(result.getString("name"));

                    enchant.setDescription(result.getString("description"))
                            .setDuration(result.getTime("duration"));
                } else {
                    enchant = null; //hardcoded i know
                }
            } finally {
                closePreparedStatement(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }

        return enchant;
    }

    @Override
    public boolean delete(Integer id) throws UnsupportedOperationException {
        final boolean actionPerformed;

        try {
            final PreparedStatement preparedStatement = getPreparedStatement("delete from enchant where id = ?");
            preparedStatement.setInt(1, id);

            actionPerformed = preparedStatement.executeUpdate() == 1;

            closePreparedStatement(preparedStatement);
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }

        return actionPerformed;
    }

    @Override
    public boolean create(Enchant entity) throws UnsupportedOperationException {
        final boolean actionPerformed;

        try {
            final PreparedStatement preparedStatement = getPreparedStatement("insert into tool (name, description, duration) values (?, ?, ?)");

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setTime(3, entity.getDuration());

            actionPerformed = preparedStatement.executeUpdate() == 1;

            closePreparedStatement(preparedStatement);

        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }
        return actionPerformed;
    }
}
