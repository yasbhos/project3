package ir.ac.kntu.menu.admin.main;

import ir.ac.kntu.db.ContestDB;
import ir.ac.kntu.db.CourseDB;
import ir.ac.kntu.db.QuestionDB;
import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.admin.change.AdminChangeMenu;
import ir.ac.kntu.menu.admin.main.AdminMainMenuOption.*;
import ir.ac.kntu.model.Admin;
import ir.ac.kntu.util.ScannerWrapper;

public class AdminMainMenu implements Menu {
    private Admin currentAdmin;
    private UserDB userDB;
    private CourseDB courseDB;
    private ContestDB contestDB;
    private QuestionDB questionDB;

    public AdminMainMenu(Admin currentAdmin, UserDB userDB, CourseDB courseDB, ContestDB contestDB, QuestionDB questionDB) {
        this.currentAdmin = currentAdmin;
        this.userDB = userDB;
        this.courseDB = courseDB;
        this.contestDB = contestDB;
        this.questionDB = questionDB;
    }

    @Override
    public void handleMenu() {
        AdminMainMenuOption option;
        do {
            option = ScannerWrapper.getInstance().readEnum(AdminMainMenuOption.values(), "Admin Main Menu");
            handleTheOption(option);
        } while (option != AdminMainMenuOption.LOGOUT);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((AdminMainMenuOption) option) {
            case USERS -> handleUsersSubMenu();
            case COURSES -> handleCoursesSubMenu();
            case CONTESTS -> handleContestsSubMenu();
            case QUESTIONS -> handleQuestionsSubMenu();
            case ACCOUNT -> {
                AdminChangeMenu adminChangeMenu = new AdminChangeMenu(currentAdmin);
                adminChangeMenu.handleMenu();
            }
            case LOGOUT -> {

            }
            default -> {
            }
        }
    }

    private void handleUsersSubMenu() {
        UsersSubMenu usersSubMenu;
        do {
            usersSubMenu = ScannerWrapper.getInstance().readEnum(UsersSubMenu.values());
            handleUsersSubMenuOption(usersSubMenu);
        } while (usersSubMenu != UsersSubMenu.BACK);
    }

    private void handleUsersSubMenuOption(UsersSubMenu usersSubMenuOption) {
        switch (usersSubMenuOption) {
            case LIST_OF_USERS -> listOfUsers();
            case BACK -> {

            }
            default -> {
            }
        }
    }

    //TODO
    private void listOfUsers() {
    }

    private void handleCoursesSubMenu() {
        CoursesSubMenu coursesSubMenu;
        do {
            coursesSubMenu = ScannerWrapper.getInstance().readEnum(CoursesSubMenu.values());
            handleCoursesSubMenuOption(coursesSubMenu);
        } while (coursesSubMenu != CoursesSubMenu.BACK);
    }

    private void handleCoursesSubMenuOption(CoursesSubMenu coursesSubMenuOption) {
        switch (coursesSubMenuOption) {
            case LIST_OF_COURSES -> listOfCourses();
            case BACK -> {

            }
            default -> {
            }
        }
    }

    //TODO
    private void listOfCourses() {
    }

    private void handleContestsSubMenu() {
        ContestsSubMenu contestsSubMenu;
        do {
            contestsSubMenu = ScannerWrapper.getInstance().readEnum(ContestsSubMenu.values());
            handleContestsSubMenuOption(contestsSubMenu);
        } while (contestsSubMenu != ContestsSubMenu.BACK);
    }

    private void handleContestsSubMenuOption(ContestsSubMenu contestsSubMenu) {
        switch (contestsSubMenu) {
            case ADD_CONTEST -> addContest();
            case LIST_OF_CONTESTS -> listOfContests();
            case BACK -> {

            }
            default -> {
            }
        }
    }

    //TODO
    private void addContest() {
    }

    //TODO
    private void listOfContests() {
    }

    private void handleQuestionsSubMenu() {
        QuestionsSubMenu questionsSubMenu;
        do {
            questionsSubMenu = ScannerWrapper.getInstance().readEnum(QuestionsSubMenu.values());
            handleQuestionsSubMenuOption(questionsSubMenu);
        } while (questionsSubMenu != QuestionsSubMenu.BACK);
    }

    private void handleQuestionsSubMenuOption(QuestionsSubMenu questionsSubMenu) {
        switch (questionsSubMenu) {
            case LIST_OF_QUESTIONS -> listOfQuestions();
            case BACK -> {

            }
            default -> {
            }
        }
    }

    //TODO
    private void listOfQuestions() {
    }
}
