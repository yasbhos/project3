package ir.ac.kntu.model;

import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Assignment {
    public enum Status {
        ACTIVE,
        INACTIVE
    }

    private String name;
    private String description;
    private DateTime startDate;
    private DateTime endDate;
    private int delayCoefficient;
    private DateTime delayDateTime;
    private Status assignmentStatus;
    private Status scoreBoardStatus;
    private ArrayList<Question> questions;
    private ArrayList<Responder> responders;
    private boolean automaticScoring;

    public Assignment(String name, String description, DateTime startDate, DateTime endDate,
                      int delayCoefficient, DateTime delayDateTime, Status assignmentStatus,
                      Status scoreBoardStatus, boolean automaticScoring) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.delayCoefficient = delayCoefficient;
        this.delayDateTime = delayDateTime;
        this.assignmentStatus = assignmentStatus;
        this.scoreBoardStatus = scoreBoardStatus;
        this.questions = new ArrayList<>();
        this.responders = new ArrayList<>();
        this.automaticScoring = automaticScoring;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public int getDelayCoefficient() {
        return delayCoefficient;
    }

    public void setDelayCoefficient(int delayCoefficient) {
        this.delayCoefficient = delayCoefficient;
    }

    public DateTime getDelayDateTime() {
        return delayDateTime;
    }

    public void setDelayDateTime(DateTime delayDateTime) {
        this.delayDateTime = delayDateTime;
    }

    public Status getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(Status assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public Status getScoreBoardStatus() {
        return scoreBoardStatus;
    }

    public void setScoreBoardStatus(Status scoreBoardStatus) {
        this.scoreBoardStatus = scoreBoardStatus;
    }

    public boolean addQuestion(Question question) {
        return questions.add(question);
    }

    public boolean removeQuestion(Question question) {
        return questions.remove(question);
    }

    public Question searchQuestionByName() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(i + 1 + ". " + questions.get(i).getName());
        }
        int index = ScannerWrapper.getInstance().readInt("Enter question index: ");
        if (index > 0 && index <= questions.size()) {
            return questions.get(index - 1);
        }
        System.out.println(); //TODO
        return null;
    }

    public void scoreBoard() {
        Collections.sort(responders);
        System.out.println("Scoreboard for " + this.name);
        System.out.println("------------------------------------------------------------");
        System.out.println("| Student name | Mark | Average Time");
        for (Responder responder : responders) {
            System.out.println(responder);
        }
        System.out.println("------------------------------------------------------------");
    }

    public void registerMarkToFinalSent() {
        System.out.println("Register mark to final sent for " + this.name);
        System.out.println("------------------------------------------------------------");
        //TODO
        System.out.println("------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "name='" + name + '\'' +
                "\n, description='" + description + '\'' +
                "\n, startDate=" + startDate +
                "\n, endDate=" + endDate +
                ", delayCoefficient=" + delayCoefficient +
                ", delayDateTime=" + delayDateTime +
                "\n, assignmentStatus=" + assignmentStatus +
                ", scoreBoardStatus=" + scoreBoardStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Assignment)) {
            return false;
        }
        Assignment that = (Assignment) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, startDate, endDate);
    }

    private class Responder implements Comparable<Responder> {
        private String username;
        private double totalScore;
        private long averageSentTime;

        public Responder(String username) {
            this.username = username;
        }

        public double getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(double totalScore) {
            this.totalScore = totalScore;
        }

        public long getAverageSentTime() {
            return averageSentTime;
        }

        public void setAverageSentTime(long averageSentTime) {
            this.averageSentTime = averageSentTime;
        }

        @Override
        public int compareTo(Responder o) {
            if (this.totalScore > o.totalScore) {
                return 1;
            }
            if (this.totalScore < o.totalScore) {
                return -1;
            }
            if (this.averageSentTime < o.averageSentTime) {
                return 1;
            }
            if (this.averageSentTime > o.averageSentTime) {
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return username + " | " + totalScore + " | " + averageSentTime;
        }
    }
}
