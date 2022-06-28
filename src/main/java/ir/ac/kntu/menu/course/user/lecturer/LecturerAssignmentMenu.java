package ir.ac.kntu.menu.course.user.lecturer;

import ir.ac.kntu.db.QuestionDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.question.admin.AdminQuestionMenu;
import ir.ac.kntu.model.Assignment;
import ir.ac.kntu.model.Question;
import ir.ac.kntu.util.ScannerWrapper;

public class LecturerAssignmentMenu implements Menu {
    private Assignment assignment;
    private QuestionDB questionDB;

    public LecturerAssignmentMenu(Assignment assignment, QuestionDB questionDB) {
        this.assignment = assignment;
        this.questionDB = questionDB;
    }

    @Override
    public void menu() {
        LecturerAssignmentMenuOption lecturerAssignmentMenuOption;
        do {
            lecturerAssignmentMenuOption = ScannerWrapper.getInstance().readEnum(LecturerAssignmentMenuOption.values(),
                    "ASSIGNMENT MENU");
            handleTheOption(lecturerAssignmentMenuOption);
        } while (lecturerAssignmentMenuOption != LecturerAssignmentMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((LecturerAssignmentMenuOption) option) {
            case SCOREBOARD -> assignment.scoreBoard();
            case ADD_QUESTION -> addQuestion();
            case REMOVE_QUESTION -> removeQuestion();
            case LIST_OF_QUESTIONS -> listOfQuestions();
            case LIST_OF_ANSWERS_BY_EMAIL -> listOfAnswersByEmail();
            case REGISTER_MARK_TO_FINAL_SENT -> registerMarkToFinalSent();
            default -> {
            }
        }
    }

    private void addQuestion() {
        //TODO: add new question or existing question
        Question question = questionDB.getQuestion();
        if (question == null) {
            return;
        }

        assignment.addQuestion(question);
        System.out.println("Successfully added");
    }

    private void removeQuestion() {
        Question question = assignment.searchQuestion();
        if (question == null) {
            return;
        }

        assignment.removeQuestion(question);
        System.out.println("Successfully removed");
    }

    private void listOfQuestions() {
        Question question = assignment.searchQuestion();
        if (question == null) {
            return;
        }

        AdminQuestionMenu adminQuestionMenu = new AdminQuestionMenu(question);
        adminQuestionMenu.menu();
    }

    private void listOfAnswersByEmail() {
        //TODO: implement this method
    }

    private void registerMarkToFinalSent() {
        //TODO: implement this method
    }
}
