package org.applic_spring.service;

import org.applic_spring.model.Role;
import org.applic_spring.model.User;
import org.applic_spring.repository.UserRepo;
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
    public User getById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User getUserByName(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public void addUser(User user, String role) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.valueOf(role)));
        userRepo.save(user);
    }

    @Override
    public User editUser(Long id, User userDetails) {
        User user = getById(id);
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        userRepo.save(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);
    }
}
