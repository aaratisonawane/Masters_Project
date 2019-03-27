<%@ page import="com.se.pranita.termproject.model.Result" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.se.pranita.termproject.model.Exam" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<%
    ArrayList<Result> results = (ArrayList) request.getAttribute("results");
    ArrayList<Exam> exams = (ArrayList) request.getAttribute("exams");
%>
<body onload='setExams(<%=exams%>)'>
<%@ include file="header.jsp" %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <h1>Results</h1>
                <% if (currentUser.getType().getValue() != 0) { %>
                <div>
                    <button type="button" class="btn btn-primary pull-right" onclick="addResult('Create');">New Result
                    </button>
                    <br/></div>
                <br/>
                <% } %>
                <% if (results.size() > 0) {%>
                <div class="panel-group" id="accordion">
                    <% for (int i = 0; i < results.size(); i++) {
                        Result result = results.get(i);
                    %>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <% if( result.isOwner() ) { %>
                                <button id="delete<%= i %>" class="btn btn-danger pull-right" onclick='deleteResult(<%=result%>)'>
                                    Delete
                                </button>
                                <button id="edit<%= i %>" class="btn btn-warning pull-right" onclick='addResult("Update", <%=result%>);'>
                                    Edit
                                </button>
                            <% } %>
                            <h4 class="panel-title">
                                <b><%= result.getExamName() %></b><br/>
                            </h4>
                            <h5>Posted on: <%= new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a").format(result.getCreateTime()) %><br/>
                                Posted by: <%= result.getOwnerName() %></h5>
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse<%= i %>">
                                Show Result</a>
                        </div>
                        <div id="collapse<%= i %>" class="panel-collapse collapse">
                            <div class="panel-body">
                                <pre><code><%= result.getResultDetails() %></code></pre>
                            </div>
                        </div>
                    </div>
                    <% } %>

                </ul>
                <% } else {%>
                    <div class="jumbotron">
                        <p>No results to display.</p>
                    </div>
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
<script src="js/results.js"></script>
</body>

</html>