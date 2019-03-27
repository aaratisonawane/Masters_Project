package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.dao.UserDAO;
import com.se.pranita.termproject.model.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/phd_students")
public class UserDetailServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ArrayList<User> users = new UserDAO().getByProgram("Ph.D");

            req.setAttribute("users", users);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/phd_students.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            HttpSession session = req.getSession(false);
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }
}
