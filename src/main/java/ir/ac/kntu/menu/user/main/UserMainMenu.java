package ir.ac.kntu.menu.user.main;

import ir.ac.kntu.db.ContestDB;
import ir.ac.kntu.db.CourseDB;
import ir.ac.kntu.db.QuestionDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ScannerWrapper;

public class UserMainMenu implements Menu {
    private User currentUser;
    private CourseDB courseDB;
    private ContestDB contestDB;
    private QuestionDB questionDB;

    public UserMainMenu(User currentUser, CourseDB courseDB, ContestDB contestDB, QuestionDB questionDB) {
        this.currentUser = currentUser;
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
            case REGISTER_TO_COURSE -> {
            }
            case REGISTER_TO_CONTEST -> {
            }
            case ADD_COURSE -> {
            }
            case LOGOUT -> {
            }
            default -> {
            }
        }
    }
}
