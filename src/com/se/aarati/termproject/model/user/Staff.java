package com.se.aarati.termproject.model.user;

/**
 * Created by aarati on 14/4/19.
 */
public class Staff extends User {

    public Staff() {
        super(UserType.STAFF);
    }

    public Staff(String netID, String password, String firstName, String lastName) {
        super(UserType.STAFF, netID, password, firstName, lastName);
    }
}
