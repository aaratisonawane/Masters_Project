<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student Details</title>
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
                <h1 class="heading text-center">Edit Student Details</h1>
                <form name="loginForm" class="form-signin" action="EditStudentServlet">
                    <div class="pad" id="name_parent">
                        <label for="name">Name</label>
                        <div class="row" id="name">
                            <div class="col-lg-6">
                                <input name="firstName" id="firstName" type="text" class="form-control col-lg-6"
                                       placeholder="First" required>
                            </div>
                            <div class="col-lg-6">
                                <input name="lastName" id="lastName" type="text" class="form-control col-lg-6"
                                       placeholder="Last" required>
                            </div>
                        </div>
                    </div>
                    <div class="pad" id="start_sem">
                        <label for="sem">Starting Term</label>
                        <div class="row" id="sem">
                            <div class="col-lg-6">
                                <select class="form-control" required>
                                    <option>Fall</option>
                                    <option>Spring</option>
                                </select>
                            </div>
                            <div class="col-lg-6">
                                <input name="year" id="start_year" type="text" class="form-control col-lg-6"
                                       placeholder="Year">
                            </div>
                        </div>
                    </div>
                    <div class="form-group" id="program">
                        <label for="prgm">Program</label>
                        <select class="form-control" id="prgm" required>
                            <option></option>
                            <option>BS</option>
                            <option>BA</option>
                            <option>MS</option>
                            <option>Ph.D</option>
                        </select>
                    </div>
                    <div class="form-group" id="department">
                        <label for="dept">Department</label>
                        <select class="form-control" id="dept" required>
                            <option></option>
                            <option>Chemical Engineering</option>
                            <option>Computer Science</option>
                            <option>Computer Engineering</option>
                            <option>Electrical Engineering</option>
                            <option>Environmental Sciences</option>
                            <option>Mechanical Engineering</option>
                        </select>
                    </div>

                    <button class="btn btn-lg btn-primary btn-success" type="submit">Save</button>
                    <button class="btn btn-lg btn-primary btn-danger" type="reset">Cancel</button>
                </form>
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