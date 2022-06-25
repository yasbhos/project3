package ir.ac.kntu.menu.user.main;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.model.User;

public class UserMainMenu implements Menu {
    private User currentUser;

    public UserMainMenu(User currentUser) {
        this.currentUser = currentUser;
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
