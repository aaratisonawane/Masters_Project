<%--
  Created by IntelliJ IDEA.
  User: pranita
  Date: 14/4/16
  Time: 7:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <div class="jumbotron">
                    <h2>About WebPortal</h2>
                    <%--<h1>Welcome to the WebPortal homepage.</h1>--%>
                    <p>WebPortal is a SE Project for Spring 2016 Term developed by Pranita Setiya.</p>
                    <p>WebPortal is a Student Management System.</p>
                    <p>Technologies Used:
                    <ul>
                        <li>JSP and Servlets</li>
                        <li>MySQL</li>
                        <li>Apache Tomcat Server</li>
                        <li>JUnit 4</li>
                    </ul>
                    </p>
                    <p>
                        Libraries Used:
                        <ul>
                        <li><a href="http://getbootstrap.com/" target="_blank">Twitter Bootstrap</a></li>
                        <li><a href="https://jquery.com/" target="_blank">jQuery</a></li>
                        <li><a href="https://eonasdan.github.io/bootstrap-datetimepicker/" target="_blank">Bootstrap Datetimepicker</a></li>
                        <li><a href="https://plugins.jquery.com/toaster/" target="_blank">jQuery Toaster</a></li>
                        <li><a href="http://momentjs.com/" target="_blank">Moment</a></li>
                        <li><a href="https://github.com/google/gson" target="_blank">Gson</a></li>
                        </ul>
                    </p>
                </div>
            </div>
        </div>

        <%@ include file="footer.jsp" %>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/sidebar.js"></script>
</body>

</html>
