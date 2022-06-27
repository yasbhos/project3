package ir.ac.kntu.menu.contest.admin;

import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.*;
import ir.ac.kntu.util.DateTimeUtility;
import ir.ac.kntu.util.QuestionUtility;
import ir.ac.kntu.util.ScannerWrapper;

public class AdminContestSubMenu implements Menu {
    private User currentAdmin;
    private Contest contest;
    private UserDB userDB;

    public AdminContestSubMenu(User currentAdmin, Contest contest, UserDB userDB) {
        this.currentAdmin = currentAdmin;
        this.contest = contest;
        this.userDB = userDB;
    }

    @Override
    public void menu() {
        AdminContestSubMenuOption adminContestSubMenuOption;
        do {
            adminContestSubMenuOption = ScannerWrapper.getInstance().readEnum(AdminContestSubMenuOption.values());
            handleTheOption(adminContestSubMenuOption);
        } while (adminContestSubMenuOption != AdminContestSubMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((AdminContestSubMenuOption) option) {
            case ADD_QUESTION -> addQuestion();
            case LIST_OF_QUESTIONS -> listOfQuestions();
            case INVITE_USER_TO_CONTEST -> inviteUserToContest();
            case SCOREBOARD -> scoreBoard();
            case EDIT_NAME -> editName();
            case EDIT_START_DATETIME -> editStartDateTime();
            case EDIT_END_DATETIME -> editEndDateTime();
            case EDIT_AUTOMATIC_SCORING -> editAutomaticScoring();
            case BACK -> {
            }
            default -> {

            }
        }
    }

    private void addQuestion() {
        Question question = QuestionUtility.readQuestion("Enter question attributes: ");
        if (question == null) {
            return;
        }

        contest.addQuestion(question);
        System.out.println("Successfully added");
    }

    private void listOfQuestions() {

    }

    private void inviteUserToContest() {
        if (contest instanceof PrivateContest privateContest) {
            User user = userDB.getUser();
            if (user == null) {
                return;
            }

            privateContest.addWhosCanParticipant(user);
            System.out.println("Successfully added");
            return;
        }

        System.out.println("This contest is not private");
    }

    private void scoreBoard() {
        contest.scoreBoard();
    }

    private void editName() {
        String name = ScannerWrapper.getInstance().readString("Enter new name: ");
        contest.setName(name);
        System.out.println("Successfully edited");
    }

    public void editStartDateTime() {
        DateTime dateTime = DateTimeUtility.readDateTime("Enter new start date-time: ");
        if (dateTime == null) {
            return;
        }

        contest.setStartDate(dateTime);
    }

    public void editEndDateTime() {
        DateTime dateTime = DateTimeUtility.readDateTime("Enter new end date-time: ");
        if (dateTime == null) {
            return;
        }

        contest.setEndDate(dateTime);
    }

    public void editAutomaticScoring() {
        enum Status {
            TRUE,
            FALSE
        }

        Status status = ScannerWrapper.getInstance().readEnum(Status.values(), "Automatic Scoring Status");
        switch (status) {
            case TRUE -> contest.setAutomaticScoring(true);
            case FALSE -> contest.setAutomaticScoring(false);
            default -> {

            }
        }
    }
}
