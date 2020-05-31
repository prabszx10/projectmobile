package com.example.projectmobile;

public class UserHelper {

    String username;
    String password;
    String reenter;
    String email;

    public UserHelper() {
    }

    public UserHelper(String username, String password, String reenter, String email) {
        this.username = username;
        this.password = password;
        this.reenter = reenter;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReenter() {
        return reenter;
    }

    public void setReenter(String reenter) {
        this.reenter = reenter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
