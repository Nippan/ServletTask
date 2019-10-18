package dao;

import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

public class UserDaoHibernateImpl implements UserDaoFactory {
    private Session session;
    private final SessionFactory sessionFactory = createSessionFactory();

    public UserDaoHibernateImpl() {
        this.session = sessionFactory.openSession();
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public User getUserByLogin(String login) {
        User user = (User) session.createQuery("FROM User WHERE login = '" + login + "'").uniqueResult();
        session.close();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> cars = session.createQuery("FROM User").list();
        session.close();
        return cars;
    }

    @Override
    public void addUser(String name, String login, String password, String role) {
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, login, password, role));
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(int id, String name, String pass) {
        Transaction transaction = session.beginTransaction();
        String hql = "UPDATE User "
                + "SET name = :name "
                +   ", password = :pass "
                +  " where id = :idParam";
        Query query = session.createQuery(hql);

        query.setParameter("idParam"  , id);
        query.setParameter("name"     , name);
        query.setParameter("pass", pass);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(String login) {
        Transaction transaction = session.beginTransaction();
        String hql = "DELETE User WHERE login = '" + login + "' ";
        session.createQuery(hql).executeUpdate();
        transaction.commit();
        session.close();
    }

}
