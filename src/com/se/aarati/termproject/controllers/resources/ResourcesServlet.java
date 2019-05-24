package com.se.aarati.termproject.controllers.resources;

import com.se.aarati.termproject.model.*;
import com.se.aarati.termproject.model.common.ConnectionHandler;
import com.se.aarati.termproject.model.dao.ReservationDAO;
import com.se.aarati.termproject.model.dao.ResourcesDAO;
import com.se.aarati.termproject.model.user.User;
import com.se.aarati.termproject.model.user.UserUtil;
import com.se.aarati.termproject.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by aarati on 20/5/19.
 */
@WebServlet("/view_resources")
public class ResourcesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = UserUtil.getCurrentUser(req);

        try {
            ArrayList<Reservation> reservations = new ReservationDAO().getById(user.getNetID());

            req.setAttribute("reservations", reservations);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/ViewResources");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        try {

            new ResourcesDAO().save(req.getParameter("resource_name"), req.getParameter("resource_type"),
                    req.getParameter("resource_additional"));

            response.sendRedirect("/reserve_resource");

        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect("/error");
        }
    }
}
