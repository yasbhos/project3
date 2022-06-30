package ir.ac.kntu.model.contest;

import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.model.DateTime;
import ir.ac.kntu.model.SingleResponder;
import ir.ac.kntu.model.User;
import ir.ac.kntu.model.question.Question;
import ir.ac.kntu.util.DateTimeUtility;

import java.util.ArrayList;
import java.util.Collections;

public class NormalContest extends Contest {
    private static final int MAXIMUM_PARTICIPANTS = 50;

    private final ArrayList<User> participants;

    private final ArrayList<SingleResponder> responders;

    public NormalContest(User ownerAdmin, String name, DateTime startDateTime, DateTime endDateTime, ArrayList<Question> questions) {
        super(ownerAdmin, name, startDateTime, endDateTime, questions);
        this.participants = new ArrayList<>();
        this.responders = new ArrayList<>();
    }

    public boolean addParticipant(User participant) {
        if (isCapacityFilled()) {
            System.out.println("The capacity is full");
            return false;
        }

        return participants.add(participant);
    }

    public boolean isCapacityFilled() {
        return participants.size() >= MAXIMUM_PARTICIPANTS;
    }

    @Override
    public void scoreBoard() {
        Collections.sort(responders);
        System.out.println("Scoreboard for contest " + super.getName());
        System.out.println("------------------------------------------------------------");
        System.out.println("| Username | Total Score | Average sent DateTime");
        responders.forEach(System.out::println);
        System.out.println("------------------------------------------------------------");
    }

    @Override
    public void updateResponder(String username) {
        double totalScore = 0;
        ArrayList<DateTime> sentDTs = new ArrayList<>();

        for (Question question : super.getQuestions()) {
            Question.Responder responder = question.getResponderByUsername(username);
            if (responder == null) {
                continue;
            }

            totalScore += responder.getFinalAnswer().getScoreWithDelay();
            sentDTs.add(responder.getFinalAnswer().getSentDateTime());
        }

        SingleResponder responder = new SingleResponder(username);
        responder.setTotalScore(totalScore);
        responder.setAverageSentDT(DateTimeUtility.getAverageSentDateTimes(sentDTs));
        responders.remove(responder);
        responders.add(responder);
    }

    public void finalResult(UserDB userDB) {
        if (DateTimeUtility.now().compareTo(super.getEndDateTime()) <= 0) {
            System.out.println("The contest is not over yet");
            return;
        }

        Collections.sort(responders);
        for (int i = 0; i < 5; i++) {
            responders.get(i).setTotalScore(responders.get(i).getTotalScore() + 20);
        }

        System.out.println("Final Result of contest " + super.getName());
        System.out.println("------------------------------------------------------------");
        System.out.println("| Username | Total Score | Average sent DateTime");
        responders.forEach(System.out::println);
        System.out.println("------------------------------------------------------------");

        for (SingleResponder responder : responders) {
            User target = userDB.getUserByUsername(responder.getUsername());
            if (target == null) {
                return;
            }

            target.setRating(target.getRating() + responder.getTotalScore());
        }
    }
}
