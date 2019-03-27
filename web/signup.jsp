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
    <title>Create your WebPortal Account</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
</head>
<body onload="updateDisplay(); passwordError();">
<div id="wrap">
    <div id="main" class="container">
        <h1 class="heading text-center">Create your WebPortal Account</h1>
        <p class="heading-desc text-center">Already have an account? Log in <a href="/">Here</a>. </p>
        <form name="loginForm" class="form-signin" action="SignUpServlet" onchange="updateDisplay();"
              onsubmit="validateForm();" method="post">
            <div class="pad" id="netid_parent">
                <label for="netid">Net ID</label>
                <input id="netid" name="netID" type="text" class="form-control" placeholder="" autofocus>
            </div>
            <div class="pad" id="name_parent">
                <label for="name">Name</label>
                <div class="row" id="name">
                    <div class="col-lg-6">
                        <input name="firstName" id="firstName" type="text" class="form-control col-lg-6"
                               placeholder="First">
                    </div>
                    <div class="col-lg-6">
                        <input name="lastName" id="lastName" type="text" class="form-control col-lg-6"
                               placeholder="Last">
                    </div>
                </div>
            </div>
            <div class="pad" id="passParent">
                <label for="pass">Create a password</label>
                <div class="alert alert-danger" role="alert" id="pass_error">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error:</span>
                    Passwords do not match
                </div>
                <input name="password" type="password" class="form-control" id="pass" placeholder="">
            </div>
            <div class="pad" id="cnfPassParent">
                <label for="cnfPass">Confirm your password</label>
                <input name="confirmPassword" type="password" onblur="passwordError();" class="form-control" id="cnfPass" placeholder="">
            </div>
            <div class="pad">
                <label for="role">Role</label>
                <select name="role" class="form-control" id="role">
                    <option></option>
                    <option>Student</option>
                    <option>Faculty</option>
                    <option>Staff</option>
                </select>
            </div>
            <div class="pad" id="start_sem">
                <label for="sem">Starting Term</label>
                <div class="row" id="sem">
                    <div class="col-lg-6">
                        <select name="sem" class="form-control">
                            <option>Fall</option>
                            <option>Spring</option>
                        </select>
                    </div>
                    <div class="col-lg-6">
                        <input name="year" id="start_year" type="number" class="form-control col-lg-6" placeholder="Year">
                    </div>
                </div>
            </div>
            <div class="pad" id="program">
                <label for="prgm">Program</label>
                <select name="program" class="form-control" id="prgm">
                    <option></option>
                    <option>BS</option>
                    <option>MS</option>
                    <option>Ph.D</option>
                </select>
            </div>
            <div class="pad" id="department">
                <label for="dept">Department</label>
                <select name="department" class="form-control" id="dept">
                    <option>Chemical Engineering</option>
                    <option>Computer Science</option>
                    <option>Computer Engineering</option>
                    <option>Electrical Engineering</option>
                    <option>Environmental Sciences</option>
                    <option>Mechanical Engineering</option>
                </select>
            </div>

            <div class="alert alert-danger" role="alert" id="errors">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                <div id="error_msg"></div>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
        </form>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/signup.js"></script>
</body>

</html>
