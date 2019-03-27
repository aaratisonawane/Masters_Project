package com.se.pranita.termproject.model;

import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

/**
 * Created by Pranita on 23/4/16.
 */
public class Discussion {
    private int id;
    private int discussion_id;
    private String netID;
    private String title;
    private DiscussionType type;
    private String details;
    private Timestamp create_time;
    private Timestamp updated_time;
    private String ownerName;

    public Discussion() {
    }

    public Discussion(DiscussionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscussion_id() {
        return discussion_id;
    }

    public void setDiscussion_id(int discussion_id) {
        this.discussion_id = discussion_id;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DiscussionType getType() {
        return type;
    }

    public void setType(DiscussionType type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Timestamp updated_time) {
        this.updated_time = updated_time;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public enum DiscussionType {
        TOPIC(0), REPLY(1);

        int value;

        DiscussionType(int value) {
            this.value = value;
        }

        public static Discussion.DiscussionType getDiscussionType(String type) {
            if (type.equalsIgnoreCase("topic"))
                return TOPIC;
            else if (type.equalsIgnoreCase("reply"))
                return REPLY;
            return null;
        }

        @Override
        public String toString() {
            switch (value) {
                case 0:
                    return "Topic";
                case 1:
                    return "Reply";
                default:
                    return "Discussion";
            }
        }

        public static DiscussionType getDiscussionType(int type) {
            switch (type) {
                case 0:
                    return TOPIC;
                case 1:
                    return REPLY;
                default:
                    return DiscussionType.TOPIC;
            }
        }

        public int getValue() {
            return this.value;
        }
    }

}
