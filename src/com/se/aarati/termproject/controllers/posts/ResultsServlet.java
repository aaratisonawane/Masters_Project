package com.se.aarati.termproject.controllers.posts;

import com.se.aarati.termproject.model.Exam;
import com.se.aarati.termproject.model.Result;
import com.se.aarati.termproject.model.dao.ExamDAO;
import com.se.aarati.termproject.model.dao.ResultDAO;
import com.se.aarati.termproject.model.user.User;
import com.se.aarati.termproject.model.user.UserUtil;

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
import java.util.stream.Collectors;

/**
 * Created by aarati on 24/5/19.
 */
@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = UserUtil.getCurrentUser(req);

        try {
            ArrayList<Result> results = new ResultDAO().get(user.getNetID());
            ArrayList<Exam> exams = new ExamDAO().get(user.getNetID(), true);
            ArrayList<Exam> newExams = exams.stream().filter(Exam::isExpired).collect(Collectors.toCollection(ArrayList::new));
            req.setAttribute("results", results);
            req.setAttribute("exams", newExams);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/results.jsp");
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

                new ResultDAO().save(user.getNetID(), request.getParameter("result_details"), Integer.parseInt(request.getParameter("examID")));

            } else if (request.getParameter("action").equalsIgnoreCase("update")) {

                new ResultDAO().put(Integer.parseInt(request.getParameter("examID")), request.getParameter("result_details"), request.getParameter("resultID"));

            } else if (request.getParameter("action").equalsIgnoreCase("delete")) {
                new ResultDAO().delete(Integer.parseInt(request.getParameter("resultID")));
            }


            out.print("success");
        } catch (Exception ex) {
            ex.printStackTrace();
            out.print("error");
        }
    }
}
