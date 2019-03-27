package com.se.pranita.termproject.controllers.courses;


import com.se.pranita.termproject.model.Course;
import com.se.pranita.termproject.model.dao.CoursesDAO;
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
import java.util.ArrayList;

/**
 * Created by Pranita on 21/4/16.
 */
@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = UserUtil.getCurrentUser(req);
        System.out.println(user);

        try {

            ArrayList<Course> courses = new CoursesDAO().get(user.getType(), user.getNetID());

            req.setAttribute("courses", courses);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/view_courses");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            HttpSession session = req.getSession(false);
            if (req.getParameter("action") == null) {
                try {

                    new CoursesDAO().save(req.getParameter("course_num"), req.getParameter("course_name"),
                            req.getParameter("department"), req.getParameter("sem"), Integer.parseInt(req.getParameter("year")),
                            req.getParameter("course_syllabus"), req.getParameter("ins_office"),
                            UserUtil.getCurrentUser(req).getNetID(), req.getParameter("ins_office_hour"),
                            req.getParameter("ta_name"), req.getParameter("ta_email"), req.getParameter("ta_office"),
                            req.getParameter("ta_office_hour"));

                    resp.sendRedirect("/courses");


                } catch (Exception ex) {
                    session.setAttribute("error", ex.getMessage());
                    resp.sendRedirect("/error");
                }
            } else if (req.getParameter("action").equalsIgnoreCase("drop") || req.getParameter("action").equalsIgnoreCase("enroll")) {
                new CoursesDAO().enroll(req.getParameter("action").equalsIgnoreCase("enroll"),
                        req.getParameter("netId"), req.getParameter("num"),
                        req.getParameter("tsem"), Integer.parseInt(req.getParameter("tyear")));
            } else if (req.getParameter("action").equalsIgnoreCase("update")) {
                new CoursesDAO().put(req.getParameter("number"), req.getParameter("term"),
                        Integer.parseInt(req.getParameter("year")),
                        req.getParameter("department"), req.getParameter("course_syllabus"),
                        req.getParameter("ins_office_hour"), req.getParameter("ins_office"),
                        req.getParameter("ta_name"), req.getParameter("ta_office_hour"),
                        req.getParameter("ta_office"), req.getParameter("ta_email"));
            }
            out.print("success");
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.print("error");
        }
    }
}
