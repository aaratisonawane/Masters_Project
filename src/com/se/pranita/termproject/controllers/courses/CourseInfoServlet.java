package com.se.pranita.termproject.controllers.courses;

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
import java.sql.SQLException;

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/course_info")
public class CourseInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = UserUtil.getCurrentUser(req);

        try {
            req.setAttribute("courses", new CoursesDAO().getById(user.getNetID()));
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/course_info.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }
}
