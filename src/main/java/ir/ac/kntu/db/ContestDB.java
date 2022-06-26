package ir.ac.kntu.db;

import ir.ac.kntu.model.Contest;
import ir.ac.kntu.model.PrivateContest;
import ir.ac.kntu.model.User;
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

    public Contest getContestForAdmin() {
        for (Contest contest : contests) {
            System.out.println("ID: " + contest.getId() +
                    "Name: " + contest.getName());
        }
        String id = ScannerWrapper.getInstance().readString("Enter contest id: ");

        return getContestByID(id);
    }

    public Contest getContestForUser(User currentUser) {
        for (Contest contest : contests) {
            if (contest instanceof PrivateContest privateContest &&
                    !privateContest.canParticipants(currentUser)) {
                continue;
            }
            System.out.println("ID: " + contest.getId() +
                    "Name: " + contest.getName());
        }
        String id = ScannerWrapper.getInstance().readString("Enter contest id: ");

        return getContestByID(id);
    }

    public Contest getContestForGuest() {
        for (Contest contest : contests) {
            if (contest.getEndDate().compareTo(DateTimeUtility.now()) > 0) {
                System.out.println("ID: " + contest.getId() +
                        "Name: " + contest.getName());
            }
        }
        String id = ScannerWrapper.getInstance().readString("Enter contest id: ");

        return getContestByID(id);
    }

    public Contest getContestByID(String id) {
        return contests.stream().filter(contest -> contest.getId().equals(id)).findFirst().orElse(null);
    }
}
