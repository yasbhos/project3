package ir.ac.kntu.db;

import ir.ac.kntu.model.Question;

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

    public Question getQuestionByName(String name) {
        return questions.stream().filter(question -> question.getName().equals(name)).findFirst().orElse(null);
    }
}
