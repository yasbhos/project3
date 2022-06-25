package ir.ac.kntu.db;

import ir.ac.kntu.model.Course;

import java.util.ArrayList;

public class CourseDB {
    private ArrayList<Course> courses;

    public CourseDB(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public boolean addCourse(Course course) {
        return courses.add(course);
    }

    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    public boolean containsCourse(Course course) {
        return courses.contains(course);
    }

    public Course getCourseByName(String name) {
        return courses.stream().filter(course -> course.getName().equals(name)).findFirst().orElse(null);
    }
}
