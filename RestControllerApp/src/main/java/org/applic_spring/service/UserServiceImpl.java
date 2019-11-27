package org.applic_spring.service;

import org.applic_spring.model.Role;
import org.applic_spring.model.User;
import org.applic_spring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getByName(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public void addUser(User user, String role) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.valueOf(role)));
        userRepo.save(user);
    }

    @Override
    public void editUser(User user, String name, String email, String pass) {
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(pass);
        userRepo.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);
    }
}
