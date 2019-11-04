package org.springCRUD.service;

import java.util.List;
import org.springCRUD.model.User;

public interface UserService {
    List<User> allUsers();

    void add(User var1, String var2);

    void delete(Long var1);

    void edit(User var1);

    User getById(Long var1);

    User getByLogin(String var1);
}
