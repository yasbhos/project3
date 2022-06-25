package ir.ac.kntu.util;

import ir.ac.kntu.model.User;

public class UserUtility {
    public static User readUser(String massage) {
        System.out.println(massage);

        String firstName = ScannerWrapper.getInstance().readString("Enter firstname: ");
        String userName = ScannerWrapper.getInstance().readString("Enter username: ");
        String passToHash = ScannerWrapper.getInstance().readPassword("Enter password: ");
        if (passToHash == null) {
            return null;
        }
        String email = ScannerWrapper.getInstance().readString("Enter email: ");
        String phoneNumber = ScannerWrapper.getInstance().readString("Enter phone number: ");
        String nationalCode = ScannerWrapper.getInstance().readString("Enter national code: ");

        return new User(firstName, userName, passToHash, email, phoneNumber, nationalCode);
    }
}
