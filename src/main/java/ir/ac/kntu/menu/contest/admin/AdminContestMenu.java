package ir.ac.kntu.menu.contest.admin;

import ir.ac.kntu.db.ContestDB;
import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.Contest;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ContestUtility;
import ir.ac.kntu.util.ScannerWrapper;

public class AdminContestMenu implements Menu {
    private User currentAdmin;
    private UserDB userDB;
    private ContestDB contestDB;

    public AdminContestMenu(User currentAdmin, UserDB userDB, ContestDB contestDB) {
        this.currentAdmin = currentAdmin;
        this.userDB = userDB;
        this.contestDB = contestDB;
    }

    @Override
    public void menu() {
        AdminContestMenuOption adminContestMenuOption;
        do {
            adminContestMenuOption = ScannerWrapper.getInstance().readEnum(AdminContestMenuOption.values(), "Contest Menu");
            handleTheOption(adminContestMenuOption);
        } while (adminContestMenuOption != AdminContestMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((AdminContestMenuOption) option) {
            case ADD_CONTEST -> addContest();
            case LIST_OF_CONTESTS -> listOfContests();
            case BACK -> {
            }
            default -> {

            }
        }
    }

    private void addContest() {
        Contest contest = ContestUtility.readContest(currentAdmin, "Enter contest attributes: ");
        if (contest == null) {
            return;
        }

        contestDB.addContest(contest);
        System.out.println("Successfully added");
    }

    private void listOfContests() {
        Contest contest = contestDB.getContestForAdmin();
        if (contest == null) {
            return;
        }

        System.out.println(contest);
        AdminContestSubMenu adminContestSubMenu = new AdminContestSubMenu(currentAdmin, contest, userDB);
        adminContestSubMenu.menu();
    }
}
