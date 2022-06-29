package ir.ac.kntu.menu.contest.user;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.question.user.UserQuestionMenu;
import ir.ac.kntu.model.contest.Contest;
import ir.ac.kntu.model.User;
import ir.ac.kntu.model.question.Question;
import ir.ac.kntu.util.ScannerWrapper;

public class UserContestMenu implements Menu {
    private final User currentUser;

    private final Contest contest;

    public UserContestMenu(User currentUser, Contest contest) {
        this.currentUser = currentUser;
        this.contest = contest;
    }

    @Override
    public void menu() {
        UserContestMenuOption userContestMenuOption;
        do {
            userContestMenuOption = ScannerWrapper.getInstance().readEnum(UserContestMenuOption.values(),
                    "CONTEST MENU");
            handleTheOption(userContestMenuOption);
        } while (userContestMenuOption != UserContestMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((UserContestMenuOption) option) {
            case LIST_OF_QUESTIONS -> listOfQuestions();
            case SCOREBOARD -> contest.scoreBoard();
            default -> {
            }
        }
    }

    public void listOfQuestions() {
        Question question = contest.searchQuestion();
        if (question == null) {
            return;
        }

        UserQuestionMenu userQuestionMenu = new UserQuestionMenu(currentUser, question);
        userQuestionMenu.menu();
    }
}
