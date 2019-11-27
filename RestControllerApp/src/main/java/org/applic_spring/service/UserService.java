package org.applic_spring.service;

import org.applic_spring.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    List<User> getUsers();
    User getByName(String name);
    void addUser(User user, String role);
    void editUser(User user, String name, String email, String pass);
    void deleteUser(User user);
}
