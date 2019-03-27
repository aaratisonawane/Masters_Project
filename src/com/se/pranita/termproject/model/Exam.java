package com.se.pranita.termproject.model;

import com.google.gson.GsonBuilder;

/**
 * Created by Pranita on 24/4/16.
 */
public class Exam {
    private int examID;
    private String netID;
    private String name;
    private String dateOfExam;
    private String additionalDetails;
    private String ownerName;
    private boolean enrolled;
    private boolean expired;

    public Exam() {
    }

    public Exam(int examID, String netID, String name, String dateOfExam, String additionalDetails, String ownerName) {
        this.examID = examID;
        this.netID = netID;
        this.name = name;
        this.dateOfExam = dateOfExam;
        this.additionalDetails = additionalDetails;
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(String dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
