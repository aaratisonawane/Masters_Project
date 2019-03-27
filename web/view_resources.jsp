<%@ page import="java.util.ArrayList" %>
<%@ page import="com.se.pranita.termproject.model.Reservation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resources</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<% ArrayList<Reservation> reservations = (ArrayList) request.getAttribute("reservations"); %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <% if(reservations.size() > 0) {%>
                <ul class="list-group">
                    <% for (int i = 0; i < reservations.size(); i++) {
                        Reservation reservation = (Reservation) reservations.get(i);
                    %>
                    <li class="list-group-item">
                        <button type="button" class="btn btn-danger pull-right" data-toggle="modal"
                                data-target="#cancelModal<%= i %>">Cancel
                        </button>
                        <div>
                            <b>Resource Name: </b><%=reservation.getResourceName()%><br/>
                            <%--<b>Resource Type: </b>Projector<br/>--%>
                            <b>Booked time slot: </b><br/>
                            <ul>
                                <li><%=reservation.getSlot_date()%>, <%=reservation.getTimeSlotVal()%><br/></li>
                            </ul>
                        </div>
                    </li>

                    <div id="cancelModal<%= i %>" class="modal fade" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Cancel Reservation</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Are you sure you want to cancel reservation for:<br/><b>Resource Name: </b><%=reservation.getResourceName()%><br/>
                                        <b>Booked time slot: </b><br/>
                                    <ul>
                                        <li><%=reservation.getSlot_date()%>, <%=reservation.getTimeSlotVal()%><br/></li>
                                    </ul>
                                    </p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="cancelIt('<%=reservation.getResourceName()%>', '<%=reservation.getNetID()%>', '<%=reservation.getSlot_date()%>', '<%=reservation.getSlot_time_range()%>');">Yes</button>
                                    <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                                </div>
                            </div>

                        </div>
                    </div>

                    <% } %>

                </ul>
                <% } else { %>
                    <div class="jumbotron">
                        <h2>Reserved Resources</h2>
                        <p>You haven't reserved any resources.</p>
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
<script src="js/view_resources.js"></script>

</body>

</html>