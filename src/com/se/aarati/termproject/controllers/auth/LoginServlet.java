package com.se.aarati.termproject.controllers.auth;

import com.se.aarati.termproject.model.user.Authenticator;
import com.se.aarati.termproject.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by aarati on 14/4/19.
 */
@WebServlet(name = "com.se.aarati.termproject.controllers.auth.LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            User user = Authenticator.login(request.getParameter("netid"), request.getParameter("password"));
            HttpSession session = request.getSession(false);
            if (user != null)
            {
                session.setAttribute("currentSessionUser", user);
                response.sendRedirect("/home");
            }
            else {
                session.setAttribute("error", "Invalid Username or Password");
                response.sendRedirect("/error");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
