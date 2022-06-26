package ir.ac.kntu.menu.guest.main;

import ir.ac.kntu.db.ContestDB;
import ir.ac.kntu.db.QuestionDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.util.ScannerWrapper;

public class GuestMainMenu implements Menu {
    private ContestDB contestDB;
    private QuestionDB questionDB;

    public GuestMainMenu(ContestDB contestDB, QuestionDB questionDB) {
        this.contestDB = contestDB;
        this.questionDB = questionDB;
    }

    @Override
    public void handleMenu() {
        GuestMainMenuOption guestMainMenuOption;
        do {
            guestMainMenuOption = ScannerWrapper.getInstance().readEnum(GuestMainMenuOption.values(), "Guest Menu");
            handleTheOption(guestMainMenuOption);
        } while (guestMainMenuOption != GuestMainMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((GuestMainMenuOption) option) {
            case LIST_OF_QUESTIONS -> listOfQuestions();
            case LIST_OF_CONTESTS -> listOfContests();
            case BACK -> {

            }
            default -> {
            }
        }
    }

    //TODO
    private void listOfQuestions() {
    }

    //TODO
    private void listOfContests() {
    }

}
