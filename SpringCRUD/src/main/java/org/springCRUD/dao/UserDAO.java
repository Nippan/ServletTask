package org.springCRUD.dao;


import org.springCRUD.model.Person;

import java.util.List;

public interface UserDAO {
    List<Person> allUsers();
    void add(Person user);
    void delete(Person user);
    void edit(Person user);
    Person getById(int id);
}