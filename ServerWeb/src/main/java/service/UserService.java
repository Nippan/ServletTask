package service;

import dao.UserDaoFactory;
import dao.UserDaoHibernateImpl;
import dao.UserDaoJDBCimpl;
import model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class UserService {
    private static UserService userService;
    private String role = null;

    private UserService() {

    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public boolean addUser(String name, String login, String password, String role) {
        try {
            getUserDAO().addUser(name, login, password, role);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String login)
    {
        try {
            getUserDAO().deleteUser(login);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(int id, String name, String password) {
        try {
            getUserDAO().updateUser(id, name, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            return getUserDAO().getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public  User getUserByLogin(String login) {
        try {
            return getUserDAO().getUserByLogin(login);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean validateClient(String login, String password) {
        for (User user:getAllUsers()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                role = user.getRole();
                return true;
            }
        }
        return false;
    }

    public String getRole() {
        return role;
    }

    private UserDaoFactory getUserDAO() throws IOException {
        String path = "C:\\Users\\1\\IdeaProjects\\ServerWeb\\src\\main\\resources\\config.properties";
        FileInputStream fis = new FileInputStream(path);
        Properties property = new Properties();
        property.load(fis);
        String conn = property.getProperty("typeConnection");
        if (conn.equals("hibernate")) {
            return new UserDaoHibernateImpl();
        } else if (conn.equals("jdbc")) {
            return new UserDaoJDBCimpl();
        } else {
            System.out.println("Неизвестный тип подключения к базе данных");
        }
        return null;
    }
}
