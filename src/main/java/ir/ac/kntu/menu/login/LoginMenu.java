package ir.ac.kntu.menu.login;

import ir.ac.kntu.db.*;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.admin.main.AdminMainMenu;
import ir.ac.kntu.menu.guest.main.GuestMainMenu;
import ir.ac.kntu.menu.user.main.UserMainMenu;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.UserUtility;

public class LoginMenu implements Menu {
    private AdminDB adminDB;
    private UserDB userDB;
    private CourseDB courseDB;
    private ContestDB contestDB;
    private QuestionDB questionDB;

    public LoginMenu(AdminDB adminDB, UserDB userDB, CourseDB courseDB, ContestDB contestDB, QuestionDB questionDB) {
        this.adminDB = adminDB;
        this.userDB = userDB;
        this.courseDB = courseDB;
        this.contestDB = contestDB;
        this.questionDB = questionDB;
    }

    @Override
    public void handleMenu() {
        LoginMenuOption option;
        do {
            option = ScannerWrapper.getInstance().readEnum(LoginMenuOption.values());
            handleTheOption(option);
        } while (option != LoginMenuOption.EXIT);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((LoginMenuOption) option) {
            case SIGN_IN -> signIn();
            case SIGN_UP -> signUp();
            case CONTINUE_AS_GUEST -> continueAsGuest();
            case EXIT -> exit();
            default -> {
            }
        }
    }

    private void signIn() {
        String username = ScannerWrapper.getInstance().readString("Enter username: ");
        String password = ScannerWrapper.getInstance().readPassword("Enter password: ");
        User currentUser = adminDB.getAdminByUsernameAndPassword(username, password);
        if (currentUser == null) {
            currentUser = userDB.getUserByUsernameAndPassword(username, password);
            if (currentUser == null) {
                System.out.println("User not found");
                return;
            }
            System.out.println("Successfully signed in");
            UserMainMenu userMainMenu = new UserMainMenu(currentUser, courseDB, contestDB, questionDB);
            userMainMenu.handleMenu();
        }
        System.out.println("Successfully signed in");
        AdminMainMenu adminMainMenu = new AdminMainMenu(currentUser, userDB, courseDB, contestDB, questionDB);
        adminMainMenu.handleMenu();
    }

    private void signUp() {
        User user = UserUtility.readUser("Enter your attributes");
        userDB.addUser(user);
        System.out.println("Successfully signed up");
    }

    private void continueAsGuest() {
        GuestMainMenu guestMainMenu = new GuestMainMenu(contestDB, questionDB);
        guestMainMenu.handleMenu();
    }

    private void exit() {
        System.exit(0);
    }

}
