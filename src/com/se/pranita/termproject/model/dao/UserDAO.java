package com.se.pranita.termproject.model.dao;

import com.se.pranita.termproject.model.Course;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.model.user.Student;
import com.se.pranita.termproject.model.user.User;
import com.se.pranita.termproject.model.user.UserFactory;
import com.se.pranita.termproject.utils.Constants;
import com.se.pranita.termproject.utils.TermUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Pranita on 24/4/16.
 */
public class UserDAO {

    public ArrayList<User> getByProgram(String prog) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`users` WHERE `program`='" + prog + "'";

        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);

        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new Student();
            user.setNetID(rs.getString("netID"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setType(User.UserType.STUDENT);

            ((Student) user).setStartTerm(rs.getString("startTerm"));
            ((Student) user).setStartYear(rs.getInt("startYear"));
            ((Student) user).setProgram(rs.getString("program"));
            ((Student) user).setDepartment(rs.getString("department"));
            System.out.println(user.toJSON());
            users.add(user);
        }

        return users;
    }

    public User getStudentById(String netID) throws SQLException {

        String query = "SELECT " +
                Constants.DATABASENAME + ".`users`.`firstName`, " +
                Constants.DATABASENAME + ".`users`.`lastName`, " +
                Constants.DATABASENAME + ".`users`.`department`, " +
                Constants.DATABASENAME + ".`users`.`startYear`, " +
                Constants.DATABASENAME + ".`users`.`startTerm`, " +
                Constants.DATABASENAME + ".`users`.`program`, " +
                Constants.DATABASENAME + ".`users`.`phoneNumber`, " +
                Constants.DATABASENAME + ".`users`.`advisor`, " +
                Constants.DATABASENAME + ".`courses`.`number`, " +
                Constants.DATABASENAME + ".`courses`.`term`, " +
                Constants.DATABASENAME + ".`courses`.`year`, " +
                Constants.DATABASENAME + ".`courses`.`name`" +
                " FROM " + Constants.DATABASENAME + ".`courses` JOIN " + Constants.DATABASENAME + ".`course_user` ON " +
                Constants.DATABASENAME + ".`courses`.`number` = " +
                Constants.DATABASENAME + ".`course_user`.`number` AND " +
                Constants.DATABASENAME + ".`courses`.`term` = " +
                Constants.DATABASENAME + ".`course_user`.`term` AND " +
                Constants.DATABASENAME + ".`courses`.`year` = " +
                Constants.DATABASENAME + ".`course_user`.`year` " +
                "RIGHT JOIN " + Constants.DATABASENAME + ".`users` ON " +
                Constants.DATABASENAME + ".`users`.`netID` = " +
                Constants.DATABASENAME + ".`course_user`.`netID`" +
                " WHERE " +
                Constants.DATABASENAME + ".`users`.`netID`='" + netID + "'";
        Connection con = ConnectionHandler.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);


        if (rs.next()) {
            User user = new UserFactory().getUser(User.UserType.STUDENT.getValue());
            user.setNetID(netID);
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            ((Student) user).setDepartment(rs.getString("department"));
            ((Student) user).setStartYear(rs.getInt("startYear"));
            ((Student) user).setStartTerm(rs.getString("startTerm"));
            ((Student) user).setProgram(rs.getString("program"));
            ((Student) user).setPhoneNumber(rs.getString("phoneNumber"));
            ((Student) user).setAdvisorName(rs.getString("advisor"));

            ArrayList<Course> courses = new ArrayList<>();

            if(rs.getString("number") != null) {
                Course course_1 = new Course();
                course_1.setNumber(rs.getString("number"));
                course_1.setTerm(rs.getString("term"));
                course_1.setYear(rs.getInt("year"));
                course_1.setName(rs.getString("name"));

                course_1.setStatus(TermUtil.compareCurrentTerm(TermUtil.Term.getTerm(course_1.getTerm()),
                        course_1.getYear()) <= 0 ? Course.CourseStatus.ENROLLED : Course.CourseStatus.COMPLETED);
                courses.add(course_1);

                while (rs.next()) {
                    Course course = new Course();
                    course.setNumber(rs.getString("number"));
                    course.setTerm(rs.getString("term"));
                    course.setYear(rs.getInt("year"));
                    course.setName(rs.getString("name"));

                    course.setStatus(TermUtil.compareCurrentTerm(TermUtil.Term.getTerm(course.getTerm()),
                            course.getYear()) <= 0 ? Course.CourseStatus.ENROLLED : Course.CourseStatus.COMPLETED);
                    courses.add(course);

                }
            }

            user.setCourses(courses);
            return user;
        }
        return null;
    }

    public void save(String netID, String password, String firstName, String lastName, User.UserType type,
                     String department, String program, String sem, String year) throws SQLException {

        Connection conn = ConnectionHandler.getConnection();
        String query = "INSERT INTO " + Constants.DATABASENAME + ".`users` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, netID);
        ps.setString(2, password);
        ps.setString(3, firstName);
        ps.setString(4, lastName);
        ps.setInt(5, type.getValue());
        if (type == User.UserType.STUDENT) {
            ps.setString(6, sem);
            ps.setInt(7, Integer.parseInt(year));
            ps.setString(8, program);
            ps.setString(9, department);
            ps.setString(10, null);
            ps.setString(11, null);
        } else {
            ps.setString(6, null);
            ps.setInt(7, -1);
            ps.setString(8, null);
            ps.setString(9, null);
            ps.setString(10, null);
            ps.setString(11, null);
        }

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void delete(String netID) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "DELETE FROM " + Constants.DATABASENAME + ".`users` WHERE `netID`=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, netID);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void put(String netID, String firstName, String lastName, String newPass,
                    String phoneNumber, String advisor) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();

        String query = "UPDATE " + Constants.DATABASENAME + ".`users`" +
                "SET `firstName`=?, `lastName`=?, `password`=?, `phoneNumber`=?, `advisor`=?" +
                "WHERE netID=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, newPass);
        ps.setString(4, phoneNumber);
        ps.setString(5, advisor);
        ps.setString(6, netID);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

}
