package ir.ac.kntu.model;

import ir.ac.kntu.util.Cipher;

import java.util.Objects;

public class Admin {
    private String firstName;
    private String username;
    private String hashedPassword;
    private String email;
    private String phoneNumber;
    private String nationalCode;


    public Admin(String firstName, String username, String password, String email, String phoneNumber, String nationalCode) {
        this.firstName = firstName;
        this.username = username;
        this.hashedPassword = Cipher.sha256(password);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String password) {
        this.hashedPassword = Cipher.sha256(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(username, admin.username) && Objects.equals(nationalCode, admin.nationalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, nationalCode);
    }
}
