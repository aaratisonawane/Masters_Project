package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.dao.UserDAO;
import com.se.pranita.termproject.model.user.Student;
import com.se.pranita.termproject.model.user.User;
import com.se.pranita.termproject.model.user.UserUtil;

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
import java.util.Enumeration;

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/view_student")
public class ViewStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            User user = new UserDAO().getStudentById(req.getParameter("netID"));
            if (user == null) {
                throw new SQLException("Error retrieving data.");
            }
            System.out.println(user);
            req.setAttribute("user", user);

            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/view_student.jsp");
            rd.forward(req, resp);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            HttpSession session = req.getSession(false);
            session.setAttribute("error", ex.getMessage());
            resp.sendRedirect("/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            User user = UserUtil.getCurrentUser(req);
            if (user.getPassword().equalsIgnoreCase(req.getParameter("oldPass"))) {
                String newPass = (req.getParameter("newPass") == null)? user.getPassword() : req.getParameter("newPass");
                if(newPass.isEmpty())
                    newPass = user.getPassword();
                String lastName = req.getParameter("lastName") == null ? "" : req.getParameter("lastName");
                String firstName = req.getParameter("firstName") == null ? "" : req.getParameter("firstName");
                String phoneNumber = req.getParameter("phoneNumber") == null ? "" : req.getParameter("phoneNumber");
                String advisor = req.getParameter("advisor") == null ? "" : req.getParameter("advisor");
                new UserDAO().put(req.getParameter("netID"), firstName, lastName,
                        newPass, phoneNumber, advisor);
                user.setPassword(newPass);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                ((Student)user).setPhoneNumber(phoneNumber);
                ((Student)user).setAdvisorName(advisor);
                out.print("success");
            } else {
                out.print("error");
            }
        }catch (Exception ex) {
            out.print("error");
        }
    }
}
