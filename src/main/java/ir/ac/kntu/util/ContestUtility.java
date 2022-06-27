package ir.ac.kntu.util;

import ir.ac.kntu.model.*;

import java.util.ArrayList;

public class ContestUtility {
    public enum ContestType {
        NORMAL,
        PRIVATE,
        SPECIAL
    }

    public static Contest readContest(User ownerAdmin, String message) {
        System.out.println(message);

        String name = ScannerWrapper.getInstance().readString("Enter name: ");
        DateTime startDateTime = DateTimeUtility.readDateTime("Enter start date-time");
        DateTime endDateTime = DateTimeUtility.readDateTime("Enter end date-time");
        ContestType contestType = ScannerWrapper.getInstance().readEnum(ContestType.values(), "Contest Type");

        Contest contest =
                switch (contestType) {
                    case NORMAL -> new NormalContest(ownerAdmin, name, startDateTime, endDateTime, new ArrayList<>(),
                            true);
                    case PRIVATE -> new PrivateContest(ownerAdmin, name, startDateTime, endDateTime, new ArrayList<>(),
                            true);
                    case SPECIAL -> {
                        int maxGroupCapacity = ScannerWrapper.getInstance().readInt("Enter maximum group capacity: ");
                        yield new SpecialContest(ownerAdmin, name, startDateTime, endDateTime, new ArrayList<>(),
                                maxGroupCapacity, true);
                    }
                    default -> null;
                };

        return contest;
    }
}
