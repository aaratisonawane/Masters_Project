package com.se.pranita.termproject.tests;

import com.se.pranita.termproject.model.Result;
import com.se.pranita.termproject.model.dao.ResultDAO;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Pranita on 25/4/16.
 */
public class ResultDAOTest {
//    @Test
    public void testResultDAO() throws Exception {
        ResultDAO resultDAO = new ResultDAO();

        String className = ResultDAOTest.class.getSimpleName();

        Result testResult = new Result();
        testResult.setNetID("SS123456");
        testResult.setExamName(className + "examName");
        //exam with id 1 should be present
        testResult.setResultDetails(className + "ResultDetails");

        resultDAO.save(testResult.getNetID(), testResult.getResultDetails(), 1);

        Result savedResult = null;
        ArrayList<Result> list = resultDAO.get(testResult.getNetID());
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getExamName().equalsIgnoreCase(testResult.getExamName())){
                savedResult = list.get(i);
            }

        assertNotNull("read failed", savedResult);

        assertEquals("save failed", savedResult.getNetID(), testResult.getNetID());
        assertEquals("save failed", savedResult.getResultDetails(), testResult.getResultDetails());


        testResult.setExamName(className + "examNameMod");
        testResult.setResultDetails(className + "ResultDetailsMod");

        resultDAO.put(1, testResult.getResultDetails(), String.valueOf(savedResult.getResultID()));

        savedResult = null;
        list = resultDAO.get(testResult.getNetID());
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getExamName().equalsIgnoreCase(testResult.getExamName())){
                savedResult = list.get(i);
            }

        assertNotNull("read failed", savedResult);

        assertEquals("update failed", savedResult.getNetID(), testResult.getNetID());
        assertEquals("update failed", savedResult.getResultDetails(), testResult.getResultDetails());

        resultDAO.delete(savedResult.getResultID());

        savedResult = null;
        list = resultDAO.get(testResult.getNetID());
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getExamName().equalsIgnoreCase(testResult.getExamName())){
                savedResult = list.get(i);
            }

        assertNull("delete failed", savedResult);
    }

}