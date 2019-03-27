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
    <title>Welcome to Pranita's WebPortal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
</head>
<body>
<div id="wrap">
    <div id="main" class="container">
        <h1 class="heading text-center">Welcome</h1>
        <p class="heading-desc text-center">Sign in to continue</p>
        <form name="loginForm" class="form-signin" action="LoginServlet">
            <center>
                <i class="img-circle glyphicon glyphicon-user icon-large icon-white"
                   style="font-size: 70px; padding: 20px;"></i>
            </center>
            <input name="netid" type="text" class="form-control" placeholder="Net ID" autofocus>
            <input name="password" type="password" class="form-control" placeholder="Password">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
        <a href="/SignUp" class="text-center account-creation">Create account</a>
    </div>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</body>

</html>
