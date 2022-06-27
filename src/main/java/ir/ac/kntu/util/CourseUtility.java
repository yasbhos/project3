package ir.ac.kntu.util;

import ir.ac.kntu.model.Course;
import ir.ac.kntu.model.DateTime;
import ir.ac.kntu.model.User;
import ir.ac.kntu.model.Course.CourseStatus;

public class CourseUtility {
    public static Course readCourse(User owner, String massage) {
        System.out.println(massage);

        String name = ScannerWrapper.getInstance().readString("Enter name: ");
        String institute = ScannerWrapper.getInstance().readString("Enter institute: ");
        DateTime startDate = DateTimeUtility.readDate("Enter start-date");
        CourseStatus status = ScannerWrapper.getInstance().readEnum(CourseStatus.values(), "COURSE STATUS");
        String password = null;
        if (status == CourseStatus.OPEN_PRIVATE) {
            password = ScannerWrapper.getInstance().readPassword("Enter password: ");
            if (password == null) {
                return null;
            }
        }
        String description = ScannerWrapper.getInstance().readString("Enter description: ");

        return new Course(owner, name, institute, startDate, status, password, description);
    }

}
