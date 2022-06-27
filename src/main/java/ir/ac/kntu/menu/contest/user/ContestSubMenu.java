package ir.ac.kntu.menu.contest.user;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.question.user.UserQuestionMenu;
import ir.ac.kntu.model.Contest;
import ir.ac.kntu.model.Question;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ScannerWrapper;

public class ContestSubMenu implements Menu {
    private User currentUser;
    private Contest contest;

    public ContestSubMenu(User currentUser, Contest contest) {
        this.currentUser = currentUser;
        this.contest = contest;
    }

    @Override
    public void menu() {
        ContestSubMenuOption contestSubMenuOption;
        do {
            contestSubMenuOption = ScannerWrapper.getInstance().readEnum(ContestSubMenuOption.values(),
                    "CONTEST SUB MENU");
            handleTheOption(contestSubMenuOption);
        } while (contestSubMenuOption != ContestSubMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((ContestSubMenuOption) option) {
            case LIST_OF_QUESTIONS -> listOfQuestions();
            case SCOREBOARD -> contest.scoreBoard();
            default -> {
            }
        }
    }

    public void listOfQuestions() {
        Question question = contest.getQuestion();
        if (question == null) {
            return;
        }

        UserQuestionMenu userQuestionMenu = new UserQuestionMenu(currentUser, question);
        userQuestionMenu.menu();
    }
}
