package ir.ac.kntu.db;

import ir.ac.kntu.model.Contest;
import ir.ac.kntu.util.DateTimeUtility;
import ir.ac.kntu.util.ScannerWrapper;

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

    public Contest getContest() {
        for (Contest contest :
                contests) {
            if (contest.getEndDate().compareTo(DateTimeUtility.now()) > 0) {
                System.out.println("Name: " + contest.getName());
            }
        }
        String name = ScannerWrapper.getInstance().readString("Enter contest name: ");

        return getContestByName(name);
    }

    public Contest getContestByName(String name) {
        return contests.stream().filter(contest -> contest.getName().equals(name)).findFirst().orElse(null);
    }
}
