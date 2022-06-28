package ir.ac.kntu.menu.course.admin;

import ir.ac.kntu.db.CourseDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.Course;
import ir.ac.kntu.model.DateTime;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.DateTimeUtility;
import ir.ac.kntu.util.ScannerWrapper;

public class AdminCourseMenu implements Menu {
    private User currentAdmin;

    private Course course;

    private CourseDB courseDB;

    public AdminCourseMenu(User currentAdmin, Course course, CourseDB courseDB) {
        this.currentAdmin = currentAdmin;
        this.course = course;
        this.courseDB = courseDB;
    }

    @Override
    public void menu() {
        AdminCourseMenuOption adminCourseMenuOption;
        do {
            adminCourseMenuOption = ScannerWrapper.getInstance().readEnum(AdminCourseMenuOption.values(), "COURSE MENU");
            handleTheOption(adminCourseMenuOption);
        } while (adminCourseMenuOption != AdminCourseMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((AdminCourseMenuOption) option) {
            case EDIT_NAME -> editName();
            case EDIT_INSTITUTE -> editInstitute();
            case EDIT_START_DATETIME -> editStartDateTime();
            case EDIT_STATUS -> editStatus();
            case EDIT_PASSWORD -> editPassword();
            case EDIT_DESCRIPTION -> editDescription();
            default -> {
            }
        }
    }

    private void editName() {
        String name = ScannerWrapper.getInstance().readString("Enter new name: ");
        course.setName(name);
        System.out.println("Successfully changed");
    }

    private void editInstitute() {
        String institute = ScannerWrapper.getInstance().readString("Enter new institute: ");
        course.setInstitute(institute);
        System.out.println("Successfully changed");
    }

    private void editStartDateTime() {
        DateTime dateTime = DateTimeUtility.readDateTime("Enter new date-time: ");
        course.setStartDate(dateTime);
        System.out.println("Successfully changed");
    }

    private void editStatus() {
        System.out.println("Enter new status: ");
        Course.CourseStatus status = ScannerWrapper.getInstance().readEnum(Course.CourseStatus.values());
        course.setStatus(status);
        System.out.println("Successfully changed");
    }

    private void editPassword() {
        String oldPassword = ScannerWrapper.getInstance().readPassword("Enter old password: ");
        if (!course.getHashedPassword().equals(Cipher.sha256(oldPassword))) {
            System.out.println("Wrong password");
            return;
        }

        String password = ScannerWrapper.getInstance().readPassword("Enter new password: ");
        course.setPassword(password);
        System.out.println("Successfully changed");
    }

    private void editDescription() {
        String description = ScannerWrapper.getInstance().readString("Enter new description: ");
        course.setDescription(description);
        System.out.println("Successfully changed");
    }
}
