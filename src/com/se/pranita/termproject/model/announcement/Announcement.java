package com.se.pranita.termproject.model.announcement;

import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

/**
 * Created by Pranita on 23/4/16.
 */
public abstract class Announcement {
    private Integer id;
    private String netID;
    private String link;
    private String title;
    private String details;
    private AnnouncementType type;
    private Timestamp createTime;
    private String ownerName;

    public Announcement(Integer id, String netID, String link, String title, String details, AnnouncementType type, Timestamp createTime) {
        this.id = id;
        this.netID = netID;
        this.link = link;
        this.title = title;
        this.details = details;
        this.type = type;
        this.createTime = createTime;
    }

    public Announcement(AnnouncementType type) {
        this.type = type;
    }


    private boolean isOwner(String user) {
        return user.equalsIgnoreCase(netID);
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public AnnouncementType getType() {
        return type;
    }

    public void setType(AnnouncementType type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public enum AnnouncementType {
        JOB(0), EVENT(1), NEWS(2);

        int value;

        AnnouncementType(int value) {
            this.value = value;
        }

        public static AnnouncementType getAnnouncementType(String type) {
            if (type.equalsIgnoreCase("job") || type.equalsIgnoreCase("job posting"))
                return JOB;
            else if (type.equalsIgnoreCase("event"))
                return EVENT;
            else if (type.equalsIgnoreCase("news"))
                return NEWS;
            else
                return null;
        }

        @Override
        public String toString() {
            switch (value) {
                case 0:
                    return "Job";
                case 1:
                    return "Event";
                case 2:
                    return "News";
                default:
                    return "Announcement";
            }
        }

        public int getValue() {
            return this.value;
        }
    }
}
