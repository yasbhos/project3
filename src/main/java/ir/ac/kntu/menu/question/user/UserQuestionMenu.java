package ir.ac.kntu.menu.question.user;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.User;
import ir.ac.kntu.model.question.Answer;
import ir.ac.kntu.model.question.Question;
import ir.ac.kntu.util.ScannerWrapper;

public class UserQuestionMenu implements Menu {
    private final User currentUser;

    private final Question question;

    public UserQuestionMenu(User currentUser, Question question) {
        this.currentUser = currentUser;
        this.question = question;
    }

    @Override
    public void menu() {
        UserQuestionMenuOption userQuestionMenuOption;
        do {
            userQuestionMenuOption = ScannerWrapper.getInstance().readEnum(UserQuestionMenuOption.values(),
                    "USER QUESTION MENU");
            handleTheOption(userQuestionMenuOption);
        } while (userQuestionMenuOption != UserQuestionMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((UserQuestionMenuOption) option) {
            case LIST_SENT_ANSWERS -> listSentAnswers();
            case SEND_ANSWER -> sendAnswer();
            default -> {
            }
        }
    }

    private void listSentAnswers() {
        //TODO: implement this method
    }

    private void sendAnswer() {
        Answer answer = question.readAnswer(currentUser, "Enter answer: ");
        if (answer == null) {
            return;
        }

        question.addAnswer(answer);
    }
}
