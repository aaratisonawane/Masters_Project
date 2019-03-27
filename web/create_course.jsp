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
    <title>Create Course</title>
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
    <div id="main" class="container" >
        <h1 class="heading text-center">Create a course</h1>
        <form name="loginForm" class="form-signin" action="courses"
              onsubmit="validateForm();" method="post" style="max-width: 800px !important;" >
                <div class="pad" id="name_parent">
                    <label for="name">Course Details</label>
                    <div class="row" id="name">
                        <div class="col-lg-6">
                            <input id="course_num" name="course_num" type="text" class="form-control" placeholder="Number" autofocus required>
                        </div>
                        <div class="col-lg-6">
                            <input id="course_name" name="course_name" type="text" class="form-control" placeholder="Name" required>
                        </div>
                    </div>
                </div>


            <div class="pad" id="offered_sem">
                <label for="sem">Offered Term</label>
                <div class="row" id="sem">
                    <div class="col-lg-6">
                        <select name="sem" class="form-control">
                            <option>Fall</option>
                            <option>Spring</option>
                        </select>
                    </div>
                    <div class="col-lg-6">
                        <!--TODO add minimum year-->
                        <input name="year" id="start_year" type="number" class="form-control col-lg-6" placeholder="Year" required>
                    </div>
                </div>
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

            <div class="pad" id="course_syllabus_parent">
                <label for="course_syllabus">Course Syllabus</label>
                <textarea id="course_syllabus" name="course_syllabus" class="form-control" placeholder=""></textarea>
            </div>

            <div class="pad">
                <label for="office_details">Instructor Office Details</label>
                <div class="row" id="office_details">
                    <div class="col-lg-6">
                        <input id="ins_office" name="ins_office" type="text" class="form-control" placeholder="Location(Example: WCH 111)">
                    </div>
                    <div class="col-lg-6">
                        <input id="ins_office_hour" name="ins_office_hour" type="text" class="form-control" placeholder="Hours">
                    </div>
                </div>
            </div>

            <div class="pad">
                <label for="ta_name_details">TA Details</label>
                <div class="row" id="ta_name_details">
                    <div class="col-lg-6">
                        <input id="ta_name" name="ta_name" type="text" class="form-control" placeholder="Name">
                    </div>
                    <div class="col-lg-6">
                        <input id="ta_email" name="ta_email" type="email" class="form-control" placeholder="Email">
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <input id="ta_office" name="ta_office" type="text" class="form-control" placeholder="Location(Example: WCH 111)">
                    </div>
                    <div class="col-lg-6">
                        <input id="ta_office_hour" name="ta_office_hour" type="text" class="form-control" placeholder="Office Hours">
                    </div>
                </div>
            </div>

            <div class="alert alert-danger" role="alert" id="errors">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                <div id="error_msg"></div>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Create Course</button>
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
