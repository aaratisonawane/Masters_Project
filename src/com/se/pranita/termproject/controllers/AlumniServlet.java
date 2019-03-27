package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.Alumni;
import com.se.pranita.termproject.model.dao.AlumniDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/alumni")
public class AlumniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        try {
            ArrayList<Alumni> alumnis = new AlumniDAO().get();

            req.setAttribute("alumnis", alumnis);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/alumni.jsp");
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

            if(request.getParameter("action").equalsIgnoreCase("save")) {

                new AlumniDAO().save(request.getParameter("name"), request.getParameter("homepage"),
                        request.getParameter("description"), request.getParameter("image"));

            }else if(request.getParameter("action").equalsIgnoreCase("update")) {

                new AlumniDAO().put(request.getParameter("name"), request.getParameter("homepage"),
                        request.getParameter("description"), request.getParameter("image"),
                        request.getParameter("old_name"));

            } else if(request.getParameter("action").equalsIgnoreCase("delete")) {
                new AlumniDAO().delete(request.getParameter("name"));
            }


            out.print("success");
        }catch (SQLException ex){
            ex.printStackTrace();
            out.print("error");
        }
    }
}
