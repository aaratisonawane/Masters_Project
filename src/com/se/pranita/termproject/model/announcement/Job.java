package com.se.pranita.termproject.model.announcement;

import java.sql.Timestamp;

/**
 * Created by Pranita on 23/4/16.
 */
public class Job extends Announcement{

    public Job(Integer id, String netID, String link, String title, String details, Timestamp createTime) {
        super(id, netID, link, title, details, AnnouncementType.JOB, createTime);
    }

    public Job() {
        super(AnnouncementType.JOB);
    }
}
