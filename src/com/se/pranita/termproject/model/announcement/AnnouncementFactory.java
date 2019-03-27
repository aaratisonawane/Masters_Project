package com.se.pranita.termproject.model.announcement;

/**
 * Created by Pranita on 23/4/16.
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
