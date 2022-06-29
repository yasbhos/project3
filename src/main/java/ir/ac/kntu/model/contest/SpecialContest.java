package ir.ac.kntu.model.contest;

import ir.ac.kntu.model.DateTime;
import ir.ac.kntu.model.GroupResponder;
import ir.ac.kntu.model.User;
import ir.ac.kntu.model.question.Question;
import ir.ac.kntu.util.DateTimeUtility;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.Collections;

public class SpecialContest extends Contest {
    private static final int MAXIMUM_PARTICIPANTS = 100;

    private int maximumGroupsCapacity;

    private final ArrayList<GroupResponder> responders;

    public SpecialContest(User ownerAdmin, String name, DateTime startDateTime, DateTime endDateTime,
                          ArrayList<Question> questions, int maximumGroupCapacity) {
        super(ownerAdmin, name, startDateTime, endDateTime, questions);
        this.maximumGroupsCapacity = maximumGroupCapacity;
        this.responders = new ArrayList<>();
    }

    public int getMaximumGroupsCapacity() {
        return maximumGroupsCapacity;
    }

    public void setMaximumGroupsCapacity(int maximumGroupsCapacity) {
        this.maximumGroupsCapacity = maximumGroupsCapacity;
    }

    public boolean addGroup(String name, ArrayList<User> members) {
        if ((responders.size() + 1) * maximumGroupsCapacity >= MAXIMUM_PARTICIPANTS) {
            System.out.println("The capacity is full");
            return false;
        }

        GroupResponder responder = new GroupResponder(name, members);

        return responders.add(responder);
    }

    public boolean addParticipantToExistingGroup(User participant) {
        for (GroupResponder responder : responders) {
            System.out.println("Name: " + responder.getName());
        }
        String name = ScannerWrapper.getInstance().readString("Enter group name: ");

        GroupResponder target = responders.stream().filter(groupResponder -> groupResponder.getName().equals(name)
        ).findFirst().orElse(null);
        if (target == null) {
            System.out.println("Invalid name");
            return false;
        }

        if (target.addMember(participant)) {
            System.out.println("Successfully added");
            return true;
        }

        return false;
    }

    @Override
    public void scoreBoard() {
        Collections.sort(responders);
        System.out.println("Scoreboard for contest " + super.getName());
        System.out.println("------------------------------------------------------------");
        System.out.println("| Group name | Total Score | Average sent DateTime");
        responders.forEach(System.out::println);
        System.out.println("------------------------------------------------------------");
    }

    @Override
    public void updateResponder(String username) {
        GroupResponder target = getTarget(username);
        if (target == null) {
            return;
        }

        double totalScore = 0;
        ArrayList<DateTime> sentDTs = new ArrayList<>();

        for (Question question : super.getQuestions()) {
            ArrayList<Question.Responder> responders = getTargetGroupAnswers(target, question);
            Collections.sort(responders);

            totalScore += responders.get(0).getFinalAnswer().getScoreWithDelay();
            sentDTs.add(responders.get(0).getFinalAnswer().getSentDateTime());
        }

        target.setTotalScore(totalScore);
        target.setAverageSentDT(DateTimeUtility.getAverageSentDateTimes(sentDTs));
    }

    private GroupResponder getTarget(String username) {
        for (GroupResponder responder : responders) {
            for (User user : responder.getMembers()) {
                if (user.getUsername().equals(username)) {
                    return responder;
                }
            }
        }

        return null;
    }

    private ArrayList<Question.Responder> getTargetGroupAnswers(GroupResponder target, Question question) {
        ArrayList<Question.Responder> responders = new ArrayList<>();
        for (Question.Responder responder : question.getResponders()) {
            for (User user : target.getMembers()) {
                if (user.getUsername().equals(responder.getUsername())) {
                    responders.add(responder);
                }
            }
        }

        return responders;
    }
}
