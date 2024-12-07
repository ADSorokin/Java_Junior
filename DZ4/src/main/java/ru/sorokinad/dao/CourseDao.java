package ru.sorokinad.dao;

import ru.sorokinad.model.Course;

import java.util.List;

public interface CourseDao {
    void saveCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(int id);

    void updateCourse(Course course);

    void deleteCourse(int id);
}