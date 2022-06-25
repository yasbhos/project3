package ir.ac.kntu.model;

import ir.ac.kntu.util.DateTimeUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Question {
    public enum QuestionType {
        CHOICE_ONE, SHORT_ANSWER, LONG_ANSWER, FILL_IN_THE_BLANK
    }

    public enum QuestionLevel {
        EASY, MEDIUM, HARD, VERY_HARD
    }

    private String name;
    private double score;
    private String description;
    private QuestionType type;
    private QuestionLevel level;
    private DateTime uploadDateTime;
    private Map<User, ArrayList<Answer>> sentAnswers;

    public Question(String name, double score, String description, QuestionType type, QuestionLevel level) {
        this.name = name;
        this.score = score;
        this.description = description;
        this.type = type;
        this.level = level;
        this.uploadDateTime = DateTimeUtility.now();
        this.sentAnswers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public QuestionLevel getLevel() {
        return level;
    }

    public void setLevel(QuestionLevel level) {
        this.level = level;
    }

    public DateTime getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(DateTime uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public Question deppCopy() {
        Question question = new Question(this.name, this.score, this.description, this.type, this.level);
        question.uploadDateTime = this.uploadDateTime;
        return question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", score=" + score +
                "\n, description='" + description + '\'' +
                "\n, type=" + type +
                ", level=" + level +
                "\n, uploadDateTime=" + uploadDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && !(o instanceof Question)) {
            Question question = (Question) o;
            return Double.compare(question.score, score) == 0 && Objects.equals(name, question.name) && Objects.equals(description, question.description) && type == question.type && level == question.level;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, description, type, level);
    }
}
