package com.se.pranita.termproject.model.dao;

import com.se.pranita.termproject.model.Discussion;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.utils.Constants;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Pranita on 24/4/16.
 */
public class DiscussionDAO {

    public DiscussionDAO() {
    }

    private Discussion getDiscussion(ResultSet rs) throws SQLException {
        Discussion discussion = new Discussion();
        discussion.setId(rs.getInt("id"));
        discussion.setDiscussion_id(rs.getInt("discussion_id"));
        discussion.setNetID(rs.getString("netID"));
        discussion.setTitle(rs.getString("title"));
        discussion.setDetails(rs.getString("details"));
        discussion.setCreate_time(rs.getTimestamp("create_time"));
        discussion.setUpdated_time(rs.getTimestamp("updated_time"));
        discussion.setType(Discussion.DiscussionType.getDiscussionType(rs.getInt("type")));
        discussion.setOwnerName(rs.getString("firstName") + " " + rs.getString("lastName"));
        return discussion;
    }

    public ArrayList<Discussion> get() throws SQLException {
        ArrayList<Discussion> discussions = new ArrayList<>();
        Connection conn = ConnectionHandler.getConnection();
        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`discussion_post` JOIN " +
                Constants.DATABASENAME + ".`users` ON " +
                Constants.DATABASENAME + ".`users`.`netID` = " +
                Constants.DATABASENAME + ".`discussion_post`.`netID` " +
                "WHERE " + Constants.DATABASENAME + ".`discussion_post`.`type`=" + 0 + " " +
                "ORDER BY " + Constants.DATABASENAME + ".`discussion_post`.`create_time`" + " DESC";

        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);

        while (rs.next()) {
            discussions.add(getDiscussion(rs));
        }

        return discussions;
    }

    public ArrayList<Discussion> get(String discussionId) throws SQLException {
        ArrayList<Discussion> discussions = new ArrayList<>();
        Connection conn = ConnectionHandler.getConnection();
        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`discussion_post` JOIN " + Constants.DATABASENAME + ".`users` ON " +
                Constants.DATABASENAME + ".`users`.`netID` = " +
                Constants.DATABASENAME + ".`discussion_post`.`netID` " +
                "WHERE " + Constants.DATABASENAME + ".`discussion_post`.`discussion_id`='" + discussionId + "' " +
                "OR " + Constants.DATABASENAME + ".`discussion_post`.`id`='" + discussionId + "' " +
                "ORDER BY " + Constants.DATABASENAME + ".`discussion_post`.`create_time`" + " ASC";

        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);

        while (rs.next()) {
            discussions.add(getDiscussion(rs));
        }
        return discussions;
    }

    public void save(String netID, String title, String details, int type, String discussionID) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();

        String query = "INSERT INTO " + Constants.DATABASENAME +
                ".`discussion_post` (`netID`, `title`, `details`, `type`, `discussion_id`) " +
                "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, netID);
        ps.setString(2, title);
        ps.setString(3, details);
        ps.setInt(4, type);
        if (discussionID != null)
            ps.setInt(5, Integer.parseInt(discussionID));
        else
            ps.setString(5, null);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void put(String title, String details, int id) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();

        String query = "UPDATE " + Constants.DATABASENAME + ".`discussion_post` SET `title`=?, `details`=? " +
                "WHERE `id`=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, title);
        ps.setString(2, details);

        ps.setInt(3, id);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void delete(int id) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "DELETE FROM " + Constants.DATABASENAME + ".`discussion_post` WHERE id=? OR discussion_id=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, id);
        ps.setInt(2, id);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

}
