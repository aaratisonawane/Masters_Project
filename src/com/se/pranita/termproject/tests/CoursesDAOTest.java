package com.se.pranita.termproject.tests;

import com.se.pranita.termproject.model.Course;
import com.se.pranita.termproject.model.dao.CoursesDAO;
import com.se.pranita.termproject.model.user.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Pranita on 25/4/16.
 */
public class CoursesDAOTest {
    @Test
    public void testCoursesDAO() throws Exception {
        CoursesDAO courseDAO = new CoursesDAO();

        String className = CoursesDAOTest.class.getSimpleName();

        Course testCourse = new Course();
        testCourse.setNumber("testNum");
        testCourse.setName(className + "name");
        testCourse.setDepartment(className + "Department");
        testCourse.setInstructor("SS123456");
        testCourse.setCourse_syllabus(className + "syllabus");
        testCourse.setIns_office_hour(className + "ins_office_hour");
        testCourse.setIns_office(className + "ins_office");
        testCourse.setTa_name(className + "ta_name");
        testCourse.setTa_office_hour(className + "ta_office_hour");
        testCourse.setTa_office(className + "ta_office");
        testCourse.setTa_email(className + "ta_email");
        testCourse.setTerm("testterm");
        testCourse.setYear(1990);

        courseDAO.save(testCourse.getNumber(), testCourse.getName(), testCourse.getDepartment(),
                testCourse.getTerm(), testCourse.getYear(), testCourse.getCourse_syllabus(), testCourse.getIns_office(),
                testCourse.getInstructor(), testCourse.getIns_office_hour(), testCourse.getTa_name(),
                testCourse.getTa_email(), testCourse.getTa_office(), testCourse.getTa_office_hour());

        Course savedCourse = null;
        ArrayList<Course> list = courseDAO.get(User.UserType.FACULTY, null);
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getName().equalsIgnoreCase(testCourse.getName())){
                savedCourse = list.get(i);
            }

        assertNotNull("read failed", savedCourse);

        assertEquals("save failed", savedCourse.getNumber(), testCourse.getNumber());
        assertEquals("save failed", savedCourse.getName(), testCourse.getName());
        assertEquals("save failed", savedCourse.getDepartment(), testCourse.getDepartment());
        assertEquals("save failed", savedCourse.getCourse_syllabus(), testCourse.getCourse_syllabus());
        assertEquals("save failed", savedCourse.getIns_office_hour(), testCourse.getIns_office_hour());
        assertEquals("save failed", savedCourse.getIns_office(), testCourse.getIns_office());
        assertEquals("save failed", savedCourse.getTa_name(), testCourse.getTa_name());
        assertEquals("save failed", savedCourse.getTa_office_hour(), testCourse.getTa_office_hour());
        assertEquals("save failed", savedCourse.getTa_office(), testCourse.getTa_office());
        assertEquals("save failed", savedCourse.getTa_email(), testCourse.getTa_email());
        assertEquals("save failed", savedCourse.getTerm(), testCourse.getTerm());
        assertEquals("save failed", savedCourse.getYear(), testCourse.getYear());


        testCourse.setName(className + "namemod");
        testCourse.setDepartment(className + "Departmentmod");
        testCourse.setCourse_syllabus(className + "syllabusmod");
        testCourse.setIns_office_hour(className + "ins_office_hourmod");
        testCourse.setIns_office(className + "ins_officemod");
        testCourse.setTa_name(className + "ta_namemod");
        testCourse.setTa_office_hour(className + "ta_office_hourmod");
        testCourse.setTa_office(className + "ta_officemod");
        testCourse.setTa_email(className + "ta_emailmod");

        courseDAO.put(testCourse.getNumber(), testCourse.getTerm(), testCourse.getYear(), testCourse.getDepartment(),
                testCourse.getCourse_syllabus(), testCourse.getIns_office_hour(),
                testCourse.getIns_office(), testCourse.getTa_name(), testCourse.getTa_office_hour(),
                testCourse.getTa_office(), testCourse.getTa_email());

        savedCourse = null;
        list = courseDAO.get(User.UserType.FACULTY, null);

        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getNumber().equalsIgnoreCase(testCourse.getNumber())){
                savedCourse = list.get(i);
            }

        assertNotNull("read failed", savedCourse);

        assertEquals("save failed", savedCourse.getDepartment(), testCourse.getDepartment());
        assertEquals("save failed", savedCourse.getCourse_syllabus(), testCourse.getCourse_syllabus());
        assertEquals("save failed", savedCourse.getIns_office_hour(), testCourse.getIns_office_hour());
        assertEquals("save failed", savedCourse.getIns_office(), testCourse.getIns_office());
        assertEquals("save failed", savedCourse.getTa_name(), testCourse.getTa_name());
        assertEquals("save failed", savedCourse.getTa_office_hour(), testCourse.getTa_office_hour());
        assertEquals("save failed", savedCourse.getTa_office(), testCourse.getTa_office());
        assertEquals("save failed", savedCourse.getTa_email(), testCourse.getTa_email());

        courseDAO.delete(testCourse.getNumber(), testCourse.getTerm(), testCourse.getYear());

        savedCourse = null;
        list = courseDAO.get(User.UserType.FACULTY, null);
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getNumber().equalsIgnoreCase(testCourse.getNumber())){
                savedCourse = list.get(i);
            }

        assertNull("delete failed", savedCourse);
    }

}