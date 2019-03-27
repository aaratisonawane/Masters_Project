<%@ page import="com.se.pranita.termproject.model.Resource" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resources</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<body onload="updateMinDate();">
<%@ include file="header.jsp" %>
<% ArrayList<Resource> resources = (ArrayList) request.getAttribute("resources");%>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <h1>Resources</h1>
                <% if (resources.size() > 0) {%>
                <div class="panel-group" id="accordion">
                    <% for (int i = 0; i < resources.size(); i++) {
                        Resource resource = (Resource) resources.get(i);
                    %>
                    <%--<option value="<%= option %>"><%= option %></option>--%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <b><%= resource.getType() %>:</b> <%= resource.getName() %><br/>
                                <b>Additional Info:</b> <%= resource.getInfo() %>
                            </h4>
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse<%= i %>">
                                Reserve</a>
                        </div>
                        <div id="collapse<%= i %>" class="panel-collapse collapse">
                            <div class="panel-body">
                                <form action=""
                                      onsubmit="reserve('<%=resource.getName()%>', '<%=currentUser.getNetID()%>', '<%= i %>');">
                                    <div>
                                        <label>Select a date: </label><br/>
                                        <input id="date-<%= i %>" type="date" class="resource_additional"
                                               onchange="getTimeSlots('<%=resource.getName()%>', '<%= i %>');" required><br><br>
                                    </div>
                                    <div id="time-slots-<%= i %>">
                                    </div>
                                    <button class="btn btn-lg btn-primary btn-block">Reserve Resource
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <% } %>


                </div>
                <% } else { %>
                <div class="jumbotron">
                    <h2>Reserve Resources</h2>
                    <p>No resources to reserve.</p>
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
<script src="js/reserve_resource.js"></script>
</body>

</html>