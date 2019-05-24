package com.se.aarati.termproject.controllers.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by aarati on 19/5/19.
 */
@WebServlet("/SignOut")
public class SignOutServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null){
            session.removeAttribute("user");
            session.invalidate();
        }
        System.out.println("Signing Out");
        resp.sendRedirect("/");
    }
}
