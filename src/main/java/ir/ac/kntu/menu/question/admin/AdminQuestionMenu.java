package ir.ac.kntu.menu.question.admin;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.Question;
import ir.ac.kntu.util.ScannerWrapper;

public class AdminQuestionMenu implements Menu {
    private Question question;

    public AdminQuestionMenu(Question question) {
        this.question = question;
    }

    @Override
    public void menu() {
        AdminQuestionMenuOption adminQuestionMenuOption;
        do {
            adminQuestionMenuOption = ScannerWrapper.getInstance().readEnum(AdminQuestionMenuOption.values());
            handleTheOption(adminQuestionMenuOption);
        } while (adminQuestionMenuOption != AdminQuestionMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((AdminQuestionMenuOption) option) {
            case EDIT_NAME -> editName();
            case EDIT_SCORE -> editScore();
            case EDIT_DESCRIPTION -> editDescription();
            case EDIT_TYPE -> editType();
            case EDIT_LEVEL -> editLevel();
            default -> {
            }
        }
    }

    private void editName() {
        String name = ScannerWrapper.getInstance().readString("Enter new name: ");
        question.setName(name);
        System.out.println("Successfully changed");
    }

    private void editScore() {
        double score = ScannerWrapper.getInstance().readDouble("Enter new score: ");
        question.setScore(score);
        System.out.println("Successfully changed");
    }

    private void editDescription() {
        String description = ScannerWrapper.getInstance().readString("Enter new description: ");
        question.setDescription(description);
        System.out.println("Successfully changed");
    }

    private void editType() {
        System.out.println("Enter new type");
        Question.QuestionType type = ScannerWrapper.getInstance().readEnum(Question.QuestionType.values());
        question.setType(type);
        System.out.println("Successfully changed");
    }

    private void editLevel() {
        System.out.println("Enter new level");
        Question.QuestionLevel level = ScannerWrapper.getInstance().readEnum(Question.QuestionLevel.values());
        question.setLevel(level);
        System.out.println("Successfully changed");
    }
}
