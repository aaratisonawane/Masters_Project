<%@ page import="com.se.pranita.termproject.model.user.User" %><%--
  Created by IntelliJ IDEA.
  User: pranita
  Date: 15/4/16
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    User currentUser = (User) (session.getAttribute("currentSessionUser"));
%>
<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role = "navigation">
    <div class="container">
        <div class="navbar-header">
            <span class="navbar-brand">WebPortal</span>
            <button type = "button" class = "navbar-toggle"
            data-toggle = "collapse" data-target = "#example-navbar-collapse">
            <span class = "sr-only">Toggle navigation</span>
            <span class = "icon-bar"></span>
            <span class = "icon-bar"></span>
            <span class = "icon-bar"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active" id="nav-home-btn"><a href="/home">Home <span class="sr-only">(current)</span></a></li>
                <li><a href="#" id="nav-res-btn">Resources</a></li>
                <li><a href="#" id="nav-crs-btn">Courses</a></li>
                <li><a href="#" id="nav-post-btn">Posts</a></li>
                <%if(currentUser.getType().getValue() == 1){
                    out.print("<li><a href=\"/phd_students\" id=\"nav-student-btn\">Ph.D. Students</a></li>");
                }%>

                <li><a href="/alumni" id="nav-alumni-btn">Alumni</a></li>
            </ul>
            <% if(currentUser.getType().getValue() == 0) {%>
            <p class="navbar-text navbar-right">Signed in as <a href="/view_student?netID=<%= currentUser.getNetID() %>" class="navbar-link"><%= currentUser.getFirstName() + " " + currentUser.getLastName()%></a></p>
            <% } else {%>
            <p class="navbar-text navbar-right">Signed in as <%= currentUser.getFirstName() + " " + currentUser.getLastName()%></p>
            <% } %>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>