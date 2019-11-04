package org.springCRUD.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springCRUD.dao.RoleDAO;
import org.springCRUD.dao.UserDAO;
import org.springCRUD.model.Role;
import org.springCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Transactional
    @Override
    public List<User> allUsers() {
        return this.userDAO.allUsers();
    }

    @Transactional
    @Override
    public void add(User user, String roleUser) {
        Set<Role> role = new HashSet();
        if (roleUser.equals("ADMIN")) {
            role.add(this.roleDAO.getRoleById(1L));
            user.setRoles(role);
            this.userDAO.add(user);
        } else {
            role.add(this.roleDAO.getRoleById(2L));
            user.setRoles(role);
            this.userDAO.add(user);
        }

    }

    @Transactional
    @Override
    public void delete(Long id) {
        this.userDAO.delete(this.getById(id));
    }

    @Transactional
    @Override
    public void edit(User user) {
        this.userDAO.edit(user);
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return this.userDAO.getById(id);
    }

    @Transactional
    @Override
    public User getByLogin(String login) {
        return this.userDAO.getByLogin(login);
    }
}
