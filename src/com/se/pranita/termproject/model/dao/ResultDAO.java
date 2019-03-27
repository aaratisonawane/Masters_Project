package com.se.pranita.termproject.model.dao;

import com.se.pranita.termproject.model.Result;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.utils.Constants;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Pranita on 24/4/16.
 */
public class ResultDAO {

    public ResultDAO() {}

    public ArrayList<Result> get(String netID) throws SQLException {
        ArrayList<Result> results = new ArrayList<>();
        Connection conn = ConnectionHandler.getConnection();
        String query =
        "SELECT `resultID`, r.`examID`, r.`netID`, e.`name`, `result_details`, `create_time`, u.`firstName`, u.`lastName` FROM " +
                Constants.DATABASENAME + ".`results` r left join " +
                Constants.DATABASENAME + ".`users` u on r.`netID`=u.`netID` " +
        "LEFT JOIN " + Constants.DATABASENAME + ".`exams` e on e.`examID`=r.`examID` " +
        "LEFT JOIN " + Constants.DATABASENAME + ".`exam_user` eu on eu.`examID`=e.`examID` " +
        "WHERE e.`netID`='" + netID  + "' or eu.`netID`='" + netID + "' " +
        "ORDER BY r.`create_time` DESC";

        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);

        while (rs.next()) {
            Result result = new Result();
            result.setExamName(rs.getString("name"));
            result.setExamID(rs.getInt("examID"));
            result.setNetID(rs.getString("netID"));
            result.setResultID(rs.getInt("resultID"));
            result.setResultDetails(rs.getString("result_details"));
            result.setCreateTime(rs.getTimestamp("create_time"));
            result.setOwnerName(rs.getString("firstName") + " " + rs.getString("lastName"));
            result.setOwner(netID.equalsIgnoreCase(result.getNetID()));
            results.add(result);
        }

        return results;
    }

    public void save(String netID, String result_details, int examID) throws SQLException {
        String query = "INSERT INTO " + Constants.DATABASENAME +
                ".`results` (`netID`, `result_details`, `examID`) " +
                "VALUES(?, ?, ?)";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, netID);
        ps.setString(2, result_details);
        ps.setInt(3, examID);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void put(int examID, String result_details, String resultID) throws SQLException {
        String query = "UPDATE " + Constants.DATABASENAME + ".`results` SET `examID`=?, `result_details`=? " +
                "WHERE `resultID`=?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, examID);
        ps.setString(2, result_details);
        ps.setString(3, resultID);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void delete(int resultID) throws SQLException {
        String query = "DELETE FROM " + Constants.DATABASENAME + ".`results` WHERE resultID=?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, resultID);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

}
