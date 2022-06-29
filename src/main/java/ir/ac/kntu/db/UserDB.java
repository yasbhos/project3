package ir.ac.kntu.db;

import ir.ac.kntu.model.User;
import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class UserDB {
    private ArrayList<User> users;

    public UserDB(ArrayList<User> users) {
        this.users = users;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public User getUser() {
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + ", FirstName: " + user.getFirstName());
        }
        String username = ScannerWrapper.getInstance().readString("Enter username: ");
        User user = getUserByUsername(username);
        if (user == null) {
            System.out.println("Invalid username");
            return null;
        }

        return user;
    }

    public User getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return users.stream().filter(user ->
                user.getUsername().equals(username) && user.getHashedPassword().equals(Cipher.sha256(password))
        ).findFirst().orElse(null);
    }

    public boolean isUsernameUnique(String username) {
        return getUserByUsername(username) == null;
    }
}
