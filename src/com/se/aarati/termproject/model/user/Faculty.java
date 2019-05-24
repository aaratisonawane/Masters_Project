package com.se.aarati.termproject.model.user;

/**
 * Created by aarati on 14/5/19.
 */
public class Faculty extends User {

    public Faculty() {
        super(UserType.FACULTY);
    }

    public Faculty(String netID, String password, String firstName, String lastName) {
        super(UserType.FACULTY, netID, password, firstName, lastName);
    }
}
