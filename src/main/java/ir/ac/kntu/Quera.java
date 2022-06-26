package ir.ac.kntu;

import java.util.ArrayList;

import ir.ac.kntu.db.*;
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
        CourseDB courseDB = new CourseDB(new ArrayList<>());
        ContestDB contestDB = new ContestDB(new ArrayList<>());
        QuestionDB questionDB = new QuestionDB(new ArrayList<>());
        loginMenu = new LoginMenu(adminDB, userDB, courseDB, contestDB, questionDB);
    }
}
