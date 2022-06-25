package ir.ac.kntu.db;

import ir.ac.kntu.model.Contest;

import java.util.ArrayList;

public class ContestDB {
    private ArrayList<Contest> contests;

    public ContestDB(ArrayList<Contest> contests) {
        this.contests = contests;
    }

    public boolean addContest(Contest contest) {
        return contests.add(contest);
    }

    public boolean removeContest(Contest contest) {
        return contests.remove(contest);
    }

    public boolean containsContest(Contest contest) {
        return contests.contains(contest);
    }

    public Contest getContestByName(String name) {
        return contests.stream().filter(contest -> contest.getName().equals(name)).findFirst().orElse(null);
    }
}
