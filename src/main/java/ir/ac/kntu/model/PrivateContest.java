package ir.ac.kntu.model;

import java.util.ArrayList;

public class PrivateContest extends Contest {
    private static final int MAXIMUM_PARTICIPANTS = 20;

    private ArrayList<User> participants;

    public PrivateContest(String name, DateTime startDate, DateTime endDate, ArrayList<Question> questions) {
        super(name, startDate, endDate, questions);
        this.participants = new ArrayList<>(20); //Maximum number of participants is 20 User
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
