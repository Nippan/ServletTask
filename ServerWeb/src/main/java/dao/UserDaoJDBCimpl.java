package dao;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCimpl implements UserDaoFactory {
    private final Connection connection = DBHelper.getInstance().getConnection();

    public UserDaoJDBCimpl() {}


    @Override
    public User getUserByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM users WHERE login = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.execute();
        ResultSet result = stmt.getResultSet();
        result.next();
        User user = new User(
                result.getInt(1),
                result.getString(3),
                result.getString(2),
                result.getString(4),
                result.getString(5));
        result.close();
        stmt.close();
        return user;
    }

    @Override
    public List<User> getAllUsers() throws SQLException{
        Statement stm = connection.createStatement();
        stm.execute("SELECT * FROM users");
        ResultSet resultSet = stm.getResultSet();
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            User client = new User(
                    resultSet.getString(3),
                    resultSet.getString(2),
                    resultSet.getString(4),
                    resultSet.getString(5));
            list.add(client);
        }
        resultSet.close();
        stm.close();
        return list;
    }

    @Override
    public void addUser(String name, String login, String password, String role) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("INSERT INTO users (name, login, password) VALUES ('" +
                name + "', '" +
                login + "', '" +
                password + "')");
        stmt.close();
    }

    @Override
    public void updateUser(int id, String name, String pass) throws SQLException {
        String sql = "UPDATE users SET name = ?, password = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, pass);
        stmt.setInt(3, id);
        stmt.execute();
        stmt.close();
    }

    @Override
    public void deleteUser(String login) throws SQLException {
        String sql = "DELETE FROM users WHERE login = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.execute();
        stmt.close();
    }
}
