<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.se.aarati.termproject.model.Discussion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thread Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
    <link href="css/forum.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<%
    ArrayList<Discussion> discussions = (ArrayList) request.getAttribute("discussions");
%>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">

                <% if (discussions.size() > 0) {%>
                <div>
                    <button type="button" class="btn btn-primary pull-right" data-toggle="modal"
                            data-target="#createModal">New Reply
                    </button>
                    <br/></div>
                <br/>
                <ul class="list-group">
                    <% for (int i = 0; i < discussions.size(); i++) {
                        Discussion discussion = discussions.get(i);
                    %>
                    <li class="list-group-item">

                        <div>
                            <div class="post-head-container">
                                <div class="post-head post-head-left-thread">
                                    <div class="postbit-social-left">
                                        <% if (discussion.getType().getValue() == 0) { %>
                                        <a><i
                                                class="g-list-alt glyphicon glyphicon-list-alt"></i></a>
                                        <% } else { %>
                                        <a><i
                                                class="g-list-alt glyphicon glyphicon-share-alt"></i></a>
                                        <% } %>
                                    </div>
                                </div>

                                <div class="post-head post-head-right-thread">
                                    <b><a><%= discussion.getTitle() %>
                                    </a></b>

                                    | <b class="head"><%= discussion.getType().toString() %>
                                </b>

                                    <div class="postbit-buttons">
                                        <% if (discussion.getNetID().equalsIgnoreCase(currentUser.getNetID()) || currentUser.getType().getValue() != 0) { %>
                                        <a href='javascript:deleteDiscussion(<%= discussion %>);' title="Delete Thread"><i
                                                class="g-delete glyphicon glyphicon-trash"></i></a>
                                        <% } %>
                                        <% if (discussion.getNetID().equalsIgnoreCase(currentUser.getNetID())) { %>
                                        <a href='javascript:editDiscussion(<%= discussion %>);' title="Edit Thread"><i
                                                class="g-edit glyphicon glyphicon-edit"></i></a>
                                        <% } %>
                                    </div>

                                </div>
                            </div>
                            <div class="post-content-container">
                                <div>
                                    <%= discussion.getDetails() %><br/><br/>
                                    <% if (discussion.getType().getValue() == 0) { %>
                                    <span class="time"><b>Thread by </b><a><%= discussion.getOwnerName() %></a></span>,
                                    <% } else { %>
                                    <span class="time"><b>Reply by </b><a><%= discussion.getOwnerName() %></a></span>,
                                    <% } %>
                                    <span class="time"><b>Last updated on </b><%= new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a").format(discussion.getUpdated_time()) %></span>
                                </div>
                            </div>
                        </div>
                    </li>
                    <% } %>

                </ul>
            </div>
            <% } else {%>
            <p>No Discussions.</p>
            <% } %>


        </div>


        <div id="deleteModalHere"></div>
        <div id="editModalHere"></div>

        <div id="createModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title">New Topic</h3>
                    </div>
                    <div class="modal-body">
                        <div class="pad">
                            <label for="add_modal_title">Title</label>
                            <input id="add_modal_title" name="title" type="text" class="form-control" placeholder=""
                                   autofocus required>
                        </div>
                        <div class="pad">
                            <label for="add_modal_details">Details</label>
                            <textarea id="add_modal_details" name="details" type="text"
                                      class="form-control"></textarea>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success"
                                onclick="saveDiscussion(1, '<%= discussions.get(0).getId() %>');">Post
                        </button>
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
<script src="js/discussion.js"></script>
</body>

</html>