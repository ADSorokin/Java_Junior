package ru.sorokinad.config;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sorokinad.model.Course;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            return new Configuration().configure().addAnnotatedClass(Course.class).buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
