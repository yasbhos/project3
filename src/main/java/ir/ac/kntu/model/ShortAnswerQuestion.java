package ir.ac.kntu.model;

import ir.ac.kntu.util.ScannerWrapper;

public class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(String name, double score, String description, QuestionType type, QuestionLevel level,
                               String correctAnswer) {
        super(name, score, description, type, level);
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public Answer readAnswer(User user, String message) {
        String answer = ScannerWrapper.getInstance().readString(message);
        return new Answer(user.getUsername(), this, answer);
    }
}
