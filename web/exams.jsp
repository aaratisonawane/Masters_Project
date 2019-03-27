<%@ page import="com.se.pranita.termproject.model.Exam" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exams</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
    <link href="css/forum.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<%
    ArrayList<Exam> exams = (ArrayList) request.getAttribute("exams");
%>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <h1>Exams</h1>
                <% if (currentUser.getType().getValue() != 0) {%>
                <div>
                    <button type="button" class="btn btn-primary pull-right" onclick="addExam('Create');">New Exam
                    </button>
                    <br/></div>
                <br/>
                <% } %>
                <% if (exams.size() > 0) {%>
                <ul class="list-group">
                    <% for (int i = 0; i < exams.size(); i++) {
                        Exam exam = exams.get(i);
                    %>
                    <li class="list-group-item">

                        <div>
                            <div class="post-head-container">
                                <div class="post-head post-head-left-thread">
                                    <div class="postbit-social-left">

                                        <a><i
                                                class="g-list-alt glyphicon glyphicon-list-alt"></i></a>

                                    </div>
                                </div>

                                <div class="post-head post-head-right-thread">
                                    <b><a><%= exam.getName() %>
                                    </a></b>
                                    </b>
                                    <% if (exam.getNetID().equalsIgnoreCase(currentUser.getNetID())) { %>
                                    <div class="postbit-buttons">
                                        <a href='javascript:deleteExam(<%= exam %>);' title="Delete Exam"><i
                                                class="g-delete glyphicon glyphicon-trash"></i></a>
                                        <a href='javascript:addExam("Update", <%= exam %>);' title="Edit Exam"><i
                                                class="g-edit glyphicon glyphicon-edit"></i></a>
                                    </div>
                                    <% } %>
                                    <% if (currentUser.getType().getValue() == 0) { %>
                                    <% if (exam.isEnrolled()) { %>
                                    <% if (exam.isExpired()) { %>
                                    <button id="enroll<%= i %>" class="btn btn-success pull-right disabled"
                                            style="margin: 6px; ">Enrolled
                                    </button>
                                    <% } else {%>
                                    <button id="enroll<%= i %>" class="btn btn-success pull-right"
                                            onmouseover="makeDrop('<%= i %>')" onmouseleave="makeEnrolled('<%= i %>');"
                                            onclick='enroll("<%= i %>", <%= exam %>);' style="margin: 6px; ">Enrolled
                                    </button>
                                    <% } %>
                                    <% } else {%>
                                    <% if (!exam.isExpired() || Date.valueOf(exam.getDateOfExam()).compareTo(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()))) == 0) { %>
                                    <button id="enroll<%= i %>" class="btn btn-success pull-right" style="margin: 6px;" onmouseover="makeDrop('<%= i %>')" onmouseleave="makeEnrolled('<%= i %>');"
                                            onclick='enroll("<%= i %>", <%= exam %>);'>Enroll
                                    </button>
                                    <% } %>
                                    <% } %>
                                    <% } %>
                                </div>
                            </div>
                            <div class="post-content-container">
                                <div>
                                    <%= exam.getAdditionalDetails() %><br/><br/>
                                    <span class="time"><b>Exam date </b><%= new SimpleDateFormat("EEE, dd MMM yyyy").format(java.sql.Date.valueOf(exam.getDateOfExam())) %></span><br/>
                                    <span class="time"><b>Created by </b><%= exam.getOwnerName() %></span>
                                </div>
                            </div>
                        </div>
                    </li>
                    <% } %>

                </ul>
                <% } else {%>
                <p>No Exams.</p>
                <% } %>
            </div>

        </div>


        <div id="deleteModalHere"></div>
        <div id="UpdateModalHere"></div>
        <div id="CreateModalHere"></div>



        <%@ include file="footer.jsp" %>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/vendor/jquery.toaster.js"></script>

<script src="js/sidebar.js"></script>
<script src="js/exams.js"></script>
</body>

</html>