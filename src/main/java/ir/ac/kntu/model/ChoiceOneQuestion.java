package ir.ac.kntu.model;

import ir.ac.kntu.util.ScannerWrapper;

public class ChoiceOneQuestion extends Question {
    public enum Choices {
        A, B, C, D
    }

    private Choices correctAnswer;

    public ChoiceOneQuestion(String name, double score, String description, QuestionType type, QuestionLevel level,
                             Choices correctAnswer) {
        super(name, score, description, type, level);
        this.correctAnswer = correctAnswer;
    }

    public Choices getCorrectOption() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Choices correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public Answer readAnswer(User user, String message) {
        System.out.println(message);
        Choices answer = ScannerWrapper.getInstance().readEnum(Choices.values(), "Choices");
        return new Answer(user.getUsername(), this, answer.toString());
    }
}
