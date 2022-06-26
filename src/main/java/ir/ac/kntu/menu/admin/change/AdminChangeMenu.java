package ir.ac.kntu.menu.admin.change;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.Admin;
import ir.ac.kntu.util.ScannerWrapper;

public class AdminChangeMenu implements Menu {
    private Admin admin;

    public AdminChangeMenu(Admin admin) {
        this.admin = admin;
    }

    @Override
    public void handleMenu() {
        AdminChangeMenuOption adminChangeMenuOption;
        do {
            adminChangeMenuOption = ScannerWrapper.getInstance().readEnum(AdminChangeMenuOption.values(), "Admin Change Menu");
            handleTheOption(adminChangeMenuOption);
        } while (adminChangeMenuOption != AdminChangeMenuOption.BACK);
    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {
        switch ((AdminChangeMenuOption) option) {
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
