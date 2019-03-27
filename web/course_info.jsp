<%@ page import="java.util.ArrayList" %>
<%@ page import="com.se.pranita.termproject.model.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Information</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
    <link href="css/table_sorter.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<%
    ArrayList<Course> courses = (ArrayList) request.getAttribute("courses");
%>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <div class="jumbotron">
                    <center><h2>Your Courses</h2></center>
                    <% if(courses.size() > 0) {%>
                    <%--<center><h4>Courses you are teaching.</h4></center>--%>

                    <table id="keywords" cellspacing="0" cellpadding="0">
                        <thead>
                        <tr>
                            <th><span>Term</span></th>
                            <th><span>Course</span></th>
                            <th><span>TA</span></th>
                            <th><span>Course Syllabus</span></th>
                            <th><span>Instructor Info</span></th>
                            <th><span>Edit</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (int i = 0; i < courses.size(); i++) {
                            Course course = (Course) courses.get(i);
                        %>
                        <tr>
                            <td class="lalign"><%= course.getTerm() + " " + course.getYear() %>
                            </td>
                            <td><%= course.getNumber() %>
                            </td>
                            <td>
                                <% if (course.getTa_name().isEmpty()) {%>
                                NA
                                <%} else {%>
                                <a href="javascript:showModal('TA info for <%=course.getNumber()%>', '<b>TA name: </b><%= course.getTa_name() %><br/><b>TA email: </b><%= course.getTa_email() %><br/><b>TA Office: </b><%= course.getTa_office() %><br/><b>TA Office hours: </b><%= course.getTa_office_hour() %>', 'Close');"
                                   title="Click for more info."
                                   alt="Click for more info."><%= course.getTa_name() %>
                                </a>
                                <% } %></td>

                            <td>
                                <% if (course.getCourse_syllabus().isEmpty()) {%>
                                NA
                                <%} else {%>
                                <button type="button" class="btn"
                                        onclick="showModal('Syllabus for <%=course.getNumber()%>', '<%=course.getCourse_syllabus()%>', 'Close')">
                                    Click
                                    to show
                                </button>
                                <% } %></td>
                            <td>
                                <button type="button" class="btn"
                                        onclick="showModal('Instructor info for <%=course.getNumber()%>', '<b>Name: </b><%= currentUser.getFirstName() + " " + currentUser.getLastName() %><br/><b>Email: </b><%= course.getInstructor() %>@webportal.edu<br/><b>Office: </b><%= course.getIns_office() %><br/><b>Office hours: </b><%= course.getIns_office_hour() %>', 'Close');">
                                    Click
                                    to show
                                </button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-warning" onclick='showEditModal(<%= course.toJSON() %>);'><i class="glyphicon-edit glyphicon"></i></button>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                    <% } else { %>
                        <center><p>No courses to display.</p></center>
                    <% }%>
                </div>
            </div>
        </div>

        <div id="modalHere">

        </div>

        <%@ include file="footer.jsp" %>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.25.8/js/jquery.tablesorter.min.js"></script>

<script src="js/sidebar.js"></script>
<script src="js/vendor/jquery.toaster.js"></script>
<script src="js/course_info.js"></script>
</body>

</html>