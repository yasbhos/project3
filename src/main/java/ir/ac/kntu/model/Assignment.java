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

    private DateTime startDateTime;

    private DateTime endDateTime;

    private int delayCoefficient;

    private DateTime delayDateTime;

    private Status assignmentStatus;

    private Status scoreBoardStatus;

    private final ArrayList<Question> questions;

    private final ArrayList<Responder> responders;

    private boolean automaticScoring;

    public Assignment(String name, String description, DateTime startDateTime, DateTime endDateTime,
                      int delayCoefficient, DateTime delayDateTime, Status assignmentStatus,
                      Status scoreBoardStatus, boolean automaticScoring) {
        this.name = name;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(DateTime endDateTime) {
        this.endDateTime = endDateTime;
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

    public boolean isAutomaticScoring() {
        return automaticScoring;
    }

    public void setAutomaticScoring(boolean automaticScoring) {
        this.automaticScoring = automaticScoring;
    }

    public boolean addQuestion(Question question) {
        return questions.add(question);
    }

    public boolean removeQuestion(Question question) {
        return questions.remove(question);
    }

    public Question searchQuestion() {
        for (Question question : questions) {
            System.out.println("ID: " + question.getId() +
                    ", Name: " + question.getName() +
                    ", Level: " + question.getLevel() +
                    ", Type: " + question.getType() +
                    ", Score: " + question.getScore());
        }

        String id = ScannerWrapper.getInstance().readString("Enter question ID: ");
        for (Question question : questions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }

        return null;
    }

    public void scoreBoard() {
        Collections.sort(responders);
        System.out.println("Scoreboard for " + this.name);
        System.out.println("------------------------------------------------------------");
        System.out.println("| Student name | Mark | Average Time");
        responders.forEach(System.out::println);
        System.out.println("------------------------------------------------------------");
    }

    public void registerMarkToFinalSent() {
        System.out.println("Register mark to final sent for " + this.name);
        System.out.println("------------------------------------------------------------");
        //TODO: implement this method
        System.out.println("------------------------------------------------------------");
    }

    public void automaticScoring() {
        if (!automaticScoring) {
            return;
        }

        //TODO: implement this method
    }

    @Override
    public String toString() {
        StringBuilder assignmentToString = new StringBuilder("Assignment{" +
                "name='" + name + '\'' +
                "\ndescription='" + description + '\'' +
                "\nstartDateTime=" + startDateTime +
                "\nendDateTime=" + endDateTime +
                "\ndelayCoefficient=" + delayCoefficient +
                "\ndelayDateTime=" + delayDateTime);

        for (Question question : questions) {
            assignmentToString.append(question.getName());
        }

        assignmentToString.append("\nassignmentStatus=").append(assignmentStatus).append(", scoreBoardStatus=").append(scoreBoardStatus).append("\n}");

        return assignmentToString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Assignment that)) {
            return false;
        }
        return Objects.equals(name, that.name) && Objects.equals(startDateTime, that.startDateTime) && Objects.equals(endDateTime, that.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDateTime, endDateTime);
    }

    private class Responder implements Comparable<Responder> {
        private final String username;

        private double totalScore;

        //TODO: think about this field, really long? It can be DateTime!
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
            return Long.compare(o.averageSentTime, this.averageSentTime);
        }

        @Override
        public String toString() {
            return username + " | " + totalScore + " | " + averageSentTime;
        }
    }
}
