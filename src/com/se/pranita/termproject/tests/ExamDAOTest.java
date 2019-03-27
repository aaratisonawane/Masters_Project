package com.se.pranita.termproject.tests;

import com.se.pranita.termproject.model.Exam;
import com.se.pranita.termproject.model.dao.ExamDAO;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Pranita on 25/4/16.
 */
public class ExamDAOTest {
    @Test
    public void testExamDAO() throws Exception {
        ExamDAO examDAO = new ExamDAO();

        String className = ExamDAOTest.class.getSimpleName();

        Exam testExam = new Exam();

        testExam.setNetID("SS123456");
        testExam.setName(className + "Name");
        testExam.setDateOfExam("1970-01-01");
        testExam.setAdditionalDetails(className + "details");

        examDAO.save(testExam.getNetID(), testExam.getName(), Date.valueOf(testExam.getDateOfExam()), testExam.getAdditionalDetails());

        Exam savedExam = null;
        ArrayList<Exam> list = examDAO.get(testExam.getNetID());
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getName().equalsIgnoreCase(testExam.getName())){
                savedExam = list.get(i);
            }

        assertNotNull("read failed", savedExam);

        assertEquals("save failed", savedExam.getNetID(), testExam.getNetID());
        assertEquals("save failed", savedExam.getName(), testExam.getName());
        assertEquals("save failed", savedExam.getDateOfExam(), testExam.getDateOfExam());
        assertEquals("save failed", savedExam.getAdditionalDetails(), testExam.getAdditionalDetails());


        testExam.setName(className + "NameMod");
        testExam.setDateOfExam("1990-02-02");
        testExam.setAdditionalDetails(className + "detailsMod");

        examDAO.put(testExam.getName(), java.sql.Date.valueOf(testExam.getDateOfExam()), testExam.getAdditionalDetails(),
                savedExam.getExamID());

        savedExam = null;
        list = examDAO.get(testExam.getNetID());
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getName().equalsIgnoreCase(testExam.getName())){
                savedExam = list.get(i);
            }

        assertNotNull("read failed", savedExam);

        assertEquals("update failed", savedExam.getName(), testExam.getName());
        assertEquals("update failed", savedExam.getDateOfExam(), testExam.getDateOfExam());
        assertEquals("update failed", savedExam.getAdditionalDetails(), testExam.getAdditionalDetails());

        examDAO.delete(savedExam.getExamID());

        savedExam = null;
        list = examDAO.get(testExam.getNetID());
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getName().equalsIgnoreCase(testExam.getName())){
                savedExam = list.get(i);
            }

        assertNull("delete failed", savedExam);
    }

}