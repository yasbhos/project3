package ir.ac.kntu.model;

import java.util.ArrayList;
import java.util.Collections;

public class NormalContest extends Contest {
    private static final int MAXIMUM_PARTICIPANTS = 50;
    private final ArrayList<User> participants;
    private final ArrayList<Responder> responders;

    public NormalContest(User ownerAdmin, String name, DateTime startDateTime, DateTime endDateTime, ArrayList<Question> questions) {
        super(ownerAdmin, name, startDateTime, endDateTime, questions);
        this.participants = new ArrayList<>();
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

    @Override
    public void scoreBoard() {
        Collections.sort(responders);
        System.out.println("Scoreboard for " + super.getName());
        System.out.println("------------------------------------------------------------");
        System.out.println("| Name of user | Total Mark | Average Time");
        responders.forEach(System.out::println);
        System.out.println("------------------------------------------------------------");
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
