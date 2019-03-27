package com.se.pranita.termproject.model.dao;

import com.se.pranita.termproject.model.Resource;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.utils.Constants;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Pranita on 24/4/16.
 */
public class ResourcesDAO {

    public ResourcesDAO() {}

    public ArrayList<Resource> get() throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`resources` ORDER BY 1";
        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);

        ArrayList<Resource> resources = new ArrayList<>();
        while (rs.next()) {
            Resource resource = new Resource();
            resource.setName(rs.getString("name"));
            resource.setType(rs.getString("type"));
            resource.setInfo(rs.getString("info"));
            resources.add(resource);
        }

        return resources;
    }

    public void save(String resource_name, String resource_type, String resource_additional) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "INSERT INTO " + Constants.DATABASENAME + ".`resources` VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, resource_name);
        ps.setString(2, resource_type);
        ps.setString(3, resource_additional);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void delete(String resource_name, String resource_type, String resource_additional) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "DELETE FROM " + Constants.DATABASENAME + ".`resources` WHERE `name` = ?, `type`= ? , `info` = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, resource_name);
        ps.setString(2, resource_type);
        ps.setString(3, resource_additional);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

}
