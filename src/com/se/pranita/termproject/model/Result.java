package com.se.pranita.termproject.model;

import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

/**
 * Created by Pranita on 24/4/16.
 */
public class Result {
    private int resultID;
    private String examName;
    private String resultDetails;
    private Timestamp createTime;
    private String netID;
    private String ownerName;
    private boolean owner;
    private int examID;

    public Result() {
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(String resultDetails) {
        this.resultDetails = resultDetails;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isOwner() {
        return owner;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }
}
