package ir.ac.kntu.model.question;

public class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(String name, double score, String description, Type type, Level level, String correctAnswer) {
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
    public Question deepCopy() {
        ShortAnswerQuestion question = new ShortAnswerQuestion(super.getName(), super.getScore(),
                super.getDescription(), super.getType(), super.getLevel(), correctAnswer);
        question.setUploadDateTime(super.getUploadDateTime());

        return question;
    }
}
