package ir.ac.kntu.model;

import ir.ac.kntu.util.DateTimeUtility;

public class Answer {
    private String description;
    private Question question;
    private DateTime sentDateTime;
    private String senderUsername;
    private double delayCoefficient;
    private double score;
    private double scoreWithDelay;

    public Answer(String description, Question question, String senderUsername) {
        this.description = description;
        this.question = question;
        this.senderUsername = senderUsername;
        this.sentDateTime = DateTimeUtility.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public DateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(DateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public double getDelayCoefficient() {
        return delayCoefficient;
    }

    public void setDelayCoefficient(double delayCoefficient) {
        this.delayCoefficient = delayCoefficient;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScoreWithDelay() {
        return scoreWithDelay;
    }

    public void setScoreWithDelay(double scoreWithDelay) {
        this.scoreWithDelay = scoreWithDelay;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "description:\n'" + description + '\'' +
                "\n, question=" + question +
                "\n, sentDateTime=" + sentDateTime +
                "\n, senderUsername='" + senderUsername + '\'' +
                "\n, delayCoefficient=" + delayCoefficient +
                ", score=" + score +
                ", scoreWithDelay=" + scoreWithDelay +
                '}';
    }
}
