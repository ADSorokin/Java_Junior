package ru.sorokinad.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sorokinad.config.HibernateUtil;
import ru.sorokinad.models.Message;

import java.util.List;

public class MessageDaoImpl implements MessageDao {

    @Override
    public void saveMessage(Message message) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public List<Message> getAllMessages() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Message", Message.class).list();
        }
    }
}