package ir.ac.kntu.menu.user.change;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.User;
import ir.ac.kntu.util.ScannerWrapper;

public class UserChangeMenu implements Menu {
    private User user;

    public UserChangeMenu(User user) {
        this.user = user;
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

    //TODO
    private void changeFirstName() {
    }

    //TODO
    private void changeUsername() {
    }

    //TODO
    private void changePassword() {
    }

    //TODO
    private void changeEmail() {
    }

    //TODO
    private void changePhoneNumber() {
    }

    //TODO
    private void changeNationalCode() {
    }
}
