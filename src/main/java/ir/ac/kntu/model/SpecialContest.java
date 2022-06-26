package ir.ac.kntu.model;

import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class SpecialContest extends Contest {
    private class Responder {
        private String groupName;
        private ArrayList<Answer> sentAnswers;

        public Responder(String groupName) {
            this.groupName = groupName;
            this.sentAnswers = new ArrayList<>();
        }

        public boolean addAnswer(Answer answer) {
            return sentAnswers.add(answer);
        }

        public double getScore() {
            return sentAnswers.get(sentAnswers.size() - 1).getScore();
        }
    }

    private static final int MAXIMUM_PARTICIPANTS = 100;

    private int maximumGroupsCapacity;
    private ArrayList<Group> groups;
    private ArrayList<Responder> responders;

    public SpecialContest(String name, DateTime startDate, DateTime endDate, ArrayList<Question> questions,
                          int maximumGroupCapacity) {
        super(name, startDate, endDate, questions);
        this.maximumGroupsCapacity = maximumGroupCapacity;
        this.groups = new ArrayList<>();
        this.responders = new ArrayList<>();
    }

    public int getMaximumGroupsCapacity() {
        return maximumGroupsCapacity;
    }

    public void setMaximumGroupsCapacity(int maximumGroupsCapacity) {
        this.maximumGroupsCapacity = maximumGroupsCapacity;
    }

    public boolean addGroup(String name, ArrayList<User> members) {
        if ((groups.size() + 1) * maximumGroupsCapacity >= MAXIMUM_PARTICIPANTS) {
            return false;
        }

        Group group = new Group(name, maximumGroupsCapacity, members);

        return groups.add(group);
    }

    public boolean removeGroup(Group group) {
        return groups.remove(group);
    }

    public boolean addParticipantToExistingGroup(User participant) {
        for (Group group : groups) {
            System.out.println("Name: " + group.getName());
        }
        String name = ScannerWrapper.getInstance().readString("Enter group name: ");

        for (Group group : groups) {
            if (group.getName().equals(name)) {
                if (group.addMember(participant)) {
                    System.out.println("Successfully added");
                    return true;
                }
            }
        }

        return false;
    }
}
