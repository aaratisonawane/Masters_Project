package com.se.pranita.termproject.model.user;

import com.google.gson.GsonBuilder;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.model.Course;
import com.se.pranita.termproject.utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranita on 14/4/16.
 */
public abstract class User {

    private String netID;
    private String password;
    private String firstName;
    private String lastName;
    private UserType type;
    private ArrayList<Course> courses;
    private transient List<Observer> observers = new ArrayList<Observer>();

    public User(UserType type) {
        this.type = type;
    }

    public User(UserType type, String netID, String password, String firstName, String lastName) {
        this.type = type;
        this.netID = netID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
        notifyAllObservers();
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyAllObservers();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyAllObservers();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyAllObservers();
    }

    public String toJSON() {
        return new GsonBuilder().create().toJson(this);
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
        notifyAllObservers();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public enum UserType {
        STUDENT(0), FACULTY(1), STAFF(2);

        int value;

        UserType(int value) {
            this.value = value;
        }

        public static UserType getUserType(String type) {
            if (type.equalsIgnoreCase("student"))
                return STUDENT;
            else if (type.equalsIgnoreCase("faculty"))
                return FACULTY;
            else if (type.equalsIgnoreCase("staff"))
                return STAFF;
            else
                return null;
        }

        public int getValue() {
            return this.value;
        }
    }

    @Override
    public String toString() {
        return toJSON();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        observers.forEach(Observer::update);
    }
}
