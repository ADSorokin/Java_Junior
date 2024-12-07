package ru.sorokinad;


import ru.sorokinad.model.Course;
import ru.sorokinad.service.CourseService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CourseService courseService = new CourseService();


        courseService.addCourse(new Course("Математика", 110));
        courseService.addCourse(new Course("Химия", 120));
        courseService.addCourse(new Course("Физика", 140));

        List<Course> courses = courseService.listCourses();
        System.out.println("Список курсов:");
        courses.forEach(System.out::println);

        Course courseToUpdate = courses.get(0);
        courseToUpdate.setTitle("Астрономия");
        courseToUpdate.setDuration(95);
        courseService.updateCourse(courseToUpdate);
        System.out.println("Курс обновлен: " + courseToUpdate);

        int courseIdToDelete = courses.get(1).getId();
        courseService.deleteCourse(courseIdToDelete);
        System.out.println("Курс с ID " + courseIdToDelete + " удален.");

        List<Course> remainingCourses = courseService.listCourses();
        System.out.println("Оставшиеся курсы:");
        remainingCourses.forEach(System.out::println);
    }
}