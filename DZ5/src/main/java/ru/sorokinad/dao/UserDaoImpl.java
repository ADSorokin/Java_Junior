package ru.sorokinad.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sorokinad.config.HibernateUtil;
import ru.sorokinad.models.User;

public class UserDaoImpl implements UserDao {

    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public User getUserByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class).setParameter("username", username).uniqueResult();
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class).setParameter("username", username).uniqueResult();
            return count != null && count > 0;
        }
    }
}