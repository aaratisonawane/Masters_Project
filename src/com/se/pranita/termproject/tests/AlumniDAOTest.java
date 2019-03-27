package com.se.pranita.termproject.tests;

import com.se.pranita.termproject.model.Alumni;
import com.se.pranita.termproject.model.dao.AlumniDAO;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Pranita on 24/4/16.
 */
public class AlumniDAOTest {
    @org.junit.Test
    public void testAlumniDAO() throws Exception {

        AlumniDAO alumniDAO = new AlumniDAO();

        String className = AlumniDAOTest.class.getSimpleName();

        Alumni testAlumni = new Alumni();
        testAlumni.setName(className + "Name");
        testAlumni.setHomepage(className + "Homepage");
        testAlumni.setDescription(className + "Description");
        testAlumni.setImage(className + "Image");

        alumniDAO.save(testAlumni.getName(), testAlumni.getHomepage(), testAlumni.getDescription(), testAlumni.getImage());

        Alumni savedAlumni = null;
        ArrayList<Alumni> list = alumniDAO.get();
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getName().equalsIgnoreCase(testAlumni.getName())){
               savedAlumni = list.get(i);
            }

        assertNotNull("read failed", savedAlumni);

        assertEquals("save failed", savedAlumni.getHomepage(), testAlumni.getHomepage());
        assertEquals("save failed", savedAlumni.getDescription(), testAlumni.getDescription());
        assertEquals("save failed", savedAlumni.getImage(), testAlumni.getImage());


        testAlumni.setName(className + "NameMod");
        testAlumni.setHomepage(className + "HomepageMod");
        testAlumni.setDescription(className + "DescriptionMod");
        testAlumni.setImage(className + "ImageMod");

        alumniDAO.put(testAlumni.getName(), testAlumni.getHomepage(), testAlumni.getDescription(),
                testAlumni.getImage(), savedAlumni.getName());

        savedAlumni = null;
        list = alumniDAO.get();
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getName().equalsIgnoreCase(testAlumni.getName())){
                savedAlumni = list.get(i);
            }

        assertNotNull("read failed", savedAlumni);

        assertEquals("update failed", savedAlumni.getHomepage(), testAlumni.getHomepage());
        assertEquals("update failed", savedAlumni.getDescription(), testAlumni.getDescription());
        assertEquals("update failed", savedAlumni.getImage(), testAlumni.getImage());

        alumniDAO.delete(testAlumni.getName());

        savedAlumni = null;
        list = alumniDAO.get();
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getName().equalsIgnoreCase(testAlumni.getName())){
                savedAlumni = list.get(i);
            }

        assertNull("delete failed", savedAlumni);
    }

}