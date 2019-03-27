package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.dao.UserDAO;
import com.se.pranita.termproject.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Pranita on 16/4/16.
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        try {

            new UserDAO().save(request.getParameter("netID"), request.getParameter("password"),
                    request.getParameter("firstName"), request.getParameter("lastName"),
                    User.UserType.getUserType(request.getParameter("role")),
                    request.getParameter("department"), request.getParameter("program"), request.getParameter("sem"),
                    request.getParameter("year"));

            response.sendRedirect("/");

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect("/error");
        }

    }
}
