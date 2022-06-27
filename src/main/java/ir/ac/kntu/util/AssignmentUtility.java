package ir.ac.kntu.util;

import ir.ac.kntu.model.Assignment;
import ir.ac.kntu.model.Assignment.Status;
import ir.ac.kntu.model.DateTime;

public class AssignmentUtility {
    public static Assignment readAssignment(String message) {
        System.out.println(message);

        String name = ScannerWrapper.getInstance().readString("Enter name: ");
        String description = ScannerWrapper.getInstance().readString("Enter description: ");
        DateTime startDate = DateTimeUtility.readDateTime("Enter start date: ");
        DateTime endDate = DateTimeUtility.readDateTime("Enter end date: ");
        int delayCoefficient = ScannerWrapper.getInstance().readInt("Enter delay coefficient: ");
        DateTime delayDate = DateTimeUtility.readDateTime("Enter delay date and time: ");
        Status assignmentStatus = ScannerWrapper.getInstance().readEnum(Status.values(), "STATUS", "Enter assignment state: ");
        Status scoreBoardStatus = ScannerWrapper.getInstance().readEnum(Status.values(), "STATUS", "Enter scoreBoard state: ");
        Assignment assignment = new Assignment(name, description, startDate, endDate, delayCoefficient, delayDate, assignmentStatus,
                scoreBoardStatus, true);

        return assignment;
    }
}
