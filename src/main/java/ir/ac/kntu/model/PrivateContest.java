package ir.ac.kntu.model;

import java.util.ArrayList;

public class PrivateContest extends Contest {
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

    private static final int MAXIMUM_PARTICIPANTS = 20;

    private ArrayList<User> participants;
    private ArrayList<Responder> responders;

    public PrivateContest(String name, DateTime startDate, DateTime endDate, ArrayList<Question> questions) {
        super(name, startDate, endDate, questions);
        this.participants = new ArrayList<>(20); //Maximum number of participants is 20 User
        this.responders = new ArrayList<>();
    }

    public boolean addParticipant(User participant) {
        if (!participants.contains(participant)) {
            participants.add(participant);
            return true;
        }
        return false;
    }

    public boolean removeParticipant(User participant) {
        return participants.remove(participant);
    }
}
