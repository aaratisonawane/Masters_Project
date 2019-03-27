<%@ page import="com.se.pranita.termproject.model.user.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ph.D Students</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<%
    ArrayList<Student> students = (ArrayList) request.getAttribute("users");
%>
<body onload='setStudents(<%= students %>); updateEntries();'>
<%@ include file="header.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <h1>Ph.D Students</h1>
                <div class="pad" id="department">
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
                <ul class="list-group">
                    <% for (int i = 0; i < students.size(); i++) {
                        Student student = (Student) students.get(i);
                    %>

                    <li class="list-group-item" id="student-<%= i %>">
                        <table border="0">
                            <tr>
                                <td><i class="img-circle glyphicon glyphicon-user icon-large icon-white "
                                       style="font-size: 50px; padding: 20px;"></i></td>
                                <td> <a href="/view_student?netID=<%= student.getNetID() %>"><%= student.getFirstName() + " " + student.getLastName() %></a><br/>
                                    <%= student.getNetID() %>@webportal.edu<br/>
                                    <%= student.getDepartment() %><br/>
                                </td>
                            </tr>
                        </table>
                    </li>
                    <% } %>
                </ul>
                    <center id="nostudents"><h3>No students to display</h3></center>
            </div>
        </div>



        <%@ include file="footer.jsp" %>
    </div>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/sidebar.js"></script>
<script src="js/phd_students.js"></script>
</body>

</html>