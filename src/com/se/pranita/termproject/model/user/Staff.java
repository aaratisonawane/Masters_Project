package com.se.pranita.termproject.model.user;

/**
 * Created by Pranita on 14/4/16.
 */
public class Staff extends User {

    public Staff() {
        super(UserType.STAFF);
    }

    public Staff(String netID, String password, String firstName, String lastName) {
        super(UserType.STAFF, netID, password, firstName, lastName);
    }
}
