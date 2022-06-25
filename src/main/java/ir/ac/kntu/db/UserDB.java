package ir.ac.kntu.db;

import ir.ac.kntu.model.User;

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

    public boolean containsUser(User user) {
        return users.contains(user);
    }

    public User getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return users.stream().filter(user ->
                user.getUsername().equals(username) && user.getPassword().equals(password)
        ).findFirst().orElse(null);
    }
}
