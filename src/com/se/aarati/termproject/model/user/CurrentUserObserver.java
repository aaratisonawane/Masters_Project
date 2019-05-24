package com.se.aarati.termproject.model.user;

import javax.servlet.http.HttpSession;

/**
 * Created by aarati on 11/6/19.
 */
public class CurrentUserObserver extends Observer{

    private HttpSession session;

    public CurrentUserObserver(User user, HttpSession session) {
        this.user = user;
        this.user.attach(this);
        this.session = session;
    }

    @Override
    public void update() {
        User currentUser = (User) session.getAttribute("currentSessionUser");
        if(currentUser != null && this.user != null)
            if(currentUser.getNetID().equalsIgnoreCase(this.user.getNetID()))
                session.setAttribute("currentSessionUser", this.user);
        System.out.println("Current User modified: " + user);
    }

    public User getCurrentUser(HttpSession session) {
            return (User) session.getAttribute("currentSessionUser");
    }
}
