package org.springCRUD.service;

import org.springCRUD.dao.UserDAO;
import org.springCRUD.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<Person> allUsers() {
        return userDAO.allUsers();
    }

    @Override
    @Transactional
    public void add(Person user) {
        userDAO.add(user);
    }

    @Override
    @Transactional
    public void delete(Person user) {
        userDAO.delete(user);
    }

    @Override
    @Transactional
    public void edit(Person user) {
        userDAO.edit(user);
    }

    @Override
    @Transactional
    public Person getById(int id) {
        return userDAO.getById(id);
    }
}
