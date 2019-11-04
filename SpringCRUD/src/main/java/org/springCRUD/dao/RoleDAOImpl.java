package org.springCRUD.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springCRUD.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDAOImpl implements RoleDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getRoleById(Long roleId) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role)session.load(Role.class, roleId);
        return role;
    }

}