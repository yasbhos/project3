package ir.ac.kntu.model;

import java.util.ArrayList;

public class PrivateContest extends Contest {
    private static final int MAXIMUM_PARTICIPANTS = 20;

    private ArrayList<User> participants;
    private ArrayList<User> whosCanParticipant;
    private ArrayList<Responder> responders;

    public PrivateContest(String name, DateTime startDate, DateTime endDate, ArrayList<Question> questions) {
        super(name, startDate, endDate, questions);
        this.participants = new ArrayList<>(20); //Maximum number of participants is 20 User
        this.whosCanParticipant = new ArrayList<>();
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

    public boolean addWhosCanParticipant(User user) {
        return whosCanParticipant.add(user);
    }

    public boolean removeWhosCanParticipant(User user) {
        return whosCanParticipant.remove(user);
    }

    public boolean canParticipants(User user) {
        return whosCanParticipant.contains(user);
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
