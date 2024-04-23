package com.example.fitnessapp.Model;

import java.io.Serializable;

public class User implements Serializable {

    private String uID, UserName, uEmail, uPassword, uJobTitle, uAge;

    public User() {
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuJobTitle() {
        return uJobTitle;
    }

    public void setuJobTitle(String uJobTitle) {
        this.uJobTitle = uJobTitle;
    }

    public String getuAge() {
        return uAge;
    }

    public void setuAge(String uAge) {
        this.uAge = uAge;
    }
}


