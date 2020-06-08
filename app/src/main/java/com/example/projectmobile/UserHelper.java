package com.example.projectmobile;

public class UserHelper {

    String username;
    String fullname;
    String password;
    String reenter;
    String email;

    public UserHelper(String username, String fullname, String password, String reenter, String email) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.reenter = reenter;
        this.email = email;
    }

    public UserHelper() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
