package com.se.aarati.termproject.tests;

import com.se.aarati.termproject.model.dao.UserDAO;
import com.se.aarati.termproject.model.user.Student;
import com.se.aarati.termproject.model.user.User;
import com.se.aarati.termproject.model.user.UserFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aarati on 25/5/19.
 */
public class UserDAOTest {
    @Test
    public void testUserDAO() throws Exception {
        UserDAO userDAO = new UserDAO();

        String className = UserDAOTest.class.getSimpleName();

        User testUser = new UserFactory().getUser(User.UserType.STUDENT.getValue());

        testUser.setNetID("testUser");
        testUser.setFirstName(className + "FirstName");
        testUser.setLastName(className + "lastName");
        testUser.setPassword("password");
        ((Student)testUser).setDepartment("MS");
        ((Student) testUser).setStartTerm("Fall");
        ((Student) testUser).setStartYear(2010);

        userDAO.save(testUser.getNetID(), testUser.getPassword(), testUser.getFirstName(),
                testUser.getLastName(), testUser.getType(), ((Student) testUser).getDepartment(),
                ((Student) testUser).getProgram(), ((Student) testUser).getStartTerm(),
                String.valueOf(((Student) testUser).getStartYear()));

        User savedUser = userDAO.getStudentById(testUser.getNetID());

        assertNotNull("read failed", savedUser);

        assertEquals("save failed", savedUser.getFirstName(), testUser.getFirstName());
        assertEquals("save failed", savedUser.getLastName(), testUser.getLastName());
        assertEquals("save failed", savedUser.getType(), testUser.getType());

        userDAO.delete(savedUser.getNetID());

        savedUser = userDAO.getStudentById(savedUser.getNetID());

        assertNull("delete failed", savedUser);
    }
}