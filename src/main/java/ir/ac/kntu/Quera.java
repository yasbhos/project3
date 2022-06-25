package ir.ac.kntu;

import java.util.ArrayList;

import ir.ac.kntu.db.AdminDB;
import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.menu.login.LoginMenu;

public class Quera {
    private LoginMenu loginMenu;

    public void start() {
        initial();
        loginMenu.handleMenu();
    }

    private void initial() {
        AdminDB adminDB = new AdminDB(new ArrayList<>());
        UserDB userDB = new UserDB(new ArrayList<>());
        loginMenu = new LoginMenu(adminDB, userDB);
    }
}
