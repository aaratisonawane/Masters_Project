package com.se.pranita.termproject.controllers.resources;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.model.dao.ReservationDAO;
import com.se.pranita.termproject.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Pranita on 20/4/16.
 */
@WebServlet("/TimeSlots")
public class TimeSlotServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {

            new ReservationDAO().save(request.getParameter("name"), request.getParameter("netID"),
                    java.sql.Date.valueOf(request.getParameter("slot_date")), request.getParameter("slot_time"));

            out.print("success");
        }catch (SQLException ex){
            ex.printStackTrace();
            out.print("error");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            JsonObject jsonObject = new JsonObject();

            String netID = request.getParameter("netID");
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT * FROM " + Constants.DATABASENAME + ".`reservations` WHERE `name`='"
                    + request.getParameter("name") + "' AND `slot_date`='" + request.getParameter("date") + "'";
            if(netID != null){
                query += " AND `netID`='" + netID + "'";
            }
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);
            JsonArray jsonArray = new JsonArray();
            while (rs.next()) {
                jsonArray.add(rs.getString("slot_time_range"));
            }
            jsonObject.add("data", jsonArray);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
