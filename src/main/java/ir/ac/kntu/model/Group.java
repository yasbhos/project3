package ir.ac.kntu.model;

import java.util.ArrayList;
import java.util.Objects;

public class Group {
    private String name;
    private int maxCapacity;
    private ArrayList<User> members;
    private int rating; //total score of group
    private int ranking;

    public Group(String name, int maxCapacity, ArrayList<User> members) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.members = members;
        this.rating = 0;
        this.ranking = 0;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addMember(User member) {
        if (!members.contains(member) && (members.size() < maxCapacity)) {
            return members.add(member);
        }
        return false;
    }

    public boolean removeMember(User member) {
        return members.remove(member);
    }

    @Override
    public String toString() {
        String groupToString = "Group{" +
                "name='" + name + '\'' +
                ", members=\n";
        for (User member : members) {
            groupToString += member.toString() + "\n";
        }
        groupToString += ", rating=" + rating +
                ", ranking=" + ranking +
                '}';

        return groupToString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Group)) {
            return false;
        }
        Group group = (Group) o;
        return maxCapacity == group.maxCapacity && rating == group.rating && ranking == group.ranking && Objects.equals(name, group.name) && Objects.equals(members, group.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxCapacity, members, rating, ranking);
    }
}
