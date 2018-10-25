package com.vlsu.com.vlsu.dao;

import com.vlsu.com.vlsu.models.Enchant;
import com.vlsu.com.vlsu.models.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ToolDAO extends AbstractDAO<Tool, Integer> {

    public ToolDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Tool> getAll() throws UnsupportedOperationException {
        final List<Tool> tools = new LinkedList<>();

        try {
            final PreparedStatement preparedStatement = getPreparedStatement("select * from tool");

            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    final Tool tool = new Tool(result.getString("name"));
                    tool.setDamage(result.getFloat("damage"))
                            .setHardness(result.getFloat("hardness"));
                    tools.add(tool);
                }
            } finally {
                closePreparedStatement(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }

        return tools;
    }

    @Override
    public boolean update(int id, Tool entity) throws UnsupportedOperationException {
        final boolean actionPerformed;
        try {
            final PreparedStatement preparedStatement = getPreparedStatement("update tool set name = ?, hardness = ?, damage = ? where id = ?");

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setFloat(2, entity.getHardness());
            preparedStatement.setFloat(3, entity.getDamage());
            preparedStatement.setInt(4, id);

            actionPerformed = preparedStatement.executeUpdate() == 1;

            closePreparedStatement(preparedStatement);
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }

        return actionPerformed;
    }

    @Override
    public Tool getById(Integer id) throws UnsupportedOperationException {
        final Tool tool;

        try {
            final PreparedStatement preparedStatement = getPreparedStatement("select * from tool where id = ?");
            preparedStatement.setInt(1, id);

            try (ResultSet result = preparedStatement.executeQuery()){
                if (result.next()) {
                    tool = new Tool(result.getString("name"));

                    final Enchant enchant = new EnchantDAO(getConnection()).getById(result.getInt("enchant_id"));
                    tool.setEnchant(enchant)
                            .setDamage(result.getFloat("damage"))
                            .setHardness(result.getFloat("hardness"));
                } else {
                    tool = null; //hardcoded i know
                }
            } finally {
                closePreparedStatement(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }

        return tool;
    }

    @Override
    public boolean delete(Integer id) throws UnsupportedOperationException {
        final boolean actionPerformed;

        try {
            final PreparedStatement preparedStatement = getPreparedStatement("delete from tool where id = ?");
            preparedStatement.setInt(1, id);

            actionPerformed = preparedStatement.executeUpdate() == 1;

            closePreparedStatement(preparedStatement);
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }

        return actionPerformed;
    }

    @Override
    public boolean create(Tool entity) throws UnsupportedOperationException {
        final boolean actionPerformed;

        try {
            final PreparedStatement preparedStatement = getPreparedStatement("insert into tool (name, hardness, damage) values (?, ?, ?)");

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setFloat(2, entity.getHardness());
            preparedStatement.setFloat(3, entity.getDamage());

            actionPerformed = preparedStatement.executeUpdate() == 1;

            closePreparedStatement(preparedStatement);

        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getSQLState());
        }
        return actionPerformed;
    }
}
