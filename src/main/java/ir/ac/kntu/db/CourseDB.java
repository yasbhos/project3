package ir.ac.kntu.db;

import ir.ac.kntu.model.Course;
import ir.ac.kntu.util.ScannerWrapper;

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

    public Course getCourse() {
        String input = ScannerWrapper.getInstance().readString("Search by Name, Lecturer or Institute?(N/L/I)");
        switch (input) {
            case "N" -> searchCourseByName();
            case "L" -> searchCourseByLecturer();
            case "I" -> searchCourseByInstitute();
            default -> System.out.println("Invalid option");
        }

        return null;
    }

    public Course searchCourseByName() {
        String name = ScannerWrapper.getInstance().readString("Enter course name: ");

        for (Course course : courses) {
            if (course.getName().equals(name)) {
                System.out.println(course);
            }
        }
        String id = ScannerWrapper.getInstance().readString("Enter course id: ");

        return getCourseByID(id);
    }


    public Course searchCourseByLecturer() {
        String lecturer = ScannerWrapper.getInstance().readString("Enter course lecturer: ");

        for (Course course : courses) {
            if (course.getLecturer().equals(lecturer)) {
                System.out.println(course);
            }
        }
        String id = ScannerWrapper.getInstance().readString("Enter course id: ");

        return getCourseByID(id);
    }

    public Course searchCourseByInstitute() {
        String institute = ScannerWrapper.getInstance().readString("Enter course institute: ");

        for (Course course : courses) {
            if (course.getInstitute().equals(institute)) {
                System.out.println(course);
            }
        }
        String id = ScannerWrapper.getInstance().readString("Enter course id: ");

        return getCourseByID(id);
    }


    public Course getCourseByID(String id) {
        return courses.stream().filter(course -> course.getId().equals(id)).findFirst().orElse(null);
    }
}
