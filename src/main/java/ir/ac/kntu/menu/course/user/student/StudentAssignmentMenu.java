package ir.ac.kntu.menu.course.user.student;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.question.user.UserQuestionMenu;
import ir.ac.kntu.model.Assignment;
import ir.ac.kntu.model.Question;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ScannerWrapper;

public class StudentAssignmentMenu implements Menu {
    private User currentUser;

    private Assignment assignment;

    public StudentAssignmentMenu(User currentUser, Assignment assignment) {
        this.currentUser = currentUser;
        this.assignment = assignment;
    }

    @Override
    public void menu() {
        StudentAssignmentMenuOption studentAssignmentMenuOption;
        do {
            studentAssignmentMenuOption = ScannerWrapper.getInstance().readEnum(StudentAssignmentMenuOption.values(),
                    "ASSIGNMENT MENU");
            handleTheOption(studentAssignmentMenuOption);
        } while (studentAssignmentMenuOption != StudentAssignmentMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((StudentAssignmentMenuOption) option) {
            case SCOREBOARD -> assignment.scoreBoard();
            case LIST_OF_QUESTIONS -> listOfQuestions();
            case LIST_OF_FINAL_SENT_ANSWERS -> listOfFinalSentAnswers();
            default -> {
            }
        }
    }

    private void listOfQuestions() {
        Question question = assignment.searchQuestion();
        if (question == null) {
            return;
        }

        UserQuestionMenu userQuestionMenu = new UserQuestionMenu(currentUser, question);
        userQuestionMenu.menu();
    }

    private void listOfFinalSentAnswers() {
        //TODO: implement this method
    }
}
