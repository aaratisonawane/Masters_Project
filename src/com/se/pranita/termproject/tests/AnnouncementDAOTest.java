package com.se.pranita.termproject.tests;

import com.se.pranita.termproject.model.announcement.Announcement;
import com.se.pranita.termproject.model.announcement.AnnouncementFactory;
import com.se.pranita.termproject.model.announcement.Event;
import com.se.pranita.termproject.model.dao.AnnouncementDAO;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Pranita on 25/4/16.
 */
public class AnnouncementDAOTest {

    @Test
    public void testAnnouncementDAO() throws Exception {
        AnnouncementDAO announcementDAO = new AnnouncementDAO();

        String className = AnnouncementDAOTest.class.getSimpleName();

        Announcement testAnnouncement = new AnnouncementFactory().getAnnouncement(Announcement.AnnouncementType.JOB.getValue());
        /**
         * Should contain the netID in the users table
         **/
        testAnnouncement.setNetID("SS123456");
        testAnnouncement.setLink(className + "Link");
        testAnnouncement.setTitle(className + "Title");
        testAnnouncement.setDetails(className + "Details");

        announcementDAO.save(testAnnouncement.getNetID(), testAnnouncement.getTitle(), testAnnouncement.getDetails(),
                testAnnouncement.getLink(), testAnnouncement.getType(), null, null);

        Announcement savedAnnouncement = null;
        ArrayList<Announcement> list = announcementDAO.get();
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getTitle().equalsIgnoreCase(testAnnouncement.getTitle())){
                savedAnnouncement = list.get(i);
            }

        assertNotNull("read failed", savedAnnouncement);

        assertEquals("save failed", savedAnnouncement.getNetID(), testAnnouncement.getNetID());
        assertEquals("save failed", savedAnnouncement.getTitle(), testAnnouncement.getTitle());
        assertEquals("save failed", savedAnnouncement.getDetails(), testAnnouncement.getDetails());
        assertEquals("save failed", savedAnnouncement.getLink(), testAnnouncement.getLink());
        assertEquals("save failed", savedAnnouncement.getType(), testAnnouncement.getType());


        testAnnouncement.setLink(className + "LinkMod");
        testAnnouncement.setTitle(className + "TitleMod");
        testAnnouncement.setDetails(className + "DetailsMod");
        testAnnouncement.setType(Announcement.AnnouncementType.JOB);

        announcementDAO.put(testAnnouncement.getTitle(), testAnnouncement.getDetails(),
                testAnnouncement.getLink(), testAnnouncement.getType(), null, null, savedAnnouncement.getId());

        savedAnnouncement = null;
        list = announcementDAO.get();
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getTitle().equalsIgnoreCase(testAnnouncement.getTitle())){
                savedAnnouncement = list.get(i);
            }

        assertNotNull("read failed", savedAnnouncement);

        assertEquals("update failed", savedAnnouncement.getNetID(), testAnnouncement.getNetID());
        assertEquals("update failed", savedAnnouncement.getTitle(), testAnnouncement.getTitle());
        assertEquals("update failed", savedAnnouncement.getDetails(), testAnnouncement.getDetails());
        assertEquals("update failed", savedAnnouncement.getLink(), testAnnouncement.getLink());
        assertEquals("update failed", savedAnnouncement.getType(), testAnnouncement.getType());

        announcementDAO.delete(savedAnnouncement.getId());

        savedAnnouncement = null;
        list = announcementDAO.get();
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getTitle().equalsIgnoreCase(testAnnouncement.getTitle())){
                savedAnnouncement = list.get(i);
            }

        assertNull("delete failed", savedAnnouncement);
    }

}