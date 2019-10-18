package dao;

import model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public interface UserDaoFactory {
    User getUserByLogin(String login) throws  SQLException;
    List<User> getAllUsers() throws SQLException;
    void addUser(String name, String login, String password, String role) throws SQLException;
    void updateUser(int id, String name, String pass) throws SQLException;
    void deleteUser(String login) throws SQLException;
}
