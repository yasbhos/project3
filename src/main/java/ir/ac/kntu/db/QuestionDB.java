package ir.ac.kntu.db;

import ir.ac.kntu.model.Question;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class QuestionDB {
    private ArrayList<Question> questions;

    public QuestionDB(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public boolean addQuestion(Question question) {
        return questions.add(question);
    }

    public boolean removeQuestion(Question question) {
        return questions.remove(question);
    }

    public boolean containsQuestion(Question question) {
        return questions.contains(question);
    }

    public Question getQuestion() {
        for (Question question :
                questions) {
            System.out.println("Name: " + question.getName() +
                    ", score: " + question.getScore() +
                    ", type: " + question.getType() +
                    ", level: " + question.getLevel());
        }
        String name = ScannerWrapper.getInstance().readString("Enter question name: ");

        return getQuestionByName(name);
    }

    public Question getQuestionByName(String name) {
        return questions.stream().filter(question -> question.getName().equals(name)).findFirst().orElse(null);
    }
}
