package com.se.pranita.termproject.model.user;

/**
 * Created by Pranita on 14/4/16.
 */
public class Faculty extends User {

    public Faculty() {
        super(UserType.FACULTY);
    }

    public Faculty(String netID, String password, String firstName, String lastName) {
        super(UserType.FACULTY, netID, password, firstName, lastName);
    }
}
