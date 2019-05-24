package com.se.aarati.termproject.model.announcement;

/**
 * Created by aarati on 23/5/19.
 */
public class AnnouncementFactory {

    public Announcement getAnnouncement(int announcementType) {

        if (announcementType
                == Announcement.AnnouncementType.NEWS.getValue()) {
            return new News();

        } else if (announcementType == Announcement.AnnouncementType.EVENT.getValue()) {
            return new Event();

        } else if (announcementType == Announcement.AnnouncementType.JOB.getValue()) {
            return new Job();
        }
        return null;
    }

}
