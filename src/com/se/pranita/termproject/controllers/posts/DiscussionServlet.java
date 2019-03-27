package com.se.pranita.termproject.controllers.posts;

import com.se.pranita.termproject.model.Discussion;
import com.se.pranita.termproject.model.dao.DiscussionDAO;
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
@WebServlet("/discussions")
public class DiscussionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ArrayList<Discussion> discussions;
            String jsp;
            if (req.getParameter("discussion_id") == null) {
                discussions = new DiscussionDAO().get();
                jsp = "/discussion.jsp";
            } else {
                discussions = new DiscussionDAO().get(req.getParameter("discussion_id"));
                jsp = "/discussion_thread.jsp";
            }
            req.setAttribute("discussions", discussions);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher(jsp);
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            HttpSession session = req.getSession(false);
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PrintWriter out = response.getWriter();
        try {


            if (request.getParameter("action").equalsIgnoreCase("save")) {
//                HttpSession session = request.getSession(false);
                User user = UserUtil.getCurrentUser(request);

                new DiscussionDAO().save(user.getNetID(), request.getParameter("title"),
                        request.getParameter("details"), Integer.parseInt(request.getParameter("type")),
                        request.getParameter("discussion_id"));

            } else if (request.getParameter("action").equalsIgnoreCase("update")) {

                new DiscussionDAO().put(request.getParameter("title"),
                        request.getParameter("details"), Integer.parseInt(request.getParameter("id")));

            } else if (request.getParameter("action").equalsIgnoreCase("delete")) {

                new DiscussionDAO().delete(Integer.parseInt(request.getParameter("id")));
            }


            out.print("success");
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.print("error");
        }
    }
}
