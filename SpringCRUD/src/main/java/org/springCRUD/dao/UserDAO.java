package org.springCRUD.dao;

import java.util.List;
import org.springCRUD.model.User;

public interface UserDAO {
    List<User> allUsers();

    void add(User var1);

    void delete(User var1);

    void edit(User var1);

    User getById(Long var1);

    User getByLogin(String var1);
}