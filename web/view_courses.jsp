<%@ page import="com.se.pranita.termproject.model.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<% ArrayList<Course> courses = (ArrayList) request.getAttribute("courses");
    User user = ((User) session.getAttribute("currentSessionUser"));
    boolean isStudent = user.getType().getValue() == 0;
%>
<body onload='setCourses(<%= courses %>);'>
<%@ include file="header.jsp" %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <% if(courses.size() > 0) {%>
                <div id="department" style="padding-top: 1%; padding-bottom: 0.5%">
                    <label for="dept">Filter by Department</label>
                    <select name="department" class="form-control" id="dept" onchange="updateEntries()">
                        <option selected>All departments</option>
                        <option>Chemical Engineering</option>
                        <option>Computer Science</option>
                        <option>Computer Engineering</option>
                        <option>Electrical Engineering</option>
                        <option>Environmental Sciences</option>
                        <option>Mechanical Engineering</option>
                    </select>
                </div>
                <div style="padding-bottom: 1%" id="term_parent">
                    <label for="term">Filter by Term</label>
                    <select name="term" class="form-control" id="term" onchange="updateEntries()">
                        <option selected>All Terms</option>
                    </select>
                </div>
                <div class="panel-group" id="accordion">
                    <% for (int i = 0; i < courses.size(); i++) {
                        Course course = (Course) courses.get(i);
                    %>
                    <div class="panel panel-default" id="course-<%= i %>">
                        <div class="panel-heading">
                            <% if( isStudent ) { %>
                                <% if(course.getStatus().getValue() == 0) { %>
                                    <button id="enroll<%= i %>" class="btn btn-success pull-right" onmouseover="makeDrop('<%= i %>')" onmouseleave="makeEnrolled('<%= i %>');"
                                            onclick="enroll('<%= i %>', '<%= user.getNetID() %>', '<%= course.getNumber() %>', '<%= course.getTerm() %>', '<%= course.getYear() %>');">
                                        Enrolled
                                    </button>
                                <% } else if(course.getStatus().getValue() == 1) {%>
                                    <button id="enroll<%= i %>" class="btn btn-primary pull-right"  onmouseover="makeDrop('<%= i %>')" onmouseleave="makeEnrolled('<%= i %>');"
                                            onclick="enroll('<%= i %>', '<%= user.getNetID() %>', '<%= course.getNumber() %>', '<%= course.getTerm() %>', '<%= course.getYear() %>');">
                                        Enroll
                                    </button>
                                <% } else if(course.getStatus().getValue() == 2) {%>
                                    <button id="enroll<%= i %>" class="btn btn-success pull-right disabled">
                                        Completed
                                    </button>
                                <% } %>
                            <% } %>
                            <h4 class="panel-title">
                                <b><%= course.getNumber() %>: <%= course.getName() %></b><br/>
                            </h4>
                            <h5>Term: <%= course.getTerm() %> <%= course.getYear()%><br/>
                                Department: <%= course.getDepartment() %></h5>
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse<%= i %>">
                                more details</a>
                        </div>
                        <div id="collapse<%= i %>" class="panel-collapse collapse">
                            <div class="panel-body">
                                <b>Instructor:</b> <%= course.getInstructor_name() %><br/>
                                <% if(!course.getIns_office().isEmpty()) {%>
                                    <b>Office:</b> <%= course.getIns_office() %><br/>
                                <% } %>
                                <% if(!course.getIns_office_hour().isEmpty()) {%>
                                <b>Office Hours:</b> <%= course.getIns_office_hour() %><br/>
                                <% } %>
                                <a href="mailto:<%= course.getInstructor()%>@webportal.edu" target="_blank">Send Email</a><br/>
                                <br/>
                                <% if(!course.getTa_name().isEmpty()) {%>
                                <b>TA:</b> <%= course.getTa_name()%><br/>
                                <b>Office:</b> <%= course.getTa_office()%><br/>
                                <b>Office Hours:</b> <%= course.getTa_office_hour()%><br/>
                                <a href="mailto:<%= course.getTa_email()%>" target="_blank">Send Email</a><br/>
                                <br/>
                                <% } %>
                                <b>Course Syllabus:</b><br/>
                                <%= course.getCourse_syllabus().isEmpty() ? "NA" : course.getCourse_syllabus()%>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>
                <% } else { %>
                <div class="jumbotron">
                    <h2>Courses</h2>
                    <p>No courses to display.</p>
                </div>
                <% }%>
            </div>
        </div>

        <%@ include file="footer.jsp" %>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/sidebar.js"></script>
<script src="js/vendor/jquery.toaster.js"></script>
<script src="js/view_courses.js"></script>
</body>

</html>