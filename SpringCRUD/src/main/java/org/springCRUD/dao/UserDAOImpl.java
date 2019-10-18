package org.springCRUD.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springCRUD.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> allUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Person").list();
    }

    @Override
    public void add(Person user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void delete(Person user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void edit(Person user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public Person getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

}