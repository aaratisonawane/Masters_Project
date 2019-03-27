package com.se.pranita.termproject.tests;

import com.se.pranita.termproject.model.Discussion;
import com.se.pranita.termproject.model.dao.DiscussionDAO;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Pranita on 25/4/16.
 */
public class DiscussionDAOTest {
    @Test
    public void testDiscussionDAO() throws Exception {
        DiscussionDAO discussionDAO = new DiscussionDAO();

        String className = DiscussionDAOTest.class.getSimpleName();

        Discussion testDiscussion = new Discussion();
        testDiscussion.setNetID("SS123456");
        testDiscussion.setTitle(className + "title");
        testDiscussion.setType(Discussion.DiscussionType.TOPIC);
        testDiscussion.setDetails(className + "details");
        testDiscussion.setDiscussion_id(-99);

        discussionDAO.save(testDiscussion.getNetID(), testDiscussion.getTitle(), testDiscussion.getDetails(),
                testDiscussion.getType().getValue(), String.valueOf(testDiscussion.getDiscussion_id()));

        Discussion savedDiscussion = discussionDAO.get(String.valueOf(testDiscussion.getDiscussion_id())).get(0);

        assertNotNull("read failed", savedDiscussion);

        assertEquals("save failed", savedDiscussion.getNetID(), testDiscussion.getNetID());
        assertEquals("save failed", savedDiscussion.getTitle(), testDiscussion.getTitle());
        assertEquals("save failed", savedDiscussion.getType(), testDiscussion.getType());
        assertEquals("save failed", savedDiscussion.getDiscussion_id(), testDiscussion.getDiscussion_id());


        testDiscussion.setTitle(className + "titleMod");
        testDiscussion.setDetails(className + "detailsMod");

        discussionDAO.put(testDiscussion.getTitle(), testDiscussion.getDetails(), savedDiscussion.getId());

        savedDiscussion = discussionDAO.get(String.valueOf(savedDiscussion.getId())).get(0);

        assertNotNull("read failed", savedDiscussion);

        assertEquals("update failed", savedDiscussion.getTitle(), testDiscussion.getTitle());
        assertEquals("update failed", savedDiscussion.getDetails(), testDiscussion.getDetails());

        discussionDAO.delete(savedDiscussion.getId());

        int id = savedDiscussion.getId();
        ArrayList<Discussion> list = discussionDAO.get(String.valueOf(id));

        assertTrue("delete failed", list.size() == 0);
    }

}