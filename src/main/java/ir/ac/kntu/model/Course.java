package ir.ac.kntu.model;

import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.Objects;

public class Course {
    public enum CourseStatus {
        OPEN_PUBLIC, OPEN_PRIVATE, CLOSE
    }

    private String name;
    private String institute;
    private User lecturer;
    private DateTime startDate;
    private CourseStatus status;
    private String hashedPassword;
    private String description;
    private ArrayList<User> register;
    private ArrayList<Assignment> assignments;

    public Course(String name, String institute, User lecturer, DateTime startDate, CourseStatus status,
                  String password, String description) {
        this.name = name;
        this.institute = institute;
        this.lecturer = lecturer;
        this.startDate = startDate;
        this.status = status;
        this.hashedPassword = Cipher.sha256(password);
        this.description = description;
        this.register = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public User getLecturer() {
        return lecturer;
    }

    public void setLecturer(User lecturer) {
        this.lecturer = lecturer;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean addStudent(User student) {
        if (!register.contains(student)) {
            register.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(User student) {
        return register.remove(student);
    }

    public boolean containsStudent(User student) {
        return register.contains(student);
    }

    public boolean addAssignment(Assignment assignment) {
        if (!assignments.contains(assignment)) {
            assignments.add(assignment);
            return true;
        }
        return false;
    }

    public boolean removeAssignment(Assignment assignment) {
        return assignments.remove(assignment);
    }

    public Assignment searchAssignmentByName() {
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println(i + 1 + ". " + assignments.get(i).getName());
        }
        int index = ScannerWrapper.getInstance().readInt("Enter assignment index: ");
        if (index > 0 && index <= assignments.size()) {
            return assignments.get(index - 1);
        }
        System.out.println(); //TODO
        return null;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", institute='" + institute + '\'' +
                ", lecturer=" + lecturer +
                ", startDate:\n" + startDate +
                "\n, status=" + status +
                "\n, description='" + description + '\'' +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;

        return Objects.equals(name, course.name) && Objects.equals(institute, course.institute) && Objects.equals(lecturer, course.lecturer) && Objects.equals(startDate, course.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, institute, lecturer, startDate);
    }
}
