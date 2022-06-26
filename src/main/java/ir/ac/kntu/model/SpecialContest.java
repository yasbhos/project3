package ir.ac.kntu.model;

import java.util.ArrayList;

public class SpecialContest extends Contest {
    private static final int MAXIMUM_PARTICIPANTS = 100;

    private int maximumGroupsCapacity;
    private ArrayList<Group> groups;
    private ArrayList<User> whosCanParticipant;

    public SpecialContest(String name, DateTime startDate, DateTime endDate, ArrayList<Question> questions,
                          int maximumGroupCapacity) {
        super(name, startDate, endDate, questions);
        this.maximumGroupsCapacity = maximumGroupCapacity;
        this.groups = new ArrayList<>();
        this.whosCanParticipant = new ArrayList<>();
    }

    public int getMaximumGroupsCapacity() {
        return maximumGroupsCapacity;
    }

    public void setMaximumGroupsCapacity(int maximumGroupsCapacity) {
        this.maximumGroupsCapacity = maximumGroupsCapacity;
    }

    public boolean addGroup(String name, ArrayList<User> members) {
        Group group = new Group(name, maximumGroupsCapacity, members);
        return groups.add(group);
    }

    public boolean removeGroup(Group group) {
        return groups.remove(group);
    }

    public boolean addWhosCanParticipant(User user) {
        return whosCanParticipant.add(user);
    }

    public boolean removeWhosCanParticipant(User user) {
        return whosCanParticipant.remove(user);
    }
}
