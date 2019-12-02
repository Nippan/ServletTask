package org.applic_spring.service;

import org.applic_spring.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    List<User> getUsers();
    User getById(Long id);
    void addUser(User user, String role);
    User editUser(Long id, User userDetails);
    void deleteUser(User user);
}
