package com.se.aarati.termproject.model.announcement;

import java.sql.Timestamp;

/**
 * Created by aarati on 23/5/19.
 */
public class Job extends Announcement{

    public Job(Integer id, String netID, String link, String title, String details, Timestamp createTime) {
        super(id, netID, link, title, details, AnnouncementType.JOB, createTime);
    }

    public Job() {
        super(AnnouncementType.JOB);
    }
}
