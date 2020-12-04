package com.sonusahu.kobonat.authUtils;

public class UserProfile {

    String name,email,uid,password,phoneNumber,userProf;

    public UserProfile(String name, String email, String uid, String password) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.password = password;
    }

    public UserProfile(String name, String email, String uid, String password, String phoneNumber, String userProf) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userProf = userProf;
    }
}
