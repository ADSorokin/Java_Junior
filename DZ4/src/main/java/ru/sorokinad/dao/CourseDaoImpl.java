package ru.sorokinad.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sorokinad.config.HibernateUtil;
import ru.sorokinad.model.Course;

import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public void saveCourse(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public List<Course> getAllCourses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Course", Course.class).list();
        }
    }

    @Override
    public Course getCourseById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Course.class, id);
        }
    }

    @Override
    public void updateCourse(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            if (course != null) {
                session.delete(course);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
