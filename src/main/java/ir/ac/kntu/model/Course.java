package ir.ac.kntu.model;

import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.IdGenerator;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.Objects;

public class Course {
    public enum CourseStatus {
        OPEN_PUBLIC,
        OPEN_PRIVATE,
        CLOSE
    }

    private final String id;

    private String name;

    private String institute;

    private User lecturer;

    private DateTime startDate;

    private CourseStatus status;

    private String hashedPassword;

    private String description;

    private final ArrayList<User> register;

    private final ArrayList<Assignment> assignments;

    private final ArrayList<User> teacherAssistants;

    public Course(User owner, String name, String institute, DateTime startDate, CourseStatus status,
                  String password, String description) {
        this.id = IdGenerator.createID();
        this.name = name;
        this.institute = institute;
        this.startDate = startDate;
        this.status = status;
        this.hashedPassword = Cipher.sha256(password);
        this.description = description;
        this.register = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.teacherAssistants = new ArrayList<>();
        this.teacherAssistants.add(owner);
    }

    public String getId() {
        return id;
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

    public void setPassword(String password) {
        this.hashedPassword = Cipher.sha256(password);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean addStudent(User student) {
        return register.add(student);
    }

    public boolean removeStudent(User student) {
        return register.remove(student);
    }

    public boolean containsStudent(User student) {
        return register.contains(student);
    }

    public boolean addAssignment(Assignment assignment) {
        return assignments.add(assignment);
    }

    public boolean removeAssignment(Assignment assignment) {
        return assignments.remove(assignment);
    }

    public Assignment searchAssignment() {
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println(i + 1 + ". " + assignments.get(i).getName());
        }

        int index = ScannerWrapper.getInstance().readInt("Enter assignment index: ");
        if (index > 0 && index <= assignments.size()) {
            return assignments.get(index - 1);
        }
        System.out.println("Invalid option");

        return null;
    }

    public boolean isLecturer(User user) {
        if (this.lecturer == null) {
            return false;
        }

        return this.lecturer.equals(user);
    }

    public boolean isfTA(User user) {
        return teacherAssistants.contains(user);
    }

    public boolean isStudent(User user) {
        return register.contains(user);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", institute='" + institute + '\'' +
                ", lecturer=" + lecturer +
                "\nstartDate=" + startDate +
                "\nstatus=" + status +
                "\ndescription='" + description + '\'' +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course course)) {
            return false;
        }
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
