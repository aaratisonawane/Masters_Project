package com.se.pranita.termproject.model.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Pranita on 11/5/16.
 */
public class UserUtil {

    private static CurrentUserObserver currentUser = null;

    public static User getCurrentUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if(currentUser == null) {
            currentUser = new CurrentUserObserver((User) session.getAttribute("currentSessionUser"), session);
        }
        return currentUser.getCurrentUser(session);
    }
}
