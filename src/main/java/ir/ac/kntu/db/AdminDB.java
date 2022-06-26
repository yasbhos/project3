package ir.ac.kntu.db;

import ir.ac.kntu.model.Admin;
import ir.ac.kntu.model.Admin;
import ir.ac.kntu.util.Cipher;

import java.util.ArrayList;

public class AdminDB {
    private ArrayList<Admin> admins;

    public AdminDB(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public boolean addAdmin(Admin admin) {
        return admins.add(admin);
    }

    public boolean removeAdmin(Admin admin) {
        return admins.remove(admin);
    }

    public boolean containsAdmin(Admin admin) {
        return admins.contains(admin);
    }

    public Admin getAdminByFirstName(String firstName) {
        return admins.stream().filter(admin -> admin.getFirstName().equals(firstName)).findFirst().orElse(null);
    }

    public Admin getAdminByFirstNameAndPassword(String firstName, String password) {
        return admins.stream().filter(Admin ->
                Admin.getFirstName().equals(firstName) && Admin.getHashedPassword().equals(Cipher.sha256(password))
        ).findFirst().orElse(null);
    }
}
