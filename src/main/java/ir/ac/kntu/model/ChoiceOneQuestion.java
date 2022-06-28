package ir.ac.kntu.model;

import ir.ac.kntu.util.ScannerWrapper;

public class ChoiceOneQuestion extends Question {
    private class Choices {
        private String a, b, c, d;

        public Choices(String a, String b, String c, String d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public String getA() {
            return a;
        }

        public String getB() {
            return b;
        }

        public String getC() {
            return c;
        }

        public String getD() {
            return d;
        }
    }

    private Choices choices;
    private String correctAnswer;

    public ChoiceOneQuestion(String name, double score, String description, QuestionType type, QuestionLevel level,
                             String a, String b, String c, String d, String correctAnswer) {
        super(name, score, description, type, level);
        this.choices = new Choices(a, b, c, d);
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public Answer readAnswer(User user, String message) {
        //TODO: make this function clear and more readable
        System.out.println(message);
        enum Option {
            A, B, C, D
        }

        Option option = ScannerWrapper.getInstance().readEnum(Option.values(), "", "Enter correct option");
        return switch (option) {
            case A ->  new Answer("A", this, user.getUsername());
            case B ->  new Answer("B", this, user.getUsername());
            case C ->  new Answer("C", this, user.getUsername());
            case D ->  new Answer("D", this, user.getUsername());
        };
    }

    @Override
    public String toString() {
        //TODO: make this function clear and more readable
        String questionToString = "Question{" +
                "name='" + super.getName() + '\'' +
                ", score=" + super.getScore() +
                "\ndescription='" + super.getDescription() + "'\n";

        questionToString += "Options:\n";
        questionToString += choices.getA() + "\n";
        questionToString += choices.getB() + "\n";
        questionToString += choices.getC() + "\n";
        questionToString += choices.getD() + "\n";

        questionToString += "type=" + super.getType() +
                ", level=" + super.getLevel() +
                "\nuploadDateTime=" + super.getUploadDateTime() +
                "\n}";

        return questionToString;
    }
}
