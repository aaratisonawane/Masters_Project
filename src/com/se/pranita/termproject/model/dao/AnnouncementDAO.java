package com.se.pranita.termproject.model.dao;

import com.se.pranita.termproject.model.announcement.Announcement;
import com.se.pranita.termproject.model.announcement.AnnouncementFactory;
import com.se.pranita.termproject.model.announcement.Event;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.utils.Constants;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Pranita on 24/4/16.
 */
public class AnnouncementDAO {

    public AnnouncementDAO() {
    }

    public ArrayList<Announcement> get() throws SQLException {
        ArrayList<Announcement> announcements = new ArrayList<>();
        Connection conn = ConnectionHandler.getConnection();
        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`announcements` JOIN " + Constants.DATABASENAME + ".`users` ON " +
                Constants.DATABASENAME + ".`users`.`netID` = " +
                Constants.DATABASENAME + ".`announcements`.`netID` ORDER BY " + Constants.DATABASENAME + ".`announcements`.`create_time`" + " DESC";

        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);

        while (rs.next()) {
            Announcement announcement = new AnnouncementFactory().getAnnouncement(rs.getInt("announcement_type"));
            announcement.setNetID(rs.getString("netID"));
            announcement.setLink(rs.getString("link"));
            announcement.setTitle(rs.getString("title"));
            announcement.setDetails(rs.getString("details"));
            announcement.setCreateTime(rs.getTimestamp("create_time"));
            announcement.setId(rs.getInt("id"));

            if (announcement.getType() == Announcement.AnnouncementType.EVENT) {
                ((Event) announcement).setEventDatetime(rs.getTimestamp("event_datetime"));
                ((Event) announcement).setEventVenue(rs.getString("event_venue"));
            }
            announcement.setOwnerName(rs.getString("firstName") + " " + rs.getString("lastName"));

            announcements.add(announcement);

        }

        return announcements;
    }

    public void save(String netID, String title, String details, String link,
                     Announcement.AnnouncementType announcementType, String eventDateTime,
                     String eventVenue) throws SQLException {

        Connection conn = ConnectionHandler.getConnection();
        String query = "INSERT INTO " + Constants.DATABASENAME +
                ".`announcements` (`netID`, `title`, `details`, `link`, `announcement_type`, `event_datetime`, " +
                "`event_venue`) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, netID);
        ps.setString(2, title);
        ps.setString(3, details);
        ps.setString(4, link);
        ps.setInt(5, announcementType.getValue());

        if (announcementType == Announcement.AnnouncementType.EVENT) {

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
            java.util.Date startDate;
            try {
                startDate = df.parse(eventDateTime.trim());
                ps.setTimestamp(6, new Timestamp(startDate.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
                ps.setTimestamp(6, null);
            }

            ps.setString(7, eventVenue);
        } else {
            ps.setTimestamp(6, null);
            ps.setString(7, null);
        }

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void put(String title, String details, String link, Announcement.AnnouncementType announcementType,
                    String eventDateTime, String eventVenue, int id) throws SQLException {

        Connection conn = ConnectionHandler.getConnection();
        String query = "UPDATE " + Constants.DATABASENAME + ".`announcements` SET `title`=?, `details`=?, `link`=?, " +
                "`announcement_type`=?, `event_datetime`=?, `event_venue`=? WHERE `id`=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, title);
        ps.setString(2, details);
        ps.setString(3, link);
        ps.setInt(4, announcementType.getValue());

        if (announcementType == Announcement.AnnouncementType.EVENT) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
            java.util.Date startDate;
            try {
                startDate = df.parse(eventDateTime.trim());
                ps.setTimestamp(5, new Timestamp(startDate.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
                ps.setTimestamp(5, null);
            }
            ps.setString(6, eventVenue);
        } else {
            ps.setTimestamp(5, null);
            ps.setString(6, null);
        }
        ps.setInt(7, id);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }


    public void delete(int id) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "DELETE FROM " + Constants.DATABASENAME + ".`announcements` WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, id);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

}
