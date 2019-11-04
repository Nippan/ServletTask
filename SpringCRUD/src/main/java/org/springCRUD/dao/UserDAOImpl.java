package org.springCRUD.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> allUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM User").list();
    }

    @Override
    public void add(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void delete(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void edit(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User getById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (User)session.get(User.class, id);
    }

    @Override
    public User getByLogin(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        return (User)session.createQuery("FROM User WHERE login ='" + login + "'").uniqueResult();
    }
}