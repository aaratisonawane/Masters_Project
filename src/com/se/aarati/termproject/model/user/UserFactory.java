package com.se.aarati.termproject.model.user;

/**
 * Created by aarati on 19/4/19.
 */
public class UserFactory {

    public User getUser(int userType) {

        if (userType == User.UserType.FACULTY.getValue()) {
            return new Faculty();

        } else if (userType == User.UserType.STUDENT.getValue()) {
            return new Student();

        } else if (userType == User.UserType.STAFF.getValue()) {
            return new Staff();
        }
        return null;
    }
}
