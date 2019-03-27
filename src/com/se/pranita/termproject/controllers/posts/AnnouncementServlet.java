package com.se.pranita.termproject.controllers.posts;

import com.se.pranita.termproject.model.announcement.Announcement;
import com.se.pranita.termproject.model.dao.AnnouncementDAO;
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
@WebServlet("/announcements")
public class AnnouncementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<Announcement> announcements = new AnnouncementDAO().get();
            req.setAttribute("announcements", announcements);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/announcements.jsp");
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

                User user = UserUtil.getCurrentUser(request);

                new AnnouncementDAO().save(user.getNetID(), request.getParameter("title"),
                        request.getParameter("details"), request.getParameter("link"),
                        Announcement.AnnouncementType.getAnnouncementType(request.getParameter("announcement_type")),
                        request.getParameter("event_datetime"), request.getParameter("event_venue"));

            } else if (request.getParameter("action").equalsIgnoreCase("update")) {

                new AnnouncementDAO().put(request.getParameter("title"),
                        request.getParameter("details"), request.getParameter("link"),
                        Announcement.AnnouncementType.getAnnouncementType(request.getParameter("announcement_type")),
                        request.getParameter("event_datetime"), request.getParameter("event_venue"),
                        Integer.parseInt(request.getParameter("id")));

            } else if (request.getParameter("action").equalsIgnoreCase("delete")) {

                new AnnouncementDAO().delete(Integer.parseInt(request.getParameter("id")));
            }


            out.print("success");
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.print("error");
        }
    }
}
