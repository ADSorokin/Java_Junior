package ru.sorokinad.service;

import ru.sorokinad.dao.CourseDao;
import ru.sorokinad.dao.CourseDaoImpl;
import ru.sorokinad.model.Course;

import java.util.List;

public class CourseService {
    private final CourseDao courseDao = new CourseDaoImpl();

    public void addCourse(Course course) {
        courseDao.saveCourse(course);
    }

    public List<Course> listCourses() {
        return courseDao.getAllCourses();
    }

    public Course getCourse(int id) {
        return courseDao.getCourseById(id);
    }

    public void updateCourse(Course course) {
        courseDao.updateCourse(course);
    }

    public void deleteCourse(int id) {
        courseDao.deleteCourse(id);
    }
}