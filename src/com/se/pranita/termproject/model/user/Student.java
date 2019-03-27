package com.se.pranita.termproject.model.user;

import com.google.gson.GsonBuilder;
import com.se.pranita.termproject.utils.TermUtil;

/**
 * Created by Pranita on 14/4/16.
 */
public class Student extends User {

    private String startTerm;
    private int startYear;
    private String program;
    private String department;
    private String phoneNumber;
    private String advisorName;

    public Student() {
        super(UserType.STUDENT);
    }

    public Student(String netID, String password, String firstName, String lastName) {
        super(UserType.STUDENT, netID, password, firstName, lastName);
    }

    @Override
    public String toString() {
        return toJSON();
    }

    public Student(String netID, String password, String firstName, String lastName, String startTerm, int startYear, String program, String department, String phoneNumber, String advisorName) {
        super(UserType.STUDENT, netID, password, firstName, lastName);
        this.startTerm = startTerm;
        this.startYear = startYear;
        this.program = program;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.advisorName = advisorName;
    }

    public String getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(String startTerm) {
        this.startTerm = startTerm;
        notifyAllObservers();
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
        notifyAllObservers();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
        notifyAllObservers();
    }


    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
        notifyAllObservers();
    }

    @Override
    public String toJSON(){
        return new GsonBuilder().create().toJson(Student.this);
    }

    public String getStatus() {
        String message = "Completed %d Semester(s)";
        TermUtil.Term currentTerm = TermUtil.getCurrentTerm();
        int year_diff = (TermUtil.getCurrentYear() - this.startYear) * 2;

        if(currentTerm == TermUtil.Term.SUMMER || currentTerm == TermUtil.Term.FALL) {
            year_diff += 1;
        }

        if(this.startTerm.equalsIgnoreCase("fall"))
            year_diff -= 1;

        return String.format(message, year_diff);

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber != null)
            this.phoneNumber = phoneNumber;
        else
            this.phoneNumber = "";
        notifyAllObservers();
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        if(advisorName != null)
            this.advisorName = advisorName;
        else
            this.advisorName = "";
        notifyAllObservers();
    }

}
