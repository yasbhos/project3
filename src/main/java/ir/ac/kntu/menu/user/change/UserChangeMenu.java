package ir.ac.kntu.menu.user.change;

import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ScannerWrapper;

public class UserChangeMenu implements Menu {
    private User user;
    private UserDB userDB;

    public UserChangeMenu(User user, UserDB userDB) {
        this.user = user;
        this.userDB = userDB;
    }

    @Override
    public void handleMenu() {
        UserChangeMenuOption userChangeMenuOption;
        do {
            userChangeMenuOption = ScannerWrapper.getInstance().readEnum(UserChangeMenuOption.values(), "User Change Menu");
            handleTheOption(userChangeMenuOption);
        } while (userChangeMenuOption != UserChangeMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((UserChangeMenuOption) option) {
            case CHANGE_FIRST_NAME -> changeFirstName();
            case CHANGE_USERNAME -> changeUsername();
            case CHANGE_PASSWORD -> changePassword();
            case CHANGE_EMAIL -> changeEmail();
            case CHANGE_PHONE_NUMBER -> changePhoneNumber();
            case CHANGE_NATIONAL_CODE -> changeNationalCode();
            case BACK -> {

            }
            default -> {
            }
        }
    }

    private void changeFirstName() {
        String firstName = ScannerWrapper.getInstance().readString("Enter new first name: ");
        user.setFirstName(firstName);
        System.out.println("Successfully changed");
    }

    private void changeUsername() {
        String username = ScannerWrapper.getInstance().readString("Enter new username: ");
        if (userDB.getUserByUsername(username) != null) {
            System.out.println("Already used");
            return;
        }
        user.setUsername(username);
    }

    private void changePassword() {
        String password = ScannerWrapper.getInstance().readPassword("Enter new password: ");
        user.setPassword(password);
        System.out.println("Successfully changed");
    }

    private void changeEmail() {
        String email = ScannerWrapper.getInstance().readString("Enter new email: ");
        user.setEmail(email);
        System.out.println("Successfully changed");
    }

    private void changePhoneNumber() {
        String phoneNumber = ScannerWrapper.getInstance().readString("Enter new phone number: ");
        user.setPhoneNumber(phoneNumber);
        System.out.println("Successfully changed");
    }

    private void changeNationalCode() {
        String nationalCode = ScannerWrapper.getInstance().readString("Enter new national code: ");
        user.setNationalCode(nationalCode);
        System.out.println("Successfully changed");
    }
}
