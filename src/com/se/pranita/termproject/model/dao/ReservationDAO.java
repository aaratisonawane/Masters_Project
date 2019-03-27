package com.se.pranita.termproject.model.dao;

import com.se.pranita.termproject.model.Reservation;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Pranita on 24/4/16.
 */
public class ReservationDAO {

    public ReservationDAO() {}

    public ArrayList<Reservation> getById(String netID) throws SQLException {
        Connection conn = ConnectionHandler.getConnection();
        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`reservations` WHERE `netID`='" + netID
                + "' AND `slot_date`>='" + new Date(Calendar.getInstance().getTime().getTime()) + "' ORDER BY 3";
        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);


        ArrayList<Reservation> reservations = new ArrayList<>();
        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setResourceName(rs.getString("name"));
            reservation.setNetID(rs.getString("netID"));
            reservation.setSlot_time_range(rs.getString("slot_time_range"));
            reservation.setSlot_date(rs.getDate("slot_date"));
            reservations.add(reservation);
        }

        return reservations;
    }

    public void doOperation(boolean isSave, String name, String netId, Date slot_date, String slot_time) throws SQLException {
        String query;
        if(isSave)
            query = "INSERT INTO " + Constants.DATABASENAME + ".`reservations` VALUES (?, ?, ?, ?)";
        else
            query = "DELETE FROM " + Constants.DATABASENAME + ".`reservations` WHERE name=? AND netID=? AND slot_date=? AND slot_time_range=?";

        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, name);
        ps.setString(2, netId);
        ps.setDate(3, slot_date);
        ps.setString(4, slot_time);

        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }

    public void save(String name, String netId, Date slot_date, String slot_time) throws SQLException {
        doOperation(true, name, netId, slot_date, slot_time);
    }

    public void delete(String name, String netId, Date slot_date, String slot_time) throws SQLException {
        doOperation(false, name, netId, slot_date, slot_time);
    }

}
