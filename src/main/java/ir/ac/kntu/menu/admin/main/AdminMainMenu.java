package ir.ac.kntu.menu.admin.main;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.User;

public class AdminMainMenu implements Menu {
    private User currentAdmin;

    public AdminMainMenu(User currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    @Override
    public void handleMenu() {

    }

    @Override
    public void showOptions() {

    }

    @Override
    public <T extends Enum<T>> void handleTheOption(T option) {

    }
}
