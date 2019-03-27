package com.se.pranita.termproject.model.dao;

import com.se.pranita.termproject.model.Course;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.model.user.User;
import com.se.pranita.termproject.utils.Constants;
import com.se.pranita.termproject.utils.TermUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Pranita on 24/4/16.
 */
public class CoursesDAO {

    public CoursesDAO() {
    }

    public ArrayList<Course> getById(String netID) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();
        Connection conn = ConnectionHandler.getConnection();
        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`courses` JOIN " + Constants.DATABASENAME + ".`course_user` ON " +
                Constants.DATABASENAME + ".`courses`.`number` = " +
                Constants.DATABASENAME + ".`course_user`.`number` AND " +
                Constants.DATABASENAME + ".`courses`.`term` = " +
                Constants.DATABASENAME + ".`course_user`.`term` AND " +
                Constants.DATABASENAME + ".`courses`.`year` = " +
                Constants.DATABASENAME + ".`course_user`.`year` WHERE " +
                Constants.DATABASENAME + ".`course_user`.`netID`='" + netID + "'";

        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);

        while (rs.next()) {
            Course course = new Course();
            course.setName(rs.getString("name"));
            course.setNumber(rs.getString("number"));
            course.setDepartment(rs.getString("department"));
            course.setCourse_syllabus(rs.getString("course_syllabus"));
            course.setIns_office(rs.getString("ins_office_hour"));
            course.setIns_office(rs.getString("ins_office_hour"));
            course.setIns_office_hour(rs.getString("ins_office"));
            course.setTa_name(rs.getString("ta_name"));
            course.setTa_office(rs.getString("ta_office_hour"));
            course.setTa_office_hour(rs.getString("ta_office"));
            course.setTa_email(rs.getString("ta_email"));
            course.setTerm(rs.getString("term"));
            course.setYear(rs.getInt("year"));
            course.setInstructor(netID);

            courses.add(course);
        }

        return courses;
    }

    public ArrayList<Course> get(User.UserType type, String netID) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();

        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`courses` JOIN " +
                Constants.DATABASENAME + ".`course_user` ON " +
                Constants.DATABASENAME + ".`courses`.`number` = " +
                Constants.DATABASENAME + ".`course_user`.`number` AND " +
                Constants.DATABASENAME + ".`courses`.`term` = " +
                Constants.DATABASENAME + ".`course_user`.`term` AND " +
                Constants.DATABASENAME + ".`courses`.`year` = " +
                Constants.DATABASENAME + ".`course_user`.`year` " +
                " JOIN " + Constants.DATABASENAME + ".`users` ON " +
                Constants.DATABASENAME + ".`users`.`netID` = " +
                Constants.DATABASENAME + ".`course_user`.`netID`" +
                " WHERE " +
                Constants.DATABASENAME + ".`users`.`type`='1'";

        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);


        ArrayList<Course> courses = new ArrayList<>();
        while (rs.next()) {
            Course course = new Course();
            course.setName(rs.getString("name"));
            course.setNumber(rs.getString("number"));
            course.setDepartment(rs.getString("department"));
            course.setCourse_syllabus(rs.getString("course_syllabus"));
            course.setIns_office(rs.getString("ins_office"));
            course.setIns_office_hour(rs.getString("ins_office_hour"));
            course.setTa_name(rs.getString("ta_name"));
            course.setTa_office(rs.getString("ta_office"));
            course.setTa_office_hour(rs.getString("ta_office_hour"));
            course.setTa_email(rs.getString("ta_email"));
            course.setTerm(rs.getString("term"));
            course.setYear(rs.getInt("year"));
            course.setInstructor(rs.getString("netID"));
            course.setInstructor_name(rs.getString("firstName") + " " + rs.getString("lastName"));
            if (type == User.UserType.STUDENT)
                course.setStatus(getUserStatus(conn, netID, course.getNumber(), course.getTerm(), course.getYear()));

            courses.add(course);
        }

        return courses;
    }

    private Course.CourseStatus getUserStatus(Connection conn, String netID, String number, String term, int year) throws SQLException {
        String query = "SELECT COUNT(*) AS num FROM " + Constants.DATABASENAME + ".`course_user` WHERE `netID`='" + netID + "' AND `number`='"
                + number + "' AND `term`='" + term + "' AND `year`='" + year + "'";
        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);
        boolean enrolled = false;
        int termStatus = TermUtil.compareCurrentTerm(TermUtil.Term.getTerm(term), year);
        while (rs.next()) {
            if (rs.getInt("num") > 0) {
                enrolled = true;
            }
        }

        if (enrolled)
            return termStatus <= 0 ? Course.CourseStatus.ENROLLED : Course.CourseStatus.COMPLETED;
        else
            return (termStatus <= 0) ? Course.CourseStatus.UNENROLLED : Course.CourseStatus.GONE;
    }

    public void save(String course_num, String course_name, String department, String sem, int year,
                     String course_syllabus, String ins_office, String instructor, String ins_office_hour,
                     String ta_name, String ta_email, String ta_office, String ta_office_hour) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "INSERT INTO " + Constants.DATABASENAME + ".`courses` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, course_num);
        ps.setString(2, course_name);
        ps.setString(3, department);
        ps.setString(4, course_syllabus);
        ps.setString(5, ins_office_hour);
        ps.setString(6, ins_office);
        ps.setString(7, ta_name);
        ps.setString(8, ta_office_hour);
        ps.setString(9, ta_office);
        ps.setString(10, ta_email);
        ps.setString(11, sem);
        ps.setInt(12, year);

        ps.executeUpdate();

        query = "INSERT INTO " + Constants.DATABASENAME + ".`course_user` VALUES (?, ?, ?, ?)";
        PreparedStatement ps2 = conn.prepareStatement(query);
        ps2.setString(1, instructor);
        ps2.setString(2, course_num);
        ps2.setString(3, sem);
        ps2.setInt(4, year);

        ps2.executeUpdate();

        conn.commit();
        ps.close();
        ps2.close();
        conn.close();

    }

    public void put(String number, String term, int year, String department, String course_syllabus,
                    String ins_office_hour, String ins_office, String ta_name, String ta_office_hour,
                    String ta_office, String ta_email) throws IOException, SQLException {

        Connection conn = ConnectionHandler.getConnection();

        String query = "UPDATE " + Constants.DATABASENAME +
                ".`courses` SET department=?, course_syllabus=?, ins_office_hour=?, ins_office=?, ta_name=?, " +
                "ta_office_hour=?, ta_office=?, ta_email=? " +
                "WHERE number=? AND term=? AND year=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, department);
        ps.setString(2, course_syllabus);
        ps.setString(3, ins_office_hour);
        ps.setString(4, ins_office);
        ps.setString(5, ta_name);
        ps.setString(6, ta_office_hour);
        ps.setString(7, ta_office);
        ps.setString(8, ta_email);

        ps.setString(9, number);
        ps.setString(10, term);
        ps.setInt(11, year);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();

    }

    public void delete(String number, String term, int year) throws IOException, SQLException {

        Connection conn = ConnectionHandler.getConnection();

        String query2 = "DELETE FROM " + Constants.DATABASENAME +
                ".`course_user` " +
                "WHERE `number`=?";
        PreparedStatement ps2 = conn.prepareStatement(query2);
        ps2.setString(1, number);

        ps2.executeUpdate();

        String query = "DELETE FROM " + Constants.DATABASENAME +
                ".`courses` " +
                "WHERE `number`=? AND `term`=? AND `year`=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, number);
        ps.setString(2, term);
        ps.setInt(3, year);

        ps.executeUpdate();

        conn.commit();

        ps2.close();
        ps.close();

        conn.close();

    }

    public void enroll(boolean isEnroll, String netId, String num, String term, int year) throws IOException, SQLException {

        Connection conn = ConnectionHandler.getConnection();

        String query;
        if (isEnroll)
            query = "INSERT INTO " + Constants.DATABASENAME + ".`course_user` VALUES (?, ?, ?, ?)";
        else
            query = "DELETE FROM " + Constants.DATABASENAME + ".`course_user` WHERE `netID`=? AND `number`=? AND `term`=? AND `year`=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, netId);
        ps.setString(2, num);
        ps.setString(3, term);
        ps.setInt(4, year);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();

    }
}
