package ir.ac.kntu.model;

import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.Collections;

public class SpecialContest extends Contest {
    private static final int MAXIMUM_PARTICIPANTS = 100;

    private int maximumGroupsCapacity;

    private final ArrayList<Group> groups;

    public SpecialContest(User ownerAdmin, String name, DateTime startDateTime, DateTime endDateTime, ArrayList<Question> questions,
                          int maximumGroupCapacity) {
        super(ownerAdmin, name, startDateTime, endDateTime, questions);
        this.maximumGroupsCapacity = maximumGroupCapacity;
        this.groups = new ArrayList<>();
    }

    public int getMaximumGroupsCapacity() {
        return maximumGroupsCapacity;
    }

    public void setMaximumGroupsCapacity(int maximumGroupsCapacity) {
        this.maximumGroupsCapacity = maximumGroupsCapacity;
    }

    public boolean addGroup(String name, ArrayList<User> members) {
        if ((groups.size() + 1) * maximumGroupsCapacity >= MAXIMUM_PARTICIPANTS) {
            System.out.println("The capacity is full");
            return false;
        }

        Group group = new Group(name, members);

        return groups.add(group);
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

    @Override
    public void scoreBoard() {
        Collections.sort(groups);
        System.out.println("Scoreboard for " + super.getName());
        System.out.println("------------------------------------------------------------");
        System.out.println("| Group name | Total Mark | Average Time");
        groups.forEach(System.out::println);
        System.out.println("------------------------------------------------------------");
    }

    private class Group implements Comparable<Group> {
        private String name;

        private final ArrayList<User> members;

        private int totalScore;

        private int ranking;

        //TODO: think about this field, really long? It can be DateTime!
        private long averageSentTime;

        public Group(String name, ArrayList<User> members) {
            this.name = name;
            this.members = members;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean addMember(User member) {
            return members.add(member);
        }

        public int getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(int totalScore) {
            this.totalScore = totalScore;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public long getAverageSentTime() {
            return averageSentTime;
        }

        public void setAverageSentTime(long averageSentTime) {
            this.averageSentTime = averageSentTime;
        }

        @Override
        public int compareTo(Group o) {
            if (this.totalScore > o.totalScore) {
                return 1;
            }
            return Long.compare(o.averageSentTime, this.averageSentTime);
        }

        @Override
        public String toString() {
            return name + " | " + totalScore + " | " + averageSentTime;
        }
    }
}
