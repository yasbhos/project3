package ir.ac.kntu.util;

import ir.ac.kntu.model.Assignment;
import ir.ac.kntu.model.Assignment.Status;
import ir.ac.kntu.model.DateTime;

public class AssignmentUtility {
    public static Assignment readAssignment(String message) {
        System.out.println(message);
        String name = ScannerWrapper.getInstance().readString("Enter name: ");
        String description = ScannerWrapper.getInstance().readString("Enter description: ");
        DateTime startDateTime = DateTimeUtility.readDateTime("Enter start date and time");
        DateTime endDateTime = DateTimeUtility.readDateTime("Enter end date and time");
        int delayCoefficient = ScannerWrapper.getInstance().readInt("Enter delay coefficient: ");
        DateTime delayDateTime = DateTimeUtility.readDateTime("Enter delay date and time");
        Status assignmentStatus = ScannerWrapper.getInstance().readEnum(Status.values(), "STATUS", "Enter assignment state");
        Status scoreBoardStatus = ScannerWrapper.getInstance().readEnum(Status.values(), "STATUS", "Enter scoreBoard state");
        return new Assignment(name, description, startDateTime, endDateTime, delayCoefficient, delayDateTime, assignmentStatus,
                scoreBoardStatus, true);
    }
}
