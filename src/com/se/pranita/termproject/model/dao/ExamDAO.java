package com.se.pranita.termproject.model.dao;

import com.se.pranita.termproject.model.Exam;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.model.user.User;
import com.se.pranita.termproject.utils.Constants;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pranita on 24/4/16.
 */
public class ExamDAO {

    public ExamDAO() {
    }

    public ArrayList<Exam> get(String netID) throws SQLException {
        return get(netID, false);
    }

    public ArrayList<Exam> get(String netID, boolean specific) throws SQLException {
        ArrayList<Exam> exams = new ArrayList<>();
        Connection conn = ConnectionHandler.getConnection();
        String query = "SELECT e.`examID`, e.`netID`, `name`, `date_of_exam`, `additional_details`, b.`netID` AS studentID, u.`firstName`, u.`lastName` FROM " +
                Constants.DATABASENAME + ".`exams` e left join " +
                Constants.DATABASENAME + ".`exam_user` b on e.`examID`=b.`examID` left join " +
                Constants.DATABASENAME + ".`users` u on e.`netID`=u.`netID` ";

        if(specific)
            query += "WHERE e.`netID`='" + netID + "' ";

        query += "ORDER BY e.`date_of_exam` DESC";

        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);

        Map<Integer, Integer> added = new HashMap<>();

        while (rs.next()) {

            int examID = rs.getInt("examID");

            if (!added.keySet().contains(examID)) {
                exams.add(getExam(examID, rs, netID));
                added.put(examID, exams.size() - 1);
            } else if (netID.equalsIgnoreCase(rs.getString("studentID"))) {
                exams.remove((int) added.get(examID));
                exams.add(getExam(examID, rs, netID));
                added.put(examID, exams.size() - 1);
            }
        }

        return exams;
    }

    private Exam getExam(int examID, ResultSet rs, String netID) throws SQLException {
        Exam exam = new Exam();
        exam.setExamID(examID);
        exam.setNetID(rs.getString("netID"));
        exam.setName(rs.getString("name"));
        exam.setDateOfExam(rs.getDate("date_of_exam").toString());
        exam.setAdditionalDetails(rs.getString("additional_details"));
        exam.setOwnerName(rs.getString("firstName") + " " + rs.getString("lastName"));
        exam.setEnrolled(netID.equalsIgnoreCase(rs.getString("studentID")));

        exam.setExpired(Date.valueOf(exam.getDateOfExam()).compareTo(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()))) <= 0);
        return exam;
    }

    public void save(String netID, String name, Date dateOfExam, String additionalDetails) throws SQLException {

        String query = "INSERT INTO " + Constants.DATABASENAME +
                ".`exams` (`netID`, `name`, `date_of_exam`, `additional_details`) " +
                "VALUES(?, ?, ?, ?)";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, netID);
        ps.setString(2, name);
        ps.setDate(3, dateOfExam);
        ps.setString(4, additionalDetails);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void save(int examID, String netID, String name, Date dateOfExam, String additionalDetails) throws SQLException {

        String query = "INSERT INTO " + Constants.DATABASENAME +
                ".`exams` (`examID`, `netID`, `name`, `date_of_exam`, `additional_details`) " +
                "VALUES(?, ?, ?, ?, ?)";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, examID);
        ps.setString(2, netID);
        ps.setString(3, name);
        ps.setDate(4, dateOfExam);
        ps.setString(5, additionalDetails);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void put(String name, Date dateOfExam, String additionalDetails, int examID) throws SQLException {

        String query = "UPDATE " + Constants.DATABASENAME + ".`exams` SET `name`=?, `date_of_exam`=?, `additional_details`=? " +
                "WHERE `examID`=?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, name);
        ps.setDate(2, dateOfExam);
        ps.setString(3, additionalDetails);

        ps.setInt(4, examID);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void delete(int examID) throws SQLException {
        String query = "DELETE FROM " + Constants.DATABASENAME + ".`exams` WHERE examID=?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, examID);
        ps.executeUpdate();

        query = "DELETE FROM " + Constants.DATABASENAME + ".`exam_user` WHERE examID=?";
        PreparedStatement ps2 = conn.prepareStatement(query);
        ps2.setInt(1, examID);
        ps2.executeUpdate();

        conn.commit();
        ps.close();
        ps2.close();
        conn.close();
    }

    public void enroll(int examID, String netID, boolean enroll) throws SQLException {

        String query;
        if (enroll)
            query = "INSERT INTO " + Constants.DATABASENAME + ".`exam_user` " + "VALUES(?, ?)";
        else
            query = "DELETE FROM " + Constants.DATABASENAME + ".`exam_user` WHERE examID=? AND netID=?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, examID);
        ps.setString(2, netID);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }
}
