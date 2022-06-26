package ir.ac.kntu.model;

import java.util.ArrayList;

public class Contest {
    private String name;
    private DateTime startDate;
    private DateTime endDate;
    private ArrayList<Question> questions;

    public Contest(String name, DateTime startDate, DateTime endDate, ArrayList<Question> questions) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.questions = questions;
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
        if (!questions.contains(question)) {
            questions.add(question);
            return true;
        }
        return false;
    }

    public boolean removeQuestion(Question question) {
        return questions.remove(question);
    }

    public void scoreBoard() {
        System.out.println("Scoreboard for " + this.name);
        System.out.println("------------------------------------------------------------");
        System.out.println("| Participant name | Mark |");
        //TODO
        System.out.println("------------------------------------------------------------");
    }
}
