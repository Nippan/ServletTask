package org.springCRUD.service;

import org.springCRUD.model.Person;

import java.util.List;

public interface UserService {
    List<Person> allUsers();
    void add(Person user);
    void delete(Person user);
    void edit(Person user);
    Person getById(int id);
}
