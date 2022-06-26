package ir.ac.kntu.model;

import java.util.ArrayList;

public class NormalContest extends Contest {
    private static final int MAXIMUM_PARTICIPANTS = 50;

    private ArrayList<User> participants;
    private ArrayList<Responder> responders;

    public NormalContest(String name, DateTime startDate, DateTime endDate, ArrayList<Question> questions) {
        super(name, startDate, endDate, questions);
        this.participants = new ArrayList<>(50); //Maximum number of participants is 50 User
        this.responders = new ArrayList<>();
    }

    public boolean addParticipant(User participant) {
        if (participants.size() >= MAXIMUM_PARTICIPANTS) {
            System.out.println("The capacity is full");
            return false;
        }

        return participants.add(participant);
    }

    public boolean removeParticipant(User participant) {
        return participants.remove(participant);
    }

    private class Responder {
        private String username;
        private ArrayList<Answer> sentAnswers;

        public Responder(String username) {
            this.username = username;
            this.sentAnswers = new ArrayList<>();
        }

        public boolean addAnswer(Answer answer) {
            return sentAnswers.add(answer);
        }

        public double getScore() {
            return sentAnswers.get(sentAnswers.size() - 1).getScore();
        }
    }
}
