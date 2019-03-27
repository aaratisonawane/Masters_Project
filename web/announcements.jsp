<%@ page import="java.util.ArrayList" %>
<%@ page import="com.se.pranita.termproject.model.announcement.Announcement" %>
<%@ page import="com.se.pranita.termproject.model.announcement.Event" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Announcements</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/vendor/bootstrap-datetimepicker.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
    <link href="css/forum.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<%
    ArrayList<Announcement> announcements = (ArrayList) request.getAttribute("announcements");
%>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <h1>Announcements</h1>
                <% if (currentUser.getType().getValue() != 0) {%>
                <div>
                    <button type="button" class="btn btn-primary pull-right" data-toggle="modal"
                            data-target="#createModal">New Announcement
                    </button>
                    <br/></div>
                <br/>
                <% } %>
                <% if (announcements.size() > 0) {%>
                <ul class="list-group">
                    <% for (int i = 0; i < announcements.size(); i++) {
                        Announcement announcement = announcements.get(i);
                    %>
                    <li class="list-group-item">

                        <div>
                            <div class="post-head-container">
                                <div class="post-head post-head-left">
                                    <div>

                                        <i class="glyphicon glyphicon-user"></i>

                                        <a class="bigfusername"><%= announcement.getOwnerName() %>
                                        </a>

                                    </div>
                                </div>

                                <div class="post-head post-head-right">
                                    <span class="time"><%= new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a").format(announcement.getCreateTime()) %></span>
                                    | <b class="head"><%= announcement.getType().toString() %>
                                </b>
                                <% if(announcement.getNetID().equalsIgnoreCase(currentUser.getNetID())) { %>
                                    <div class="postbit-buttons">
                                        <a href='javascript:deleteAnnouncement(<%= announcement %>);' title="Delete Post"><i
                                                class="g-delete glyphicon glyphicon-trash"></i></a>
                                        <a href='javascript:editAnnouncement(<%= announcement %>);' title="Edit Post"><i class="g-edit glyphicon glyphicon-edit"></i></a>
                                    </div>
                                <% } %>
                                </div>
                            </div>
                            <div class="post-content-container">
                                <div>
                                    <b class="panel-title"><%= announcement.getTitle() %>
                                    </b><br/><br/>
                                    <%= announcement.getDetails() %>
                                    <br>
                                    <% if (announcement.getLink() != null) {%>
                                    <% if (announcement.getLink().length() > 0) {%>
                                    <b>Info: </b><%= announcement.getLink() %><br/>
                                    <% } %>
                                    <% } %>
                                    <% if (announcement.getType().getValue() == 1) {%>
                                    <b>Date: </b><%= new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a").format(((Event) announcement).getEventDatetime()) %>
                                    <br/>
                                    <b>Venue: </b><%= ((Event) announcement).getEventVenue() %><br/>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                    </li>
                    <% } %>

                </ul>
                <% } else {%>
                <p>No Announcements.</p>
                <% } %>
            </div>

        </div>


        <div id="deleteModalHere"></div>
        <div id="editModalHere"></div>

        <div id="createModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title">New Announcement</h3>
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
                        <div class="pad">
                            <label for="add_modal_link">Link or Contact Info</label>
                            <input id="add_modal_link" name="link" type="text" class="form-control"
                                   placeholder="http://link.xyz">
                        </div>
                        <div class="pad">
                            <label for="add_modal_type">Announcement Type</label>
                            <select name="type" class="form-control" id="add_modal_type" onchange="updateEventView('add');"
                                    required>
                                <option>Job Posting</option>
                                <option>Event</option>
                                <option>News</option>
                            </select>
                        </div>

                        <div class="pad" id="add_datetimepicker_event_parent" hidden>
                            <label for="add_datetimepicker_event">Event Date and Time</label>
                            <div class="input-group date" id="add_datetimepicker_event">
                                <input type="text" class="form-control"/> <span class="input-group-addon"><span
                                    class="glyphicon-calendar glyphicon"></span></span>
                            </div>
                        </div>
                        <div class="pad" id="add_modal_venue_parent" hidden>
                            <label for="add_modal_venue">Event Venue</label>
                            <input id="add_modal_venue" name="venue" type="text" class="form-control"
                                   placeholder="">
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" onclick="saveAnnouncement();">Post</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                    </div>
                </div>

            </div>
        </div>

        <%@ include file="footer.jsp" %>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/vendor/moment.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/vendor/jquery.toaster.js"></script>
<script src="js/vendor/bootstrap-datetimepicker.min.js"></script>

<script src="js/sidebar.js"></script>
<script src="js/announcement.js"></script>
</body>

</html>