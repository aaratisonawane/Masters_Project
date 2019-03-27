package com.se.pranita.termproject.model.user;

/**
 * Created by Pranita on 19/4/16.
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
