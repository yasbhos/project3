package ir.ac.kntu.model;

import ir.ac.kntu.util.IdGenerator;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class Contest {
    private User ownerAdmin;
    private String id;
    private String name;
    private DateTime startDate;
    private DateTime endDate;
    private ArrayList<Question> questions;
    private boolean automaticScoring;

    public Contest(User ownerAdmin, String name, DateTime startDate, DateTime endDate,
                   ArrayList<Question> questions, boolean automaticScoring) {
        this.ownerAdmin = ownerAdmin;
        this.id = IdGenerator.createID();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.questions = questions;
        this.automaticScoring = automaticScoring;
    }

    public User getOwnerAdmin() {
        return ownerAdmin;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean addQuestion(Question question) {
        return questions.add(question);
    }

    public boolean removeQuestion(Question question) {
        return questions.remove(question);
    }

    public boolean isAutomaticScoring() {
        return automaticScoring;
    }

    public void setAutomaticScoring(boolean automaticScoring) {
        this.automaticScoring = automaticScoring;
    }

    public Question getQuestion() {
        for (Question question : questions) {
            System.out.println("ID: " + question.getId() +
                    "Name: " + question.getName() +
                    "Level: " + question.getLevel() +
                    "Type: " + question.getType() +
                    "Score: " + question.getScore());
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
        System.out.println("Scoreboard for " + this.name);
        System.out.println("------------------------------------------------------------");
        System.out.println("| Participant name | Mark |");
        //TODO
        System.out.println("------------------------------------------------------------");
    }

    @Override
    public String toString() {
        StringBuilder contestToString = new StringBuilder("Contest{" +
                "name='" + name + '\'' +
                "\nstartDate=" + startDate +
                "\nendDate=" + endDate);

        contestToString.append("Questions\n");
        for (Question question :
                questions) {
            contestToString.append(question.getName()).append("\n");
        }

        contestToString.append("\n}");

        return contestToString.toString();
    }
}
