package ir.ac.kntu.menu.main.admin;

import ir.ac.kntu.db.*;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.account.AccountMenu;
import ir.ac.kntu.menu.contest.admin.AdminContestMenu;
import ir.ac.kntu.menu.course.admin.AdminCourseMenu;
import ir.ac.kntu.menu.main.admin.AdminMainMenuOption.*;
import ir.ac.kntu.menu.question.admin.AdminQuestionMenu;
import ir.ac.kntu.model.Contest;
import ir.ac.kntu.model.Course;
import ir.ac.kntu.model.Question;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ContestUtility;
import ir.ac.kntu.util.CourseUtility;
import ir.ac.kntu.util.QuestionUtility;
import ir.ac.kntu.util.ScannerWrapper;

public class AdminMainMenu implements Menu {
    private User currentAdmin;
    private AdminDB adminDB;
    private UserDB userDB;
    private CourseDB courseDB;
    private ContestDB contestDB;
    private QuestionDB questionDB;

    public AdminMainMenu(User currentAdmin,AdminDB adminDB, UserDB userDB, CourseDB courseDB, ContestDB contestDB, QuestionDB questionDB) {
        this.currentAdmin = currentAdmin;
        this.adminDB = adminDB;
        this.userDB = userDB;
        this.courseDB = courseDB;
        this.contestDB = contestDB;
        this.questionDB = questionDB;
    }

    @Override
    public void menu() {
        AdminMainMenuOption option;
        do {
            option = ScannerWrapper.getInstance().readEnum(AdminMainMenuOption.values(), "ADMIN MEIN MENU");
            handleTheOption(option);
        } while (option != AdminMainMenuOption.LOGOUT);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((AdminMainMenuOption) option) {
            case USERS -> usersSubMenu();
            case COURSES -> coursesSubMenu();
            case CONTESTS -> contestsSubMenu();
            case QUESTIONS -> questionsSubMenu();
            case ACCOUNT -> account();
            default -> {
            }
        }
    }

    private void usersSubMenu() {
        UsersSubMenu usersSubMenu;
        do {
            usersSubMenu = ScannerWrapper.getInstance().readEnum(UsersSubMenu.values());
            handleUsersSubMenuOption(usersSubMenu);
        } while (usersSubMenu != UsersSubMenu.BACK);
    }

    private void handleUsersSubMenuOption(UsersSubMenu option) {
        switch (option) {
            case LIST_OF_USERS -> listOfUsers();
            default -> {
            }
        }
    }

    private void listOfUsers() {
        User user = userDB.getUser();
        if (user == null) {
            return;
        }

        System.out.println(user);
        AccountMenu accountMenu = new AccountMenu(user, adminDB, userDB);
        accountMenu.menu();
    }

    private void coursesSubMenu() {
        CoursesSubMenu coursesSubMenu;
        do {
            coursesSubMenu = ScannerWrapper.getInstance().readEnum(CoursesSubMenu.values());
            handleCoursesSubMenuOption(coursesSubMenu);
        } while (coursesSubMenu != CoursesSubMenu.BACK);
    }

    private void handleCoursesSubMenuOption(CoursesSubMenu option) {
        switch (option) {
            case ADD_COURSE -> addCourse();
            case REMOVE_COURSE -> removeCourse();
            case LIST_OF_COURSES -> listOfCourses();
            default -> {
            }
        }
    }
    
    private void addCourse() {
        Course course = CourseUtility.readCourse(currentAdmin, "Enter course attributes");
        if (course == null) {
            return;
        }
        
        if (courseDB.containsCourse(course)) {
            System.out.println("This course has already been defined");
            return;
        }
        
        courseDB.addCourse(course);
        System.out.println("Successfully added");
    }
    
    private void removeCourse() {
        Course course = courseDB.getCourse();
        if (course == null) {
            return;
        }
        
        courseDB.removeCourse(course);
        System.out.println("Successfully removed");
    }
    
    private void listOfCourses() {
        Course course = courseDB.getCourse();
        if (course == null) {
            return;
        }

        System.out.println(course);
        AdminCourseMenu adminCourseMenu = new AdminCourseMenu(currentAdmin, course, courseDB);
        adminCourseMenu.menu();
    }

    private void contestsSubMenu() {
        ContestsSubMenu contestsSubMenu;
        do {
            contestsSubMenu = ScannerWrapper.getInstance().readEnum(ContestsSubMenu.values());
            handleContestsSubMenuOption(contestsSubMenu);
        } while (contestsSubMenu != ContestsSubMenu.BACK);
    }

    private void handleContestsSubMenuOption(ContestsSubMenu option) {
        switch (option) {
            case ADD_CONTEST -> addContest();
            case REMOVE_CONTEST -> removeContest();
            case LIST_OF_CONTESTS -> listOfContests();
            default -> {
            }
        }
    }

    private void addContest() {
        Contest contest = ContestUtility.readContest(currentAdmin, "Enter contest attributes");
        if (contest == null) {
            return;
        }

        contestDB.addContest(contest);
        System.out.println("Successfully added");
    }
    
    private void removeContest() {
        Contest contest = contestDB.getContestForAdmin();
        if (contest == null) {
            return;
        }
        
        contestDB.removeContest(contest);
        System.out.println("Successfully removed");
    }

    private void listOfContests() {
        Contest contest = contestDB.getContestForAdmin();
        if (contest == null) {
            return;
        }

        System.out.println(contest);
        AdminContestMenu adminContestMenu = new AdminContestMenu(currentAdmin, userDB, contestDB);
        adminContestMenu.menu();
    }

    private void questionsSubMenu() {
        QuestionsSubMenu questionsSubMenu;
        do {
            questionsSubMenu = ScannerWrapper.getInstance().readEnum(QuestionsSubMenu.values());
            handleQuestionsSubMenuOption(questionsSubMenu);
        } while (questionsSubMenu != QuestionsSubMenu.BACK);
    }

    private void handleQuestionsSubMenuOption(QuestionsSubMenu option) {
        switch (option) {
            case ADD_QUESTION -> addQuestion();
            case REMOVE_QUESTION -> removeQuestion();
            case LIST_OF_QUESTIONS -> listOfQuestions();
            default -> {
            }
        }
    }
    
    private void addQuestion() {
        Question question = QuestionUtility.readQuestion("Enter question attributes");
        if (question == null) {
            return;
        }
        
        questionDB.addQuestion(question);
        System.out.println("Successfully added");
    }

    private void removeQuestion() {
        Question question = questionDB.getQuestion();
        if (question == null) {
            return;
        }
        
        questionDB.removeQuestion(question);
        System.out.println("Successfully removed");
    }
    
    private void listOfQuestions() {
        Question question = questionDB.getQuestion();
        if (question == null) {
            return;
        }

        System.out.println(question);
        AdminQuestionMenu adminQuestionMenu = new AdminQuestionMenu(question);
        adminQuestionMenu.menu();
    }


    private void account() {
        AccountMenu accountMenu = new AccountMenu(currentAdmin, adminDB, userDB);
        accountMenu.menu();
    }
}
