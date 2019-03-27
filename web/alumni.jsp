<%@ page import="java.util.ArrayList" %>
<%@ page import="com.se.pranita.termproject.model.Alumni" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alumni</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<%
    ArrayList<Alumni> alumnis = (ArrayList) request.getAttribute("alumnis");
%>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <h1>Welcome to the Alumni Page.</h1>
                <% if (currentUser.getType().getValue() != 0) {%>
                <div>
                    <button type="button" class="btn btn-primary pull-right" data-toggle="modal"
                            data-target="#editOrAddModal">Add Alumni
                    </button>
                    <br/></div>
                <br/>
                <% } %>
                <% if (alumnis.size() > 0) {%>
                <ul class="list-group">
                    <% for (int i = 0; i < alumnis.size(); i++) {
                        Alumni alumni = alumnis.get(i);
                    %>
                    <li class="list-group-item">
                        <div class="row" id="name">

                            <% if (alumni.getImage().length() == 0) {%>
                            <div class="col-lg-2">
                                <center><i class="img-circle glyphicon glyphicon-tower icon-large icon-white"
                                   style="font-size: 50px; padding: 20px;"></i></center>
                            </div>
                            <%} else {%>
                            <div class="col-lg-2">
                                <center><img src="<%= alumni.getImage() %>" width="100" height="100"></center>
                            </div>
                            <% } %>
                            <div class="col-lg-10">
                                <% if (currentUser.getType().getValue() != 0) {%>
                                <button type="button" class="btn btn-danger pull-right" onclick='deleteAlumni(<%= alumni %>);'><i class="glyphicon-trash glyphicon"></i></button>
                                <button type="button" class="btn btn-warning pull-right" onclick='editAlumni(<%= alumni %>);'><i class="glyphicon-edit glyphicon"></i></button>
                                <% } %>
                                <b style="font-size: 20"><%= alumni.getName() %>
                                </b><br/>
                                <a href="<%= alumni.getHomepage() %>"><%= alumni.getHomepage() %>
                                </a><br/>
                                <p><%= alumni.getDescription() %>
                                </p>


                            </div>

                        </div>
                    </li>
                    <% } %>

                </ul>
                <% } else {%>
                <p>No Alumni Associations.</p>
                <% } %>
            </div>
        </div>



        <div id="deleteModalHere"></div>
        <div id="editModalHere"></div>

        <div id="editOrAddModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Alumni</h4>
                    </div>
                    <div class="modal-body">
                        <p>
                            <label for="add_modal_name">Name</label>
                            <input id="add_modal_name" name="name" type="text" class="form-control" placeholder=""
                                   autofocus>
                            <label for="add_modal_homepage">Homepage</label>
                            <input id="add_modal_homepage" name="homepage" type="text" class="form-control"
                                   placeholder="http://alumni.xyz">
                            <label for="add_modal_description">Description</label>
                            <textarea id="add_modal_description" name="description" type="text"
                                      class="form-control"></textarea>
                            <label for="add_modal_image">Image</label>
                            <input id="add_modal_image" name="image" type="text" class="form-control"
                                   placeholder="http://alumni.xyz/logo.png">
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" onclick="saveAlumni();">Save</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                    </div>
                </div>

            </div>
        </div>

        <%@ include file="footer.jsp" %>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/vendor/jquery.toaster.js"></script>

<script src="js/sidebar.js"></script>
<script src="js/alumni.js"></script>
</body>

</html>