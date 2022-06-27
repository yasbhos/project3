package ir.ac.kntu.menu.contest.user;

import ir.ac.kntu.db.ContestDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.Contest;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ScannerWrapper;

public class UserContestMenu implements Menu {
    private User currentUser;
    private ContestDB contestDB;

    public UserContestMenu(User currentUser, ContestDB contestDB) {
        this.currentUser = currentUser;
        this.contestDB = contestDB;
    }

    @Override
    public void menu() {
        UserContestMenuOption userContestMenuOption;
        do {
            userContestMenuOption = ScannerWrapper.getInstance().readEnum(UserContestMenuOption.values(),
                    "USER CONTEST MENU");
            handleTheOption(userContestMenuOption);
        } while (userContestMenuOption != UserContestMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((UserContestMenuOption) option) {
            case LIST_OF_CONTESTS -> listOfContest();
            default -> {
            }
        }
    }

    private void listOfContest() {
        Contest contest = contestDB.getContestForUser(currentUser);
        if (contest == null) {
            return;
        }

        System.out.println(contest);
        ContestSubMenu contestSubMenu = new ContestSubMenu(currentUser, contest);
        contestSubMenu.menu();
    }
}
