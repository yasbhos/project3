package ir.ac.kntu.menu.login;

import ir.ac.kntu.db.AdminDB;
import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.admin.main.AdminMainMenu;
import ir.ac.kntu.menu.guest.main.GuestMainMenu;
import ir.ac.kntu.menu.user.main.UserMainMenu;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ScannerWrapper;

public class LoginMenu implements Menu {
    private final AdminDB adminDB;
    private final UserDB userDB;

    public LoginMenu(AdminDB adminDB, UserDB userDB) {
        this.adminDB = adminDB;
        this.userDB = userDB;
    }

    @Override
    public void handleMenu() {
        showOptions();
        LoginMenuOption option;
        do {
            option = scanTheEnum(LoginMenuOption.values());
            handleTheOption(option);
        } while (option != LoginMenuOption.EXIT);
    }

    @Override
    public void showOptions() {
        System.out.println("*************** LOGIN MENU ***************");
        for (int i = 0; i < LoginMenuOption.values().length; i++) {
            System.out.printf("%2d. %s\n", i + 1, LoginMenuOption.values()[i].name());
        }
        System.out.println("*************** LOGIN MENU ***************");
        System.out.println("Please enter your choice: ");
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
        String username = ScannerWrapper.readString("Enter username: ");
        String password = ScannerWrapper.readPassword("Enter password: ");
        User currentUser = adminDB.getAdminByUsernameAndPassword(username, password);
        if (currentUser == null) {
            currentUser = userDB.getUserByUsernameAndPassword(username, password);
            if (currentUser == null) {
                System.out.println(); //TODO
                return;
            }
            //TODO
            System.out.println();
            UserMainMenu userMainMenu = new UserMainMenu(currentUser);
            userMainMenu.handleMenu();
        }
        //TODO
        System.out.println();
        AdminMainMenu adminMainMenu = new AdminMainMenu(currentUser);
        adminMainMenu.handleMenu();
    }

    private void signUp() {
        //TODO
    }

    private void continueAsGuest() {
        //TODO
        System.out.println();
        GuestMainMenu guestMainMenu = new GuestMainMenu();
        guestMainMenu.handleMenu();
    }

    private void exit() {
        System.exit(0);
    }

}
