package ir.ac.kntu.util;

import ir.ac.kntu.model.Question;
import ir.ac.kntu.model.Question.QuestionType;
import ir.ac.kntu.model.Question.QuestionLevel;

public class QuestionUtility {
    public static Question readQuestion(String message) {
        System.out.println(message);

        String name = ScannerWrapper.getInstance().readString("Enter question name: ");
        double score = ScannerWrapper.getInstance().readDouble("Enter question score: ");
        String description = ScannerWrapper.getInstance().readString("Enter question description: \n");
        QuestionType type = ScannerWrapper.getInstance().readEnum(QuestionType.values());
        QuestionLevel level = ScannerWrapper.getInstance().readEnum(QuestionLevel.values());

        return new Question(name, score, description, type, level);
    }
}
