package com.se.pranita.termproject.controllers.common;

import com.se.pranita.termproject.model.user.User;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        System.out.println("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        System.out.println("Requested Resource::" + uri);

        HttpSession session = req.getSession(false);
        //check if page doesn't require authentication
        if (uri.equalsIgnoreCase("/") || uri.endsWith("/error")
                || uri.endsWith("/LoginServlet") || uri.endsWith("/SignUpServlet") || uri.endsWith("/SignUp")
                || uri.endsWith("css") || uri.endsWith("js")
                || uri.endsWith("woff") || uri.endsWith("ttf")
                || uri.endsWith("woff2")) {
            chain.doFilter(request, response);
        } else if (session == null && !(uri.equalsIgnoreCase("/"))) {
            System.out.println("Unauthorized access request");
            res.sendRedirect("/");
        } else {

            if (session != null) {
                if (session.getAttribute("currentSessionUser") == null) {
                    System.out.println("Session isn't null but no user attribute");
                    res.sendRedirect("/");
                } else {
                    System.out.println("has user attribute");

                    //check if User.UserType has permission for page
                    User user = (User) session.getAttribute("currentSessionUser");
                    if(hasPermission(user.getType(), uri))
                        chain.doFilter(request, response);
                    else
                        res.sendRedirect("/home");
                }
            } else {
                System.out.println("session is null");
                chain.doFilter(request, response);
            }

        }
    }

    private boolean hasPermission(User.UserType type, String uri) {
        if(uri.endsWith("/create_course")) {
            return !(type == User.UserType.STUDENT || type == User.UserType.STAFF);
        } else if(uri.endsWith("/course_info")) {
            return !(type == User.UserType.STUDENT || type == User.UserType.STAFF);
        } else if(uri.endsWith("/phd_students")) {
            return !(type == User.UserType.STUDENT || type == User.UserType.STAFF);
        } else if(uri.endsWith("/add_resource")) {
            return type == User.UserType.STAFF;
        }
        return true;
    }


    public void destroy() {
        //close any resources here
    }

}