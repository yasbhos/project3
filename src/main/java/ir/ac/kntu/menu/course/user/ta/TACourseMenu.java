package ir.ac.kntu.menu.course.user.ta;

import ir.ac.kntu.db.QuestionDB;
import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.Assignment;
import ir.ac.kntu.model.Course;
import ir.ac.kntu.model.Question;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ScannerWrapper;

/**
 * Represents Teacher Assistant class
 */
public class TACourseMenu implements Menu {
    private User ta;

    private Course course;

    private UserDB userDB;

    private QuestionDB questionDB;


    public TACourseMenu(User ta, Course course, UserDB userDB, QuestionDB questionDB) {
        this.ta = ta;
        this.course = course;
        this.userDB = userDB;
        this.questionDB = questionDB;
    }

    @Override
    public void menu() {
        TACourseMenuOption taCourseMenuOption;
        do {
            taCourseMenuOption = ScannerWrapper.getInstance().readEnum(TACourseMenuOption.values(),
                    "TA COURSE MENU");
            handleTheOption(taCourseMenuOption);
        } while (taCourseMenuOption != TACourseMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((TACourseMenuOption) option) {
            case ADD_QUESTION_TO_ASSIGNMENT -> addQuestionToAssignment();
            case SET_LECTURER -> setLecturer();
            default -> {
            }
        }
    }

    private void addQuestionToAssignment() {
        Assignment assignment = course.searchAssignment();
        if (assignment == null) {
            return;
        }

        //TODO: add new question or existing question
        Question question = questionDB.getQuestion();
        if (question == null) {
            return;
        }

        assignment.addQuestion(question);
        System.out.println("Successfully added");
    }

    private void setLecturer() {
        User lecturer = userDB.getUser();
        if (lecturer == null) {
            return;
        }

        course.setLecturer(lecturer);
        System.out.println("Successfully set");
    }
}
