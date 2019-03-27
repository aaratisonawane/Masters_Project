package com.se.pranita.termproject.controllers.posts;

import com.se.pranita.termproject.model.Exam;
import com.se.pranita.termproject.model.dao.ExamDAO;
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
 * Created by Pranita on 23/4/16.
 */
@WebServlet("/exams")
public class ExamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = UserUtil.getCurrentUser(req);
        System.out.println(user);

        try {
            ArrayList<Exam> exams = new ExamDAO().get(user.getNetID());

            req.setAttribute("exams", exams);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/exams.jsp");
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
            if (request.getParameter("action").equalsIgnoreCase("create")) {
//                HttpSession session = request.getSession(false);
                User user = UserUtil.getCurrentUser(request);

                new ExamDAO().save(user.getNetID(), request.getParameter("name"),
                        java.sql.Date.valueOf(request.getParameter("date_of_exam")),
                        request.getParameter("additional_details"));

            } else if (request.getParameter("action").equalsIgnoreCase("update")) {

                new ExamDAO().put(request.getParameter("name"),
                        java.sql.Date.valueOf(request.getParameter("date_of_exam")),
                        request.getParameter("additional_details"), Integer.parseInt(request.getParameter("examID")));

            } else if (request.getParameter("action").equalsIgnoreCase("delete")) {

                new ExamDAO().delete(Integer.parseInt(request.getParameter("examID")));

            } else if(request.getParameter("action").equalsIgnoreCase("enroll")) {

//                HttpSession session = request.getSession(false);
                User user = UserUtil.getCurrentUser(request);

                new ExamDAO().enroll(Integer.parseInt(request.getParameter("examID")), user.getNetID(), true);

            } else if(request.getParameter("action").equalsIgnoreCase("drop")) {
//                HttpSession session = request.getSession(false);
                User user = UserUtil.getCurrentUser(request);

                new ExamDAO().enroll(Integer.parseInt(request.getParameter("examID")), user.getNetID(), false);

            }


            out.print("success");
        } catch (Exception ex) {
            ex.printStackTrace();
            out.print("error");
        }
    }
}
