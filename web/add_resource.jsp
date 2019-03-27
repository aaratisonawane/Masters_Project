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
    <title>Create Resource</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<body onload="updateDisplay();">
<%@ include file="header.jsp" %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <h1 class="heading text-center">Create a resource</h1>
                <form name="createResourceForm" class="form-signin" action="/view_resources" method="post"
                      onsubmit="validateForm();">
                    <div class="pad" id="resource_name_parent">
                        <label for="resource_name">Resource Name</label>
                        <input id="resource_name" name="resource_name" type="text" class="form-control" placeholder="" autofocus required>
                    </div>
                    <div class="pad" id="resource_type_parent">
                        <label for="resource_type">Resource Type</label>
                        <input id="resource_type" name="resource_type" type="text" class="form-control" placeholder="" required>
                    </div>
                    <div class="pad" id="resource_additional_parent">
                        <label for="resource_additional">Additional Info</label>
                        <textarea id="resource_additional" name="resource_additional" class="form-control" placeholder=""></textarea>
                    </div>

                    <div class="alert alert-danger" role="alert" id="errors">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        <div id="error_msg"></div>
                    </div>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Create Resource</button>
                </form>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/sidebar.js"></script>
<script src="js/create_course.js"></script>

</body>

</html>
