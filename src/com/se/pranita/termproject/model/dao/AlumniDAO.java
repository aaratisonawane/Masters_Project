package com.se.pranita.termproject.model.dao;

import com.se.pranita.termproject.model.Alumni;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.utils.Constants;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Pranita on 24/4/16.
 */
public class AlumniDAO {

    public AlumniDAO() {}

    public ArrayList<Alumni> get() throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`alumni`";

        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);


        ArrayList<Alumni> alumnis = new ArrayList<>();
        while (rs.next()) {
            Alumni alumni = new Alumni();
            alumni.setName(rs.getString("name"));
            alumni.setDescription(rs.getString("description"));
            alumni.setHomepage(rs.getString("homepage"));
            alumni.setImage(rs.getString("image"));
            alumnis.add(alumni);
        }
        return alumnis;
    }

    public void save(String name, String homepage, String description, String image) throws SQLException {
        String query = "INSERT INTO " + Constants.DATABASENAME + ".`alumni` VALUES(?, ?, ?, ?)";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, name);
        ps.setString(2, homepage);
        ps.setString(3, description);
        ps.setString(4, image);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void put(String name, String homepage, String description, String image, String old_name) throws SQLException {
        String query = "UPDATE " + Constants.DATABASENAME + ".`alumni` SET name=?, homepage=?, description=?, image=? WHERE name=?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, name);
        ps.setString(2, homepage);
        ps.setString(3, description);
        ps.setString(4, image);
        ps.setString(5, old_name);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void delete(String name) throws SQLException {
        String query = "DELETE FROM " + Constants.DATABASENAME + ".`alumni` WHERE name=?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, name);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }
}
