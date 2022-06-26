package ir.ac.kntu.util;

import ir.ac.kntu.model.*;

import java.util.ArrayList;

public class ContestUtility {
    public enum ContestType {NORMAL, PRIVATE, SPECIAL}

    public static Contest readContest(String message) {
        System.out.println(message);

        String name = ScannerWrapper.getInstance().readString("Enter name: ");
        DateTime startDateTime = DateTimeUtility.readDateTime("Enter start date and time: ");
        DateTime endDateTime = DateTimeUtility.readDateTime("Enter end date and time: ");
        ContestType contestType = ScannerWrapper.getInstance().readEnum(ContestType.values(), "Contest Type");
        switch (contestType) {
            case NORMAL -> new NormalContest(name, startDateTime, endDateTime, new ArrayList<>());
            case PRIVATE -> new PrivateContest(name, startDateTime, endDateTime, new ArrayList<>());
            case SPECIAL -> {
                int maxGroupCapacity = ScannerWrapper.getInstance().readInt("Enter maximum group capacity: ");
                return new SpecialContest(name, startDateTime, endDateTime, new ArrayList<>(), maxGroupCapacity);
            }
            default -> {
            }
        }
        return null;
    }
}
