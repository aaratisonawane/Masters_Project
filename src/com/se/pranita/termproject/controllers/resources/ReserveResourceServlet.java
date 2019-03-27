package com.se.pranita.termproject.controllers.resources;

import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.model.Resource;
import com.se.pranita.termproject.model.dao.ReservationDAO;
import com.se.pranita.termproject.model.dao.ResourcesDAO;
import com.se.pranita.termproject.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Pranita on 20/4/16.
 */
@WebServlet("/reserve_resource")
public class ReserveResourceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        HttpSession session = req.getSession(false);
        try {
            ArrayList<Resource> resources = new ResourcesDAO().get();
            req.setAttribute("resources", resources);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/ReserveResource");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            new ReservationDAO().delete(request.getParameter("name"), request.getParameter("netId"),
                    java.sql.Date.valueOf(request.getParameter("slot_date")), request.getParameter("slot_time"));

            out.print("success");
        }catch (SQLException ex){
            ex.printStackTrace();
            out.print("error");
        }
    }
}
