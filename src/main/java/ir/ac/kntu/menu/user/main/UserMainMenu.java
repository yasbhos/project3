package ir.ac.kntu.menu.user.main;

import ir.ac.kntu.db.ContestDB;
import ir.ac.kntu.db.CourseDB;
import ir.ac.kntu.db.QuestionDB;
import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.user.change.UserChangeMenu;
import ir.ac.kntu.model.*;
import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.CourseUtility;
import ir.ac.kntu.util.DateTimeUtility;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class UserMainMenu implements Menu {
    private User currentUser;
    private UserDB userDB;
    private CourseDB courseDB;
    private ContestDB contestDB;
    private QuestionDB questionDB;

    public UserMainMenu(User currentUser, UserDB userDB, CourseDB courseDB, ContestDB contestDB, QuestionDB questionDB) {
        this.currentUser = currentUser;
        this.userDB = userDB;
        this.courseDB = courseDB;
        this.contestDB = contestDB;
        this.questionDB = questionDB;
    }

    @Override
    public void handleMenu() {
        UserMainMenuOption userMainMenuOption;
        do {
            userMainMenuOption = ScannerWrapper.getInstance().readEnum(UserMainMenuOption.values(), "User Main Menu");
            handleTheOption(userMainMenuOption);
        } while (userMainMenuOption != UserMainMenuOption.LOGOUT);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((UserMainMenuOption) option) {
            case REGISTER_TO_COURSE -> registerToCourse();
            case REGISTER_TO_CONTEST -> registerToContest();
            case ADD_COURSE -> addCourse();
            case ACCOUNT -> account();
            case LOGOUT -> {

            }
            default -> {
            }
        }
    }

    private void registerToCourse() {
        Course course = courseDB.getCourse();
        if (!checkCourseGuards(course)) {
            return;
        }

        if (!course.addStudent(currentUser)) {
            System.out.println("Already registered");
            return;
        }
        System.out.println("Successfully registered");
    }

    private boolean checkCourseGuards(Course course) {
        if (course == null) {
            return false;
        }
        if (course.getStatus() == Course.CourseStatus.CLOSE) {
            System.out.println("This course is close");
            return false;
        }
        if (course.getStatus() == Course.CourseStatus.OPEN_PUBLIC) {
            return true;
        }
        String password = ScannerWrapper.getInstance().readPassword("Enter course password: ");

        return course.getHashedPassword().equals(Cipher.sha256(password));
    }

    private void registerToContest() {
        Contest contest = contestDB.getContestForUser(currentUser);
        if (!checkContestGuards(contest)) {
            return;
        }

        if (contest instanceof SpecialContest specialContest) {
            String input = ScannerWrapper.getInstance().readString("Add to Existing group or make New group?(E/N)");
            switch (input) {
                case "E" -> specialContest.addParticipantToExistingGroup(currentUser);
                case "N" -> {
                    String name = ScannerWrapper.getInstance().readString("Enter group name: ");
                    ArrayList<User> members = new ArrayList<>();
                    members.add(currentUser);
                    specialContest.addGroup(name, members);
                }
                default -> System.out.println("Invalid option");
            }
            return;
        }

        if (contest instanceof PrivateContest privateContest) {
            if (privateContest.addParticipant(currentUser)) {
                System.out.println("Successfully registered");
            }
            return;
        }

        NormalContest normalContest = (NormalContest) contest;
        if (normalContest.addParticipant(currentUser)) {
            System.out.println("Successfully registered");
        }
    }

    private boolean checkContestGuards(Contest contest) {
        if (contest == null) {
            return false;
        }
        if (contest.getStartDate().compareTo(DateTimeUtility.now()) > 0) {
            System.out.println("The registration deadline has passed");
            return false;
        }

        return true;
    }

    private void addCourse() {
        Course course = CourseUtility.readCourse(currentUser, "Enter course attributes");
        courseDB.addCourse(course);
        System.out.println("Successfully added");
    }

    private void account() {
        UserChangeMenu userChangeMenu = new UserChangeMenu(currentUser, userDB);
        userChangeMenu.handleMenu();
    }
}
